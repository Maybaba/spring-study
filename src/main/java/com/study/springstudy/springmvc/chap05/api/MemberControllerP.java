package com.study.springstudy.springmvc.chap05.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/members")
@Slf4j
public class MemberControllerP {
    // 회원가입 양식 열기
    @GetMapping("/sign-up")
//    @ResponseBody
    // url이 jsp경로와 같으면 void로 설정해도 알아서 위에꺼 가져와서 열기 가능
    public void signUp(){
        log.info("/members/sign-up GET : forwarding to sign-up.jsp");
        //return "members/sign-up";
    }
}
