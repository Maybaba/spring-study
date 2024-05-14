package com.study.springstudy.webservlet.chap02.v2.controller;

import com.study.springstudy.webservlet.View;
import com.study.springstudy.webservlet.chap02.v1.controller.ControllerV1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JoinController implements ControllerV2 {
    @Override
    public View process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //경로의 일정 부분을 param으로 보내서 위임시키기
        return new View("v2/reg_form");
    }
}
