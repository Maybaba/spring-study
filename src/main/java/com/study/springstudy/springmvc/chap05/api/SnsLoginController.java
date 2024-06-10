package com.study.springstudy.springmvc.chap05.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class SnsLoginController {
    @GetMapping("/kakao/login")
    public String kakaoLogin() {

        //카카오 서버로 인가토드 발급 통신을 해야함
        //카카오 로그인 인가토드 받기
        String uri = "https://kauth.kakao.com/oauth/authorize";
        uri += "?client_id=ef241b1bc55d784d7c258243910498ac";
        uri += "&redirect_uri=http://localhost8181/oauth/kakao";
        uri += "&response_type=code";

        return "redirect:" + uri;
    }
}
