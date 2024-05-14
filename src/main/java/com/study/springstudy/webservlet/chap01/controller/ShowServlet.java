package com.study.springstudy.webservlet.chap01.controller;

import com.study.springstudy.webservlet.MemberMemoryRepo;
import com.study.springstudy.webservlet.entity.Member;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/chap01/show")
public class ShowServlet extends HttpServlet {

    private MemberMemoryRepo repo = MemberMemoryRepo.getInstance();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //적절한 저장소에서 회원정보들을 가져움
        List<Member> memberList = repo.findAll();

        //해당 회원 정보를 JSP 파일에 정손하기 위한 세팅을 함
        req.setAttribute("memberList", memberList);

        //적절한 JSP를 찾아 화면 렌더링
        String viewName = "/WEB-INF/views/m-list.jsp";
        RequestDispatcher dp = req.getRequestDispatcher(viewName);
        dp.forward(req,resp);
    }
}
