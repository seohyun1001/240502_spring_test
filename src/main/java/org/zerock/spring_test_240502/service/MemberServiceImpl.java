package org.zerock.spring_test_240502.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.spring_test_240502.DTO.MemberDTO;
import org.zerock.spring_test_240502.domain.MemberVO;
import org.zerock.spring_test_240502.mapper.MemberMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberMapper memberMapper;
    private final ModelMapper modelMapper;

    @Override
    public void join(MemberDTO memberDTO) {
        log.info(modelMapper);
        MemberVO memberVO = modelMapper.map(memberDTO, MemberVO.class);
        log.info(memberVO);
        memberMapper.joinMember(memberVO);
    }

    @Override
    public MemberDTO login(MemberDTO memberDTO) {
        MemberVO memberVO = memberMapper.loginSql(memberDTO);
        return modelMapper.map(memberVO, MemberDTO.class);
    }

}
