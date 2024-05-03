package org.zerock.spring_test_240502.DTO;

import lombok.*;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private String memberID;
    private String memberPW;
    private String memberName;
    private String phone;
    private String address;
    private String email;
}
