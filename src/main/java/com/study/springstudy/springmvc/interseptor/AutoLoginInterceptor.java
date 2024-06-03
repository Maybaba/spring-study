package com.study.springstudy.springmvc.interseptor;

import com.study.springstudy.springmvc.LoginUtil;
import com.study.springstudy.springmvc.chap05.entity.Member;
import com.study.springstudy.springmvc.chap05.mapper.MemberMapper;
import com.study.springstudy.springmvc.chap05.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Configuration
public class AutoLoginInterceptor implements HandlerInterceptor {

    private final MemberMapper memberMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        //1. 사이트에 들어오면 자동로그인 쿠키를 가지고 있는지 확인

        Cookie autoLoginCookie = WebUtils.getCookie(request, LoginUtil.AUTO_LOGIN_COOKIE);


//        WebUtils.getCookies() 밖에 없는데 스프링에서 하나만 빼오는 getCookie를 만들어놨다.
/*
        Cookie[] cookies = request.getCookies(); //쿠키가 배열로 들어온다...

        for (Cookie cookie : cookies) {
            if(cookie.getName().equals(LoginUtil.AUTO_LOGIN_COOKIE)) {
                return true;
            }
        }

 */

        //2. 자동로그인 쿠키가 있으면 사이트 로그인 처리를 수행
        if (autoLoginCookie != null) {
            //3. 쿠키에 들어있는 랜덤값 읽기
            String sessionId = autoLoginCookie.getValue(); //멤버서비승서 쿠키의 값을 저장했 그 값을 읽는다

            //4. 세션아이디로 회원정보 조회
            Member foundMember = memberMapper.findMemberBySessionId(sessionId);

            //5. 회원이 정상 조회되었고
            // 자동 로그인 만료시간 이전이라면
            // 사이트 로그인 처리(세션에 DTO 세팅)을 수행
            if (foundMember != null
                    && LocalDateTime.now().isBefore(foundMember.getLimitTime())) {

                MemberService.maintainLoginState(request.getSession(), foundMember);
                return true;
            }
        }
        return false;
    }
}

