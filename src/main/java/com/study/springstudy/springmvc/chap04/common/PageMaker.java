package com.study.springstudy.springmvc.chap04.common;

//페이지 화면 렌더링에 필요한 정보들을 계산

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class PageMaker {
    //한 화면에 페이지넘버를 몇개씩 가져가야 할지?
    private static final int PAGE_COUNT = 5;

    //페이지 시작번호, 끝번호 저장
    private int begin, end;

    //현재 페이지 정보
    private Page pageInfo;

    public PageMaker(Page page) {
        this.pageInfo = page;
        makePageInfo();
    }

    //페이지 생성에 필요한 데이터를 만드는 알고리즘
    private void makePageInfo() {
        //1. end값 계산

        /*
        지금 사용자가 7페이지를 보고 있다면
        페이지구간
         7 -> 1 ~ 10
        24 -> 21 ~ 30
        //5개씩 페이지를 배치하는 경우는
        7page : 6 ~ 10
        24page : 21 ~ 25

        공식: (올림 (현재 사용자가 위치한 페이지넘버 / 한 화면에 보여줄 페이지 수)) * 한 화면에 보여줄 페이지 수
        개천재인디??
         */

        this.end = (int) Math.ceil(pageInfo.getPageNo() / (double)PAGE_COUNT) * PAGE_COUNT;
        //알트엔터로 타입캐스팅 할 수 있다!!!

        //2. begin
        this.begin = this.end - PAGE_COUNT + 1;
    }

}
