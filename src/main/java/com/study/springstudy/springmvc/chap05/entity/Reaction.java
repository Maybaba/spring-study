package com.study.springstudy.springmvc.chap05.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reaction {

    private int reactionId;
    private int boardNo;
    private String account;
    private String reactionType;
    private LocalDateTime reactionDate;

}
