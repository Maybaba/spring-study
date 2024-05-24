package com.study.springstudy.springmvc.chap05.dto.repuest;

import lombok.*;

@Getter @Setter
@ToString
@EqualsAndHashCode
@Builder
public class ReplyPostDto {

    private String text; //댓글내용
    private String author;
    private Long bno;//원본 글번호 받기

}
