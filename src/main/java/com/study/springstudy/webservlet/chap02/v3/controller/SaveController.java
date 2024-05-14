package com.study.springstudy.webservlet.chap02.v3.controller;

import com.study.springstudy.webservlet.MemberMemoryRepo;
import com.study.springstudy.webservlet.ModelAndView;
import com.study.springstudy.webservlet.View;
import com.study.springstudy.webservlet.entity.Member;
import org.springframework.boot.Banner;

import java.util.Map;


public class SaveController implements ControllerV3{

    MemberMemoryRepo repo = MemberMemoryRepo.getInstance();
    @Override
    public ModelAndView process(Map<String, String> paramMap) {
        //1. 회원가입 폼에서 넘어온 데이터 읽기 ( 계정명, 비밀번호, 이름 )
        String account = paramMap.get("account");
        String password = paramMap.get("password");
        String userName = paramMap.get("userName");



        //2. 회원정보를 객체로 포장하여 적절한 저장소에 저장

        Member member = new Member(account, password, userName);
        repo.save(member);

        //3. 적절한 페이지로  리다이렉트
        return new ModelAndView("redirect:/chap02/v3/show");
    }
}
