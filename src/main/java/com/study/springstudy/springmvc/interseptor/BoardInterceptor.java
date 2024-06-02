package com.study.springstudy.springmvc.interseptor;

import com.study.springstudy.springmvc.LoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//prehandle 구현해서
//로그인을 안하면 글쓰기, 글수정, 글삭제 요청을 거부할 것
//거부하고  (회원만 가능합니다 라고 알렛 띄우고) 로그인 페이지로 리다이렉션

@Configuration
@Slf4j
public class BoardInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        //로그인을 했다면
        //클라이언트 측에 저장된 세션을 가져와서
        //작업하던 곳으로 돌아간다.
        if (!LoginUtil.isLoggedIn(request.getSession())) {

            String redirectUri = request.getRequestURI();

            log.info("origin: {}", redirectUri);
            response.sendRedirect("/members/sign-in?message=login-required&redirect=" + redirectUri);
            return false;
        }
        return true;
    }
}
