package com.study.springstudy.webservlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// JSP 파일을 포워딩하는 역할
public class View {
    private  String viewName; //포워딩할 뷰의 경로
    private String prefix; //경로에 있는 공통 접두사
    private String suffix; //경로에 있는 공통 접미사
    private boolean redirect; //리다이렉트 여부

    //viewName에 register를 전달하면
    //완성된 이름은 /WEB-INF/views/resister.jsp
    //redirect 일 때는 앞에 있는 redirect : 을 제거해야 함..  . .
    public View(String viewName) {
        this.prefix = "/WEB-INF/views/";
        this.suffix = ".jsp";

        if(!isRedirect(viewName)) {
            this.viewName = prefix + viewName + suffix;
        } else {
            //redirect:/chap02/v2/show : 이후 1글자 뒤부터 포함해서 확인하기
            this.viewName.substring(viewName.indexOf(":") + 1);
        }
    }

    //포워딩 기능 (화면을 보여주는 렌더링 역할) or 리다이렉트 기능
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(!this.redirect) {
            RequestDispatcher dq = request.getRequestDispatcher(viewName);
            dq.forward(request, response);
        } else {
            response.sendRedirect(viewName);
        }
    }

    //리다이렉트인지 포워드인지 확인하는 메서드
    private boolean isRedirect(String viewName) {
        this.redirect = viewName.startsWith("redirect:");
        return this.redirect; //true
    }
}