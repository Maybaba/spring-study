package com.study.springstudy.springmvc;

import javax.servlet.http.HttpSession;


public class LoginUtil {

    public static final String LOGIN = "login";

    //로그인 여부 확인
    public static boolean isLoggedIn(HttpSession session) {
        return session.getAttribute(LOGIN) != null;
    }
}
