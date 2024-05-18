package com.study.springstudy.springmvc;

import lombok.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor //내용이 빈 기본생성자
@AllArgsConstructor //모든필드 초기화 생성자
//@RequiredArgsConstructor //필수 파라미터(final field) 초기화 생성자
public class Student {

    private String name;
    private int age;
    private int grade;

}
