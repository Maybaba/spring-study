package com.study.springstudy.webservlet.chap02.v2.controller;

import com.study.springstudy.webservlet.MemberMemoryRepo;
import com.study.springstudy.webservlet.View;
import com.study.springstudy.webservlet.entity.Member;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//인터페이스를 오버라이딩 하기 때문에 직접 매핑하지 않는다?
public class ShowController implements ControllerV2 {

    private MemberMemoryRepo repo = MemberMemoryRepo.getInstance();

    @Override
    public View process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //적절한 저장소에서 회원정보들을 가져움
        List<Member> memberList = repo.findAll();

        //해당 회원 정보를 JSP 파일에 정손하기 위한 세팅을 함
        request.setAttribute("memberList", memberList);

        //적절한 JSP를 찾아 화면 렌더링
//        String viewName = "v1/m-list";
//        RequestDispatcher dp = request.getRequestDispatcher(viewName);
//        dp.forward(request,response);

        return new View("v2/m-list");
    }
}
