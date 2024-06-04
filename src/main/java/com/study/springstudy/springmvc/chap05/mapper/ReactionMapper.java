package com.study.springstudy.springmvc.chap05.mapper;


import com.study.springstudy.springmvc.chap05.entity.Reaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface ReactionMapper {

//    int saveLike(@Param("boardNo") int boardNo, @Param("account") String account);
//
//    int saveDislike(@Param("boardNo") int boardNo, @Param("account") String account);
    // CRUD
    // 리액션 (기록) 추가 (Insert) 좋아요 싫어요 처음 찍었을 때
    boolean save(Reaction reaction);

    // 리액션 이늄 수정 (update)
    void update(Reaction reaction);

    // 리액션 (기록) 삭제(delete)
    void delete(@Param("boardNo") long boardNo, @Param("account")String account);

    // 리액션 기록 조회 - 사용자가 특정 게시물에 리액션 했는지 확인
    Reaction findOne(@Param("boardNo") long boardNo, @Param("account") String account);

    // 특정 게시물에 총 좋아요 수 조회
    int countLikes(long boardNo);

    // 특정 게시물에 총 싫어요 수 조회
    int countDislikes(long boardNo);
}


