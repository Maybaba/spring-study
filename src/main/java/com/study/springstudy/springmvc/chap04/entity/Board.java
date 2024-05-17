package com.study.springstudy.springmvc.chap04.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter @Getter //세터게터
@NoArgsConstructor //빈 생성자
@AllArgsConstructor //초기화 생성자
public class Board {
    private int boardNo;
    private String title;
    private  String content;
    private String writer;
    private int viewCount;
    private LocalDateTime regDageTime;

}
