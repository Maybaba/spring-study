package com.study.springstudy.springmvc.chap04.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 역할 : 브라우저에서 전달한 게시판정보를 포장하는 객체
@Getter @Setter
@AllArgsConstructor
@ToString
public class BoardPostDto {
    private int boardNo;
    private String title; //8글자 이상 함축
    private String content; //중간부터 가려짐
    private int viewCount;
    private int regDateTime;
}
