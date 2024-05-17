package com.study.springstudy.core.chap04;

import org.springframework.stereotype.Component;

@Component//("western : able to modify bean name!")
public class WesternRestaurant implements Restaurant {

    //담당 쉐프
    private Chef chef;

    //요리 코스
    private Course course;

    //생성자를 만들어서 개발자가 원하는 객체가 생성되도록 코드를 작성한다.
    public WesternRestaurant(Chef chef, Course course) {
        this.chef = chef;
        this.course = course;
    }

    //주문 기능
    public void order() {
        System.out.println("서양 요리를 주문합니다.");
        course.combineMenu();
        chef.cook();
    }
}
