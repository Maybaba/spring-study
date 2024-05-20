package com.study.springstudy.springmvc.chap04.dto;

import com.study.springstudy.springmvc.chap04.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 역할 : 브라우저에서 전달한 게시판정보를 포장하는 객체
@Getter @Setter
@AllArgsConstructor
@ToString
public class BoardPostDto {

    // 필드명은 반드시 html form 태그의 입력태그는 jsp name 속성과 같아야 함.

    private String title; //8글자 이상 함축
    private String content; //중간부터 가려짐
    private String writer;

    public Board toEntity() {
        Board b = new Board();
        b.setContent(this.content);
        b.setWriter(this.writer);
        b.setTitle(this.title);
        return b;
    }
}
