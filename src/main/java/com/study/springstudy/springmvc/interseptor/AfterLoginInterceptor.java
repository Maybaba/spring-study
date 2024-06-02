package com.study.springstudy.springmvc.interseptor;

import com.study.springstudy.springmvc.LoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//로그인한 회원은 회원가입 페이지와 로그인 페이지에 접근을 차단
@Configuration
@Slf4j
public class AfterLoginInterceptor implements HandlerInterceptor {

    //클라이언트의 요청이 컨트롤러에 들어가기 전에 해야할 일을 명시

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.debug("after login interceptor execute : ) ");

        //로그인한 상태의 세션 얻기
        if(LoginUtil.isLoggedIn(request.getSession())) {
            //로그인 세션인 경우 로그인 및 회원가입 uri 요청시
            response.sendRedirect("/"); //go home
            return false;
        }
        return true; //true 일 경우 로그인 및 회원가입 컨트롤러 진입 허용
    }
}

