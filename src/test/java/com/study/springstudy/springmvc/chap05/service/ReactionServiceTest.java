package com.study.springstudy.springmvc.chap05.service;

import com.study.springstudy.springmvc.chap05.entity.Reaction;
import com.study.springstudy.springmvc.chap05.mapper.ReactionMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.study.springstudy.springmvc.chap05.entity.ReactionType.LIKE;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReactionServiceTest {

    @Autowired ReactionMapper reactionMapper;

    @Test
    @DisplayName("반응을 남기면 데이터테이블에 저장된다. ")
    void saveT() {
        //given
        Reaction r = Reaction.builder()
                .account("kitty")
                .reactionType(LIKE)
                .boardNo(100)
                .build();

        //when
        boolean b = reactionMapper.saveReaction(r);

        //then
        assertEquals(true, b);
    }




}