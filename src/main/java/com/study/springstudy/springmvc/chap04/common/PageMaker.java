package com.study.springstudy.springmvc.chap04.common;

//페이지 화면 렌더링에 필요한 정보들을 계산

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class PageMaker {
    //한 화면에 페이지넘버를 몇개씩 가져가야 할지?
    private static final int PAGE_COUNT = 10;

    //페이지 시작번호, 끝번호 저장
    private int begin, end;

    //이전, 다음 버튼 활성화 여부
    private boolean prev, next;

    //총 게시물 수
    private int totalCount;

    //현재 페이지 정보
    private Page pageInfo;
    private int finalPage, beginPage;

    public PageMaker(Page page, int totalCount) {
        this.pageInfo = page;
        this.totalCount = totalCount;
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
        그런데 총 게시물 수를 고려를 안하고 있다.
         */

        this.end = (int) Math.ceil(pageInfo.getPageNo() / (double)PAGE_COUNT) * PAGE_COUNT;
        //알트엔터로 타입캐스팅 할 수 있다!!!

        //2. begin
        this.begin = this.end - PAGE_COUNT + 1;

        //3. 마지막 페이지 구간에서 end값 보정하기
        /*
        총 게시물이 237개이고 한 화면에 게시물을 10개씩 배치하고 있다면
        1 ~ 10 페이지 구간 : 게시물 100개
        11 ~ 20 100
        21 ~ 30 37

        -> 마지막 구간에서 end 값이 30이 맞는지 확인 필요
        -> 실제로는 24로 보정되어야 함

        // 마지막 페이지 번호를 구하는 공식

        351 10개 배치
       끝페이지 36페이지

       올림 ( 총 게시물 수 / 한페이지당 배치할 게시물 수 )

         */
        this.finalPage = (int) Math.ceil((double)totalCount/pageInfo.getAmount());

        //마지막 구간에서 end값을 final page로 보정 : 마지막 페이지로 출력
        if(finalPage < this.end) {
            this.end = finalPage;
        }

        //4. 이전 버튼 활성화 여부
        this.prev = begin!=1;

        //5. 다음버튼활성화여부
        this.next = this.end < finalPage;
    }

}
