package com.study.springstudy.springmvc.chap05.dto.response;

import com.study.springstudy.springmvc.chap05.entity.Member;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginUserInfoDto {
    //클라이언트에 보낼 정보
    private String account;
    private String nickName;
    private String email;
    private String auth;

    public LoginUserInfoDto(Member member) {
        this.account = member.getAccount();
        this.nickName = member.getName();
        this.email = member.getEmail();
        this.auth = member.getAuth().name(); //.name은 이늄 대문자만 나타낸다 DB 에 저장되어 있는 문자와 비교하기
        System.out.println(this);
    }
}
