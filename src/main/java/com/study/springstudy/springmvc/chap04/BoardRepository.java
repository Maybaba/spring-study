package com.study.springstudy.springmvc.chap04;

import com.study.springstudy.springmvc.chap04.common.Search;
import com.study.springstudy.springmvc.chap04.entity.Board;

import java.util.List;

//게시판 CRUD 기능
public interface BoardRepository {
    //게시물 목록 조회
    List<Board> findAll(Search page);

    List<Board> findAll();

    //게시글 상세 조회
    Board findOne(int boardNo);

    //게시글 등록
    boolean save(Board board);

    //게시글 삭제
    boolean delete(int boardNo);

    //조회수 상승
//    Board updateView(int boardNo);


}
