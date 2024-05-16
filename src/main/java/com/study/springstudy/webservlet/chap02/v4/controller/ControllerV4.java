package com.study.springstudy.webservlet.chap02.v4.controller;

import com.study.springstudy.webservlet.Model;
import com.study.springstudy.webservlet.ModelAndView;

import java.util.Map;

public interface ControllerV4 {
    /**
     * process : 요청이 들어오면 적절한 수행
     * @param paramMap : 요청정보(쿼리파라미터)를 모두 읽어서 맵에 담음
     * @param model : 응답 시 보여 줄 JSP에 보낼 데이터를 담는 수송객체
     * @return : 응답시 포워딩하거나 리다이렉트 할 경로의 문자열만 전달하면 알아서 전달하기
     */
    String process(Map<String, String> paramMap, Model model);
}
