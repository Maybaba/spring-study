package com.study.springstudy.springmvc.config;

import com.study.springstudy.interseptor.AfterLoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//만들어 놓은 인터셉터들을 스프링 컨텍스트에 등록하는 설정 파일
@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private final AfterLoginInterceptor afterLoginInterceptor;

    //설정 메서드

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(afterLoginInterceptor)
                //해당 인터셉터가 동작할 URL을 설정한다
                .addPathPatterns("/members/sign-up", "/members/sign-in");
    }
}