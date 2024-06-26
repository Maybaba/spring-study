package com.study.springstudy.springmvc.chap05.dto;

import com.study.springstudy.springmvc.chap04.common.PageMaker;
import com.study.springstudy.springmvc.chap05.dto.response.ReplyDetailDto;
import lombok.*;

import java.util.List;
//객체 안의 배열
// {
//  "replies" : [
//      {} {} {}
//         ]
//  }

@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyListDto {

    private PageMaker pageInfo; //페이지 끝번호, 시작번호

    private List<ReplyDetailDto> replies;

}
