package com.study.springstudy.springmvc.chap04.reposotiry;

import com.study.springstudy.springmvc.chap04.entity.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@Rollback //(false) 테스트이후에도 트랜잭션을 메모리 유지되도록 설정하기
class BoardSpringRepositoryTest {

    @Autowired
    BoardSpringRepository boardSpringRepository;

    //각 테스트 전에 공통으로 실행할 코드
    @BeforeEach
    void bulkInsert() {
        for (int i = 0; i <20 ; i++) {
            Board board = new Board();
            board.setTitle("test " + i);
            board.setContent("content " + i);
            board.setWriter("writer " + i);
            board.setViewCount(0); // 기본값이므로 생략 가능
            boardSpringRepository.save(board);
        }
    }
    @Test
    @DisplayName("게시판 데이터를 입력하면 데이터베이스에 반드시 저장되어야 한다.")
    void saveTest() {
        // gwt 패턴
        // given : 테스트에 주어질 데이터
        Board board = new Board();
        board.setTitle("new title");
        board.setContent("new content");
        board.setWriter("new writer");

        // when : 테스트 상황
        boolean result = boardSpringRepository.save(board);

        // then : 테스트 결과 단언
        assertEquals(true, result, "데이터 베이스에 입력한 게시글이 저장되었습니다.");
    }

}