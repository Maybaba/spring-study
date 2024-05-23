package com.study.springstudy.springmvc.chap04.entity;

import com.study.springstudy.springmvc.chap04.dto.BoardPostDto;
import lombok.*;
//import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Setter
@Getter //세터게터
@NoArgsConstructor //빈 생성자
@AllArgsConstructor
@ToString
@Builder
public class Board {
    private int boardNo;
    private String title;
    private String content;
    private String writer;
    private int viewCount;
    private LocalDateTime regDateTime;


    //data class, java beans 라고 부르기도 한다.
    public Board(ResultSet rs) throws SQLException {
        this.boardNo = rs.getInt("board_no");
        this.title = rs.getString("title");
        this.content = rs.getString("content");
        this.writer = rs.getString("writer");
        this.viewCount = rs.getInt("view_count");
        this.regDateTime = rs.getTimestamp("reg_date_time").toLocalDateTime();
    }
    //wrap board
    public Board(BoardPostDto dto){
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.writer = dto.getWriter();
    }

}

