package org.zerock.spring_test_240502.service;

import org.zerock.spring_test_240502.DTO.MemberDTO;

public interface MemberService {

    void join(MemberDTO memberDTO);

    MemberDTO login(MemberDTO memberDTO);

}
