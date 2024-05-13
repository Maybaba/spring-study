package com.study.springstudy.chap03;

public class AsianRestaurant implements Restaurant {

    private Chef chef;

    private Course course;

    //생성자를 만들어서 원하는 생성자를 활용하여 개발할 수 있게끔 한다.

    public AsianRestaurant(Chef chef, Course course) {
        this.chef = chef;
        this.course = course;
    }

//    public void orderMenu() {
    public void order() {
        System.out.println("아시안 요리를 주문합니다.");
        course.combineMenu();
        chef.cook();
    }
}
