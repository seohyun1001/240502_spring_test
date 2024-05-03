package org.zerock.spring_test_240502.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.spring_test_240502.DTO.BookDTO;
import org.zerock.spring_test_240502.domain.BookVO;
import org.zerock.spring_test_240502.domain.MemberVO;
import org.zerock.spring_test_240502.mapper.BookMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final ModelMapper modelMapper;
    private final BookMapper bookMapper;

    @Override
    public void registerBook(BookDTO bookDTO) {
        log.info(modelMapper);
        BookVO bookVO = modelMapper.map(bookDTO, BookVO.class);
        log.info(bookVO);
        bookMapper.addBook(bookVO);

    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<BookDTO> bookInfo = bookMapper.bookList().stream()
                .map(vo -> modelMapper.map(vo,BookDTO.class))
                .collect(Collectors.toList());
        log.info(bookInfo);
        return bookInfo;
    }

    @Override
    public BookDTO getOneBook(String id) {
        BookVO bookVO = bookMapper.bookInfo(id);
        BookDTO bookDTO = modelMapper.map(bookVO, BookDTO.class);
        return bookDTO;
    }

    @Override
    public void removeBook(String id) {
        bookMapper.delete(id);
    }

    @Override
    public void modifyBook(BookDTO bookDTO) {
        BookVO bookVO = modelMapper.map(bookDTO, BookVO.class);
        bookMapper.update(bookVO);
    }
}
