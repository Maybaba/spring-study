package com.study.springstudy.springmvc.chap04.dto;

import com.study.springstudy.springmvc.chap04.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//서버에서 조회한 데이터 중 화면에 필요한 (클라이언트에게 보여줄) 데이터만 모아놓은 클래스
@Getter @Setter
@NoArgsConstructor
public class BoardListResponseDto {

    /*
    {
    shortTitle: "",
    ...
    }
     */
    private int bno;
    private String shortTitle; //5글자 이상 줄임 처리된 제목 -> 클라이언트 개발자와 같이 정하는 필드명
    private String shortContent;//30글자 이상 줄임 처리된 내용
    private String date; //포맷팅된 날짜 문자열
    private int view; // 조회 수

    //엔터티를 DTO로 변환하는 생성자
    public BoardListResponseDto(Board b) {
        this.bno = b.getBoardNo();
        this.shortTitle = makeShortTitle(b.getTitle());
        this.shortContent = makeShortContent(b.getContent());
        this.view = b.getViewCount();
        this.date = dateFormate(LocalDateTime.parse(b.getRegDateTime().toString()));
    }

    private String dateFormate(LocalDateTime localDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM 월 dd 일 HH:mm");
        return dateTimeFormatter.format(localDateTime);
    }

    private String makeShortContent(String content) {
        return content.length() > 30 ? content.substring(0, 30) + "..." : content;
    }

    private String makeShortTitle(String title) {
        return title.length() > 5 ? title.substring(0, 5) + "..." : title;
    }

}
