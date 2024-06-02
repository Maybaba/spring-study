package com.study.springstudy.springmvc.interseptor;

import com.study.springstudy.springmvc.LoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@Slf4j
public class UserIntercepter implements HandlerInterceptor {

    //로그인을 했는데 account 가 다른 사람의 게시물을 uri로 삭제할 시 방지하기
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("u r trying to delete other's board !!");
        if(LoginUtil.isLoggedIn(request.getSession())) {
            //지우려 할 때
            // 지우는 bno 와
            // 작성자가 작성한 보드 bno 조회해서
            // bno 없을 경우 컨트롤러 진입 차단
            return false; //진입
        }

        //bno 없을 경우
        return true;
    }
}
