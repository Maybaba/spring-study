package com.study.springstudy.core.chap04;

import org.springframework.stereotype.Component;

//@Component
public class KimuraChef implements Chef {

    @Override
    public void cook() {
        System.out.println("스시의 장인 키무라 데수");
    }
}
