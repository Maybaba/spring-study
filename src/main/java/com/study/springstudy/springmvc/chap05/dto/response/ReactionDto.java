package com.study.springstudy.springmvc.chap05.dto.response;

import lombok.*;

@Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReactionDto {

    //좋아요 처리를 위해 클라이언트에 보낼 JSON
    private int likeCount;
    private int dislikeCount;
    private String userReaction; //안눌렸는지 좋아요인지 싫어요인지
}
