package com.study.springstudy.springmvc.interseptor;

import com.study.springstudy.springmvc.LoginUtil;
import com.study.springstudy.springmvc.chap04.entity.Board;
import com.study.springstudy.springmvc.chap04.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.study.springstudy.springmvc.LoginUtil.isAdmin;
import static com.study.springstudy.springmvc.LoginUtil.isMine;


@Configuration
@Slf4j
@RequiredArgsConstructor
public class BoardInterceptor implements HandlerInterceptor {

    private final BoardMapper boardMapper;

    // preHandle을 구현하여
    // 로그인을 안한 회원은 글쓰기, 글수정, 글삭제 요청을 거부할 것!
    // 거부하고 로그인 페이지로 리다이렉션할 것!
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        // 요청 URL
        String redirectUri = request.getRequestURI();

        if (!LoginUtil.isLoggedIn(session)) {
            log.info("origin: {}", redirectUri);
            response.sendRedirect("/members/sign-in?message=login-required&redirect=" + redirectUri);
            return false;
        }

        // 삭제요청이 들어오면 서버에서 한번더 관리자인지? 자기가쓴글인지 체크
        // 관리자인가?
        if (isAdmin(session)) {
            return true;
        }

        // 삭제요청인지?
        if (redirectUri.equals("/board/delete")) {
            // 내가 쓴 글이 아닌지??
            // 현재 삭제하려는 글의 글쓴이 계정명과
            // -> DB에서 조회해보면 됨 -> 글번호가 필요함
            int bno = Integer.parseInt(request.getParameter("bno"));
            Board board = boardMapper.findOne(bno);
            String boardAccount = board.getAccount();

            // 현재 로그인한 회원의 계정명을 구해서
            String loggedInUserAccount = LoginUtil.getLoggedInUserAccount(session);

            // 대조해보는 작업이 필요함
            if (!isMine(boardAccount, loggedInUserAccount)) {
                response.setStatus(403);
                response.sendRedirect("/access-deny?message=authorization");
                return false;
            }

        }
        return true;
    }
}


//
//import com.study.springstudy.springmvc.LoginUtil;
//import com.study.springstudy.springmvc.chap04.entity.Board;
//import com.study.springstudy.springmvc.chap04.mapper.BoardMapper;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//
//import static com.study.springstudy.springmvc.LoginUtil.*;
//
////prehandle 구현해서
////로그인을 안하면 글쓰기, 글수정, 글삭제 요청을 거부할 것
////거부하고  (회원만 가능합니다 라고 알렛 띄우고) 로그인 페이지로 리다이렉션
//
//@Configuration
//@Slf4j
//@RequiredArgsConstructor
//public class BoardInterceptor implements HandlerInterceptor {
//
//    private final BoardMapper boardMapper;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        //로그인을 했다면
//        //클라이언트 측에 저장된 세션을 가져와서
//        //작업하던 곳으로 돌아간다.
//
//
//        if (!isLoggedIn(request.getSession())) {  // 로그인을 안 했을 때
//            String encodedRedirectUri = URLEncoder.encode(redirectUri, StandardCharsets.UTF_8);
////            log.info("origin: {}", redirectUri);
//            request.getSession().setAttribute("redirect", encodedRedirectUri);  // 세션에 리다이렉트 URI 저장
//            response.sendRedirect("/members/sign-in?message=login-required");
//            return false;
//        }
//
//        //삭제요청인가에 대한 관리자인가?
//        if (isAdmin(session)) {
//            return true; //네, 통과
//        }
//        //내가 쓴 글인지 아닌지 확인하기
//        if (redirectUri.equals("/board/delete")) {
//            //내가 쓴 글이 아닌지?
//            //현재 삭제하려는 글의 글쓴이 계정명과
//            // DB에서 bno 조회해보면 됨 -> 글번호 구하기
//            String loggedInUserAccount = getLoggedInUserAccount(session);
//
//            int bno = Integer.parseInt(request.getParameter("bno"));
//            Board board = boardMapper.findOne(bno);
//            String boardAccount = board.getAccount();
//
//            //현재 로그인한 회원의 계정명을 구해서
//
//            //대조해보는 작업이 필요함
//            if (!isMine(boardAccount, loggedInUserAccount)) {
//                response.setStatus(403); //권한이 없음 : forbidden
//                response.sendRedirect("/access-deny?message=authorization");
//                return false;
//            }
//        }
//
//        return true;
//    }
//}
