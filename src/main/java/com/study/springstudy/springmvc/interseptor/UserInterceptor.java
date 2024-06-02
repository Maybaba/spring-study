package com.study.springstudy.springmvc.interseptor;

import com.study.springstudy.springmvc.LoginUtil;
import com.study.springstudy.springmvc.chap04.dto.BoardDetailResponseDto;
import com.study.springstudy.springmvc.chap04.entity.Board;
import com.study.springstudy.springmvc.chap04.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Integer.parseInt;

@Configuration
@Slf4j
public class UserInterceptor implements HandlerInterceptor {

    //로그인을 했는데 account 가 다른 사람의 게시물을 uri로 삭제할 시 방지하기
    //삭제 시 board No = member's account board No 있는지 확인
    @Autowired
    private BoardService boardService; // 보드 서비스를 주입 받아 사용

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("You are trying to delete someone else's board!!");

        // 요청 매개변수에서 boardNo 추출
        String boardNo = request.getParameter("bno");
        log.debug("You are trying to delete board No {}", boardNo);

        if (boardNo == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing boardNo parameter");
            return false;
        }

        int bno;
        try {
            bno = parseInt(boardNo);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid boardNo parameter");
            return false;
        }

        // 세션에서 로그인된 사용자 계정 가져오기
        String loggedInUserAccount = LoginUtil.getLoggedInUserAccount(request.getSession());
        log.debug("you're account is {}", loggedInUserAccount);


        // 보드 번호로 보드 객체 가져오기
        BoardDetailResponseDto boardDetail = boardService.detail(bno);
        log.debug("You are trying to delete board {}", boardDetail);

        if (boardDetail == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Board not found");
            return false;
        }

        // 보드의 작성자 계정과 로그인된 사용자 계정을 비교
        if (!loggedInUserAccount.equals(boardDetail.getAccount())) {

            log.debug("You are trying to delete OTHER'S board or you are not log-in state");

            //delete 더 이전 단계는 없을까?

            response.sendRedirect("/board/list");

            return false; // 일치하지 않을 경우 진입 차단
        }
        return true; //일치할 경우 컨트롤러 허용
    }
}
