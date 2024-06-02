package com.study.springstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@ServletComponentScan(basePackages = "com.study.springstudy") : 이거 서블릿 보려고 설치한 거라서 딱히 메인에 없어도 됨
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}