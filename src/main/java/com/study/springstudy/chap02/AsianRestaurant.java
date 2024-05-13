package com.study.springstudy.chap02;

public class AsianRestaurant implements Restaurant{

    private KimuraChef chef = new KimuraChef();

    private SushiCourse course = new SushiCourse();

//    public void orderMenu() {
    public void order() {
        System.out.println("아시안 요리를 주문합니다.");
        course.combineMenu();
        chef.cook();
    }
}
