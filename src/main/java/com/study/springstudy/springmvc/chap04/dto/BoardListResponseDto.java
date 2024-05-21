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
    private boolean hit; // HIT 게시물인가?
    private boolean newArticle; // 새 게시물(1시간 이내)인가?

    //엔터티를 DTO로 변환하는 생성자
    public BoardListResponseDto(Board b) {
        this.bno = b.getBoardNo();
        this.shortTitle = makeShortTitle(b.getTitle());
        this.shortContent = makeShortContent(b.getContent());
        this.view = b.getViewCount();
        this.date = dateFormatting(LocalDateTime.parse(b.getRegDateTime().toString()));
        // 게시물 등록시간
        LocalDateTime regTime = b.getRegDateTime();
        this.hit = this.view > 5;
        this.newArticle = LocalDateTime.now().isBefore(regTime.plusMinutes(5));
    }

    private String dateFormatting(LocalDateTime regDateTime) {
        DateTimeFormatter pattern
                = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return pattern.format(regDateTime);
    }

    private String makeShortContent(String content) {
        return content.length() > 30 ? content.substring(0, 30) + "..." : content;
    }

    private String makeShortTitle(String title) {
        return title.length() > 5 ? title.substring(0, 5) + "..." : title;
    }

}
