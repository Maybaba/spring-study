package com.study.springstudy.core.chap04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@Component
public class Hotel {

    // 레스토랑
    //필드 주입
    //@Autowired //자동으로 객체 연결하기
    private final Restaurant restaurant;

    // 헤드쉐프
    //@Autowired
    private final Chef headChef;

    //해당 클래스에 생성자가 단 한개뿐이람면 자동으로 @autoWired를 붙여준다. 생략할 수 있으며, 이를 생성자 주입이라고 부른다.

    public Hotel(@Qualifier("asianRestaurant") Restaurant restaurant, Chef headChef) {
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

//    @Autowired //불변성을 보장할 수 없기 때문에 추천하지 않는다.
//    public void setRestaurant(Restaurant restaurant) {
//        this.restaurant = restaurant;
//    }

    public Chef getHeadChef() {
        return headChef;
    }

//    @Autowired
//    public void setHeadChef(Chef headChef) {
//        this.headChef = headChef;
//    }
}
