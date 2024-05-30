package com.study.springstudy.springmvc.chap05.dto.repuest;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDto {

    private String account;
    private String password;
    private boolean autoLogin; //자동 로그인 체크 여부

}
