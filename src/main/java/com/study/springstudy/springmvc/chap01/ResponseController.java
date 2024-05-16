package com.study.springstudy.springmvc.chap01;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller //공통url 없이 설계
public class ResponseController {

    //JSP 파일로 포워딩할때 데이터 전달하기
    //1. Model 객체 사용하기
    @RequestMapping("/hobbies")
    public String hobbies(Model model) {

        model.addAttribute("name", "김철수수깡");
        model.addAttribute("hobbies", List.of("독서", "음악", "그림", "윤지후"));

        model.addAttribute("major", "물리학");

        return "mvc/hobbies";
    }

    //2, ModelAndView 객체 사용하기
    @RequestMapping("/hobbies2")
    public ModelAndView hobbies2() {
        ModelAndView mv = new ModelAndView("mvc/hobbies");
        mv.addObject("name", "메이");
        mv.addObject("hobbies", List.of("노래듣기", "산책하기")); //like show controller
        mv.addObject("major", "physics");

        return mv;
    }
}
