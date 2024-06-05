package com.study.springstudy.springmvc.interseptor;

import com.study.springstudy.springmvc.LoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.*;

@Slf4j
@Configuration
public class ApiAuthInterceptor implements HandlerInterceptor {

    //비동기 통신 시 인가 처리
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            HttpSession session = request.getSession();

            //로그인하지 않았다면
            if (!LoginUtil.isLoggedIn(session)) {
                //에러메세지 응답 (403 상태코드 전송)
                log.info("인가되지 않은 접근입니다. Unauthorized request to URL: {}", request.getRequestURI());
                response.sendError(403); //status code
//                response.sendError(HttpServletResponse.SC_FORBIDDEN); //status code
                return false;
            }
            return true;
        }
    }

