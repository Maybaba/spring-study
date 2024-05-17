package com.study.springstudy.core.chap03;

import org.springframework.stereotype.Component;

/*
 * @solution
 * - 먼저 DIP를 해결하기 위해 구체적인 객체 대신
 * 추상적인 역할에 의존하게 코드를 개선
 *
 *
 *@problem - 추상화를 했지만 여전히 의존객체를 바꾸러면
 *           코드를 직접 변경해야 한다.
 *
 * 제어의 역전(IoC) : 객체 생성의 제어권을 외부로 넘긴다.
 * 의존성 주입(DI) : 외부에서 생성된 객체를 주입받는 개념
 */
 //Hotel의 객체생성 제어권을 스프링에게 넘김
public class Hotel {

    // 레스토랑
    private Restaurant restaurant;

    // 헤드쉐프
    private Chef headChef;

    public Hotel(Restaurant restaurant, Chef headChef) {
        this.restaurant = restaurant;
        this.headChef = headChef;
    }

    // 호텔을 소개하는 기능
    public void inform() {
        System.out.printf("우리 호텔의 레스토랑은 %s입니다. " +
                        "그리고 헤드쉐프는 %s입니다.\n"
                , restaurant.getClass().getSimpleName()
                , headChef.getClass().getSimpleName());

        restaurant.order();
    }
}
