package org.zerock.spring_test_240502.domain;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberVO {

    private String memberID;
    private String memberPW;
    private String memberName;
    private String phone;
    private String address;
    private String email;

}
