package org.zerock.spring_test_240502.mapper;

import org.zerock.spring_test_240502.DTO.MemberDTO;
import org.zerock.spring_test_240502.domain.MemberVO;

public interface MemberMapper {

    void joinMember(MemberVO memberVO);

    MemberVO loginSql(MemberDTO memberDTO);


}
