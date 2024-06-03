package com.study.springstudy.springmvc.chap05.mapper;

import com.study.springstudy.springmvc.chap05.dto.repuest.ReactPostDto;
import com.study.springstudy.springmvc.chap05.entity.Reaction;
import com.study.springstudy.springmvc.chap05.entity.ViewLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface ReactionMapper {

    int saveLike(@Param("boardNo") int boardNo, @Param("account") String account);

    int saveDislike(@Param("boardNo") int boardNo, @Param("account") String account);
    // CRUD
    // 리액션 (기록) 추가 (Insert)
    boolean saveReaction(Reaction reaction);

    // 리액션 이늄 수정 (update)
    void updateReaction(Reaction reaction);

    // 리액션 (기록) 삭제(delete)
    void removeReaction(ViewLog viewLog);

    // 리액션 기록 조회 (bno로 조회해서 reaction list findAll 해버리기)
    Reaction findAll(@Param("reactionType") String reactionType, @Param("bno") long bno);

}
