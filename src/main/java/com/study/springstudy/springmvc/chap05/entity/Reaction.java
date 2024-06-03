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
    private long boardNo;
    private String account;
    @Setter
    private ReactionType reactionType;
    private LocalDateTime reactionDate;

}
