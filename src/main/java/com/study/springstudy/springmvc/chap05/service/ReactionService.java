package com.study.springstudy.springmvc.chap05.service;

import com.study.springstudy.springmvc.chap05.dto.repuest.ReactPostDto;
import com.study.springstudy.springmvc.chap05.entity.Reaction;
import com.study.springstudy.springmvc.chap05.mapper.ReactionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReactionService {

    private final ReactionMapper reactionMapper;

    //반응 입력
    public boolean insert(ReactPostDto dto) {

        Reaction reaction = Reaction.builder()
                .account(dto.getAccount())
                .reactionType(dto.getReactionType())
                .reactionDate(LocalDateTime.parse(dto.getReactionDate()))
                .build()
                ;

        int result;
        if ("LIKE".equals(action)) {
            result = reactionMapper.saveLike(boardNo, account);
        } else if ("DISLIKE".equals(action)) {
            result = reactionMapper.saveDislike(boardNo, account);
        } else {
            return false;
        }
        return result > 0;

        boolean flag = reactionMapper.saveReaction(reaction);
        if(flag) log.info(" 반응 남기기 성공! -{}",dto);
        else log.warn("반응 남기기 실패");

        return flag;
    }

    //반응 업데이트

    //반응 삭제

    //반응 업데이트


}
