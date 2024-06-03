package com.study.springstudy.springmvc.interseptor;

import com.study.springstudy.springmvc.LoginUtil;
import com.study.springstudy.springmvc.chap04.entity.Board;
import com.study.springstudy.springmvc.chap04.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.study.springstudy.springmvc.LoginUtil.getLoggedInUserAccount;
import static com.study.springstudy.springmvc.LoginUtil.isMine;

@RequiredArgsConstructor
@Configuration
public class ViewCountInterceptor implements HandlerInterceptor {

    private final BoardMapper boardMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

            // 조회하는 사람이 게시글을 조회했을 경우
        //글에 대한 쿠키가 로그인 세션에 의해 생기고, 사용자의 로컬에 저장된다.
        //자기가 쓴 글엔 조회수가 올라가지 않는다.
        //남이 쓴 글엔 한시간에 한번 씩 1회를 올린다.

        HttpSession session = request.getSession();

        String loggedInUserAccount = getLoggedInUserAccount(session);

        int bno = Integer.parseInt(request.getParameter("bno"));
        Board board = boardMapper.findOne(bno);
        String boardAccount = board.getAccount();

            if((loggedInUserAccount.equals(boardAccount)) && (LoginUtil.isLoggedIn(session)) ) {
                //보드 글번호 조회해서 새로운 쿠키 넣기

                return true; //회원이고 남이 쓴 글일때 한시간에 1회씩 조회수 올린다.
            }
        return false;//비회원이거나 자기가 쓴 글일 때 카운트를 하지 않는다.
    }
}
