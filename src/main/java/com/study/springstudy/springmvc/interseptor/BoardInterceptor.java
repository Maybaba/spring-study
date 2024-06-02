package com.study.springstudy.springmvc.interseptor;

import com.study.springstudy.springmvc.LoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

//prehandle 구현해서
//로그인을 안하면 글쓰기, 글수정, 글삭제 요청을 거부할 것
//거부하고  (회원만 가능합니다 라고 알렛 띄우고) 로그인 페이지로 리다이렉션

@Configuration
@Slf4j
public class BoardInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {



        if (!LoginUtil.isLoggedIn(request.getSession())) {  // 로그인을 안 했을 때
            String redirectUri = request.getRequestURI();
            String encodedRedirectUri = URLEncoder.encode(redirectUri, StandardCharsets.UTF_8.toString());
            log.info("origin: {}", redirectUri);
            response.sendRedirect("/members/sign-in?message=login-required&redirect=" + encodedRedirectUri);  // 작업하던 uri로 리턴
            return false;
        }
        return true;
    }
}
