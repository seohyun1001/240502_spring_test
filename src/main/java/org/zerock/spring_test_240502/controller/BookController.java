package org.zerock.spring_test_240502.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.spring_test_240502.DTO.BookDTO;
import org.zerock.spring_test_240502.DTO.MemberDTO;
import org.zerock.spring_test_240502.service.BookService;
import org.zerock.spring_test_240502.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/book")
@Log4j2
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // addBook 페이지
    @GetMapping("/addBook")
    public void addBook() {
        log.info("------- /book/add GET -------");
    }

    // add(책 등록)
    @PostMapping("/addBook")
    public String addBookPost(MultipartFile file,
                              BookDTO bookDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) throws IOException {
        log.info("------- /book/add POST -------");

        log.info(file.getOriginalFilename());
        log.info(file.getSize());
        log.info(file.getContentType());
        file.transferTo(new File("c://files//" + file.getOriginalFilename()));
        bookDTO.setImgFileName(file.getOriginalFilename());

        if (bindingResult.hasErrors()) {
            log.info("bindingResult has errors");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/book/addBook";
        }
        log.info(bookDTO);

        bookService.registerBook(bookDTO);

        return "redirect:/book/books";
    }

    // 도서 목록 및 편집
    @GetMapping({"/books","/editBook"})
        public void books(Model model) {
        log.info("------- /book/books GET -------");
        model.addAttribute("books", bookService.getAllBooks());
    }

    // 도서 상세정보
    @GetMapping({"/book", "/updateBook"})
    public void book(String id, Model model) {
        log.info("------- /book/book GET -------");
        log.info(id);
        model.addAttribute("book", bookService.getOneBook(id));
    }

    // 도서 삭제
    @GetMapping("/remove")
    public String removeBook(String id, RedirectAttributes redirectAttributes) {
        log.info("------- /book/remove POST -------" + id);
        bookService.removeBook(id);
        return "redirect:/book/books";
    }

    // 도서 수정
    @PostMapping("/updateBook")
    public String updateBook(@RequestParam("file") MultipartFile file,
                             BookDTO bookDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            log.info("bindingResult has errors");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("book", bookDTO.getId());
            return "redirect:/book/updateBook";
        }

        log.info(bookDTO);

        if (!file.isEmpty()) {
            log.info(file.getOriginalFilename());
            log.info(file.getSize());
            log.info(file.getContentType());
            file.transferTo(new File("c://files//" + file.getOriginalFilename()));
            bookDTO.setImgFileName(file.getOriginalFilename());
        } else {
            BookDTO existingBook = bookService.getOneBook(bookDTO.getId());
            bookDTO.setImgFileName(existingBook.getImgFileName());
        }

        bookService.modifyBook(bookDTO);

        return "redirect:/book/books";

    }

}
