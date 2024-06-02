package com.study.springstudy.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/index") //프로젝트할땐 @GetMapping( "/") 로 해서 경로 입력 안해도 그냥 접속 할 수 있도록 하자!
    public void home() {}
}