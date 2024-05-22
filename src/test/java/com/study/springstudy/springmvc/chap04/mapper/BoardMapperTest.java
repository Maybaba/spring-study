package com.study.springstudy.springmvc.chap04.mapper;

import com.study.springstudy.springmvc.chap04.entity.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardMapperTest {

    @Autowired
    BoardMapper mapper;

    @Test
    @DisplayName("")
    void insertTsrt() {
        for (int i = 0; i < 300; i++) {
            Board b = new Board();
            b.setTitle("title" + i);
            b.setWriter("writer" + i);
            b.setContent("content" + i);

            mapper.save(b);

        }
    }

}