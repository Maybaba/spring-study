package com.study.springstudy.springmvc.chap05.entity;


import lombok.*;

import java.time.LocalDateTime;

@Getter
// 무분별한 setter는 지양한다.
// db의 데이터를 변동없이 불변성유지하여 들고오기 위해서 그렇게 진행한다.
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {

    private long replyNo;
    @Setter
    private String replyText;
    private String writer; //조회할 때 사용하는 이르
    private LocalDateTime replyDate;
    private long boardNo;
    private String account;
    private String replyWriter; //이전에 입력했던 비회원 이름

}
