package com.study.springstudy.springmvc.chap04.entity;

import com.study.springstudy.springmvc.chap03.dto.ScorePostDto;
import com.study.springstudy.springmvc.chap04.dto.BoardPostDto;
import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter @Getter //세터게터
@NoArgsConstructor //빈 생성자
@ToString
public class Board {
    private int boardNo;
    private String title;
    private String content;
    private String writer;
    private int viewCount;
    private LocalDateTime regDateTime;


    //data class, java beans 라고 부르기도 한다.
    public Board(ResultSet rs) throws SQLException {
        this.boardNo = rs.getInt("boardNo");
        this.title = rs.getString("title");
        this.content = rs.getString("content");
        this.writer = rs.getString("writer");
        this.viewCount = rs.getInt("viewCount");
        //regsate는 언제?
    }
    //wrap board
    public Board(BoardPostDto dto) {
        this.boardNo = dto.getBoardNo();
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.viewCount = dto.getViewCount();
    }

}