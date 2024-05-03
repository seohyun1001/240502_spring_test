package org.zerock.spring_test_240502.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.spring_test_240502.DTO.MemberDTO;
import org.zerock.spring_test_240502.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // join(회원가입 페이지)
    @GetMapping("/join")
    public void join() {
        log.info("------- /member/join GET -------");
    }

    // join(회원가입 정보 저장)
    @PostMapping("/join")
    public String joinPost(MemberDTO memberDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) throws IOException {
        log.info("------- /member/join POST -------");

        if (bindingResult.hasErrors()) {
            log.info("bindingResult has errors");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/member/join";
        }
        log.info(memberDTO);

        memberService.join(memberDTO);

        return "redirect:/member/login";
    }

    // login(로그인 페이지)
    @GetMapping("/login")
    public void login() {
        log.info("------- /member/login GET -------");
    }

    // 로그인 처리
    @PostMapping("/login")
    public String loginPost(MemberDTO memberDTO, HttpServletRequest req,
                            Model model, RedirectAttributes redirectAttributes) {
        log.info("------- /member/login POST -------");

        try {
            MemberDTO loginInfo = memberService.login(memberDTO);
            HttpSession session = req.getSession(true);
            session.setAttribute("loginInfo", loginInfo);
            model.addAttribute("info", loginInfo);
            log.info("로그인 성공 : " + loginInfo.getMemberID());
            return "redirect:/";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "아이디와 비밀번호를 확인해 주십시오.");
            log.info("로그인 실패");
            return "redirect:/member/login";
        }
    }

    // 로그아웃 처리
    @GetMapping("/logout")
    public String  logout(HttpServletRequest req) {

        HttpSession session = req.getSession();
        session.removeAttribute("loginInfo");
        session.invalidate();
        log.info("로그아웃 성공");

        return "redirect:/";
    }



}
