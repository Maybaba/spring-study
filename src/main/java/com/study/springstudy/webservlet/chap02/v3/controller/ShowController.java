package com.study.springstudy.webservlet.chap02.v3.controller;

import com.study.springstudy.webservlet.MemberMemoryRepo;
import com.study.springstudy.webservlet.ModelAndView;
import com.study.springstudy.webservlet.entity.Member;
import java.util.List;
import java.util.Map;

//인터페이스를 오버라이딩 하기 때문에 직접 매핑하지 않는다?
public class ShowController implements ControllerV3 {

    private MemberMemoryRepo repo = MemberMemoryRepo.getInstance();

    @Override
    public ModelAndView process(Map<String, String> paramMap) {
        //조회된 데이터 가져오기
        List<Member> memberList = repo.findAll();
        //데이터 전송하기
        ModelAndView modelAndView = new ModelAndView("v3/m-list");
        modelAndView.addAttribute("memberList", memberList);

        return new ModelAndView("v2/m-list");
    }

}
