package com.study.springstudy.core.chap04;

import org.springframework.stereotype.Component;

@Component
public class JannChef implements Chef {

    @Override
    public void cook() {
        System.out.println("프랑스 요리의 대가 쟝 입니다 🪬");
    }
}
