package com.study.springstudy.chap01;

public class Hotel {

    //레스토랑
    private WesternRestaurant restaurant = new WesternRestaurant();
    //헤드쉐프
    private JannChef headChef = new JannChef();


    //호텔을 소개하는 기능
    public void inform() {
        System.out.printf("우리 호텔의 레스토랑은 %s입니다." + " 그리고 헤드쉐프는 %s 입니다. \n" );
    }
}
