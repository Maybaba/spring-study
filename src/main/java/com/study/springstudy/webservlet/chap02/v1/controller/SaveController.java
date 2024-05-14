package com.study.springstudy.webservlet.chap02.v1.controller;

import com.study.springstudy.webservlet.MemberMemoryRepo;
import com.study.springstudy.webservlet.entity.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveController implements ControllerV1{
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException {

        MemberMemoryRepo repo = MemberMemoryRepo.getInstance();

            //1. 회원가입 폼에서 넘어온 데이터 읽기 ( 계정명, 비밀번호, 이름 )
            String userName = request.getParameter("userName");
            String account = request.getParameter("account");
            String password = request.getParameter("password");



            //2. 회원정보를 객체로 포장하여 적절한 저장소에 저장

            Member member = new Member(account, password, userName);
//        System.out.println("member = " + member);
            repo.save(member);

            //3. 적절한 페이지로  리다이렉트
            response.sendRedirect("/chap02/v1/show");
    }

    }

