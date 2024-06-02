package com.study.springstudy.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity // 시큐리티 설정 파일
public class SecurityConfig {

    //시큐리티 기본 설정(인증 및 인가 처리, 초기 로그인 화면 없애기)
    @Bean //@Component (@Controller, @Service, @Repository, @Mapper)등과 같은 관리 클래스 어노태이션인데
    //@Baen : 은 내가 안만든 클래스에 의존성 주입해 달라는 것 : autoWired
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //csrf 토큰공격방지 기능 off
                .authorizeRequests()
                .antMatchers("/**")//모든 요청에 대해 인증하기 않겠다.
                .permitAll();

              return  http.build();
    }

    //비밀번호를 인코딩하는 객체를 스프링 컨테이너에 등록
    @Bean //아래 인터페이스를 주입받아 사용할 수 있고, 테스트도 해볼 수 있다,
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
