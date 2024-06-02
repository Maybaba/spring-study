package com.study.springstudy.springmvc;

import com.study.springstudy.springmvc.chap05.dto.response.LoginUserInfoDto;

import javax.servlet.http.HttpSession;


public class LoginUtil {
    public static final String LOGIN = "login";
    //로그인 여부 확인
    public static boolean isLoggedIn(HttpSession session) {
        return session.getAttribute(LOGIN) != null;
    }

    // 로그인한 회원의 account 찾기
    public static String getLoggedInUserAccount(HttpSession session) {
        LoginUserInfoDto currentUser
                = (LoginUserInfoDto) session.getAttribute(LOGIN);
        return  (currentUser != null) ? currentUser.getAccount() : null;
    }

}
