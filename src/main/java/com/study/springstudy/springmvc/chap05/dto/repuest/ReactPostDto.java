package com.study.springstudy.springmvc.chap05.dto.repuest;

import com.study.springstudy.springmvc.chap05.entity.ReactionType;
import lombok.*;

@Getter @Setter
@ToString
@EqualsAndHashCode
@Builder
public class ReactPostDto { //좋아요 버튼 혹은 싫어요 버튼 눌렀을 때 담기는 정보

    private String account; //반응을 남긴 유저
    private Long bno; //반응을 남긴 보드 번호
    private ReactionType reactionType; //네가 남긴 반응
    private String reactionDate; //네가 반을을 남긴 날짜

}
