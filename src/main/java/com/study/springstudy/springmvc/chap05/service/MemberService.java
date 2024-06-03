package com.study.springstudy.springmvc.chap05.service;

import com.study.springstudy.springmvc.LoginUtil;
import com.study.springstudy.springmvc.chap05.dto.repuest.AutoLoginDto;
import com.study.springstudy.springmvc.chap05.dto.repuest.LoginDto;
import com.study.springstudy.springmvc.chap05.dto.repuest.SignUpDto;
import com.study.springstudy.springmvc.chap05.dto.response.LoginUserInfoDto;
import com.study.springstudy.springmvc.chap05.entity.Member;
import com.study.springstudy.springmvc.chap05.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.time.LocalDateTime;

import static com.study.springstudy.springmvc.LoginUtil.*;
import static com.study.springstudy.springmvc.chap05.service.LoginResult.*;

@Service //스프링에 객체생성등록
@Slf4j //로그찍기위한 어노태이션
@RequiredArgsConstructor
public class MemberService {

    // 의존성 주입
    private final MemberMapper memberMapper;
    private final PasswordEncoder encoder;


    //회원가입중간처리
    public boolean join(SignUpDto dto) {

        //dto 엔터티로 변환
       Member member = dto.toEntity();

        //비번 인코딩(암호화) : spring security module
        String encodePassword = encoder.encode(dto.getPassword());
        member.setPassword(encodePassword);

        return memberMapper.save(member);
    }

    //로그인 검증 처리 1. 문자데이터를 주는 방법 2. 오류 에러 서버번호를 내리는 법
    public LoginResult authenticate(LoginDto dto, HttpSession session, HttpServletResponse response) {

        //회원가입 여부 확인
        String account = dto.getAccount();

        Member foundMember = memberMapper.findOne(account);

        if(foundMember == null) {
            log.info("{} - 회원가입이 필요합니다", account);
            return NO_ACC;
        }

        //비밀번호 일치검사
        String inputPassword = dto.getPassword(); //클라이언트에 입력한 비번
        String originPassword = foundMember.getPassword(); //데이터베이스에 저장된 비번

        //PasswordEncoder에서는 암호회된 비번을 내부적으로 비교해주는 기능 제공 (확인기능한 불린값만 제공)
        if(!encoder.matches(inputPassword, originPassword)) {
//        if (!inputPassword.equals(originPassword)) {
            log.info("비밀번호가 일치하지 않습니다 ");
            return NO_PW;
        }

        //자동로그인 추가 처리
        if (dto.isAutoLogin()) {
            //1. 자동 로그인 쿠키 생성
            // - 쿠키 내부에 절대로 중복되지 않는 유니크한 값을 저장
            //(UUID, SessionID) 세션아이디같은 경우 절대절대 중복이 나지 않는다.
            //쿠키이ㅡㅡ 이름은상수로 지정한다.
            String sessionId = session.getId();
            Cookie autoLoginCookie = new Cookie(AUTO_LOGIN_COOKIE, sessionId);

            //쿠키 설정
            autoLoginCookie.setPath("/"); //쿠키를 사용할 경로, 사이트 전체에서 쿠키를 사용하겠다
            autoLoginCookie.setMaxAge(60*60*24*90); //90일로 자동로그인 기간 설정

            //2. 쿠키를 클라이언트에 전송 - 응답 바디에 실어보내야 함
            response.addCookie(autoLoginCookie);

            //3. DB에도 해당 쿠키값을 저장
            memberMapper.updateAutoLogin(AutoLoginDto.builder()
                            .sessionId(sessionId)
                            .limitTime(LocalDateTime.now().plusDays(90))
                            .account(account)
                    .build());

        }

        return maintainLoginState(session, foundMember);
    }

    public static LoginResult maintainLoginState(HttpSession session, Member foundMember) {
        log.info("{}님 로그인 성공 짝짝짝", foundMember.getName());

        //세션의 수명 (지속시간) 조작하기 : 설정된 시간 OR 브라우저를 닫기 전까지
        int maxInactiveInterval = session.getMaxInactiveInterval();
        session.setMaxInactiveInterval(60*60);
        log.debug("session time : {}", maxInactiveInterval);

        session.setAttribute(LOGIN,new LoginUserInfoDto(foundMember));

        return SUCCESS;
    }

    //아이디, 이메일 중복검사
    public boolean checkIdentifier(String type, String keyword) {
        return memberMapper.existsById(type, keyword);
    }


    //오토 로그인 삭제
    public void autoLoginClear(HttpServletRequest request, HttpServletResponse response) {

        //1. 쿠키 제거하기
        Cookie c = WebUtils.getCookie(request, AUTO_LOGIN_COOKIE);

        if (c != null) {
            c.setPath("/");
            c.setMaxAge(0); //쿠키 지속 시간 0초 설정
            response.addCookie(c); //시간 재설정 응답
        }
        //2. DB에 자동로그인 칼럼들을 원래대로 돌려놓기
        memberMapper.updateAutoLogin(
                AutoLoginDto.builder()
                        .sessionId("none")
                        .limitTime(LocalDateTime.now())
                        .account(LoginUtil.getLoggedInUserAccount(request.getSession()))
                        .build()
        );
    }
}
