package com.study.springstudy.springmvc.chap05.service;

import com.study.springstudy.springmvc.chap05.dto.repuest.LoginDto;
import com.study.springstudy.springmvc.chap05.dto.repuest.SignUpDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("회원가입을 하면 비밀번호가 인코딩된다. ")
    void joinT() {
        //given
        SignUpDto dto = SignUpDto.builder()
                .account("kitty")
                .email("kitty@naver.com")
                .password("kkk1234")
                .name("키티")
                .build();

        //when
        boolean flag = memberService.join(dto);

        //then
        assertTrue(flag);
    }

    @Test
    @DisplayName("아이디가 존재하지 않는 경우를 테스트한ㄷㅏ. ")
    void NoAccT() {
        //given
        LoginDto dto = LoginDto.builder()
                .account("45234234")
                .password("45254654gfhf")
                .build();

        //when
        LoginResult result = memberService.authenticate(dto);

        //then
        assertEquals(LoginResult.NO_ACC, result);
    }

    @Test
    @DisplayName("비밀번호가 존재하지 않는 경우를 테스트한ㄷㅏ. ")
    void NoPWT() {
        //given
        LoginDto dto = LoginDto.builder()
                .account("kitty")
                .password("45254654gfhf")
                .build();

        //when
        LoginResult result = memberService.authenticate(dto);

        //then
        assertEquals(LoginResult.NO_PW, result);
    }
    @Test
    @DisplayName("로그인이 성공하는 경우를 테스트한ㄷㅏ. ")
    void SuccessT() {
        //given
        LoginDto dto = LoginDto.builder()
                .account("kitty")
                .password("kkk1234")
                .build();

        //when
        LoginResult result = memberService.authenticate(dto);

        //then
        assertEquals(LoginResult.SUCCESS, result);
    }

}