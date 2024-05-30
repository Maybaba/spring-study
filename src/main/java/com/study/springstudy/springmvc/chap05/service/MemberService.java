package com.study.springstudy.springmvc.chap05.service;

import com.study.springstudy.springmvc.chap05.dto.repuest.SignUpDto;
import com.study.springstudy.springmvc.chap05.entity.Member;
import com.study.springstudy.springmvc.chap05.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

}
