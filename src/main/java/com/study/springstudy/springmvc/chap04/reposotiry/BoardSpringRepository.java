package com.study.springstudy.springmvc.chap04.reposotiry;

import com.study.springstudy.springmvc.chap04.BoardRepository;
import com.study.springstudy.springmvc.chap04.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //spring이 관리하는 클래스
@RequiredArgsConstructor //값 final 선언
public class BoardSpringRepository implements BoardRepository {

    //templete : 데이터베이스에 추가할 객체 생성
    private final JdbcTemplate template;
    @Override
    public List<Board> findAll() {
        String sql = "SELECT * FROM tbl_board "; //+ sortStatement(sort);
        return template.query(sql, (rs, n) -> new Board(rs)); //새로운 내용 추가해서 리턴하기
        }

    @Override
    public Board findOne(int boardNo) {
        String sql = "SELECT * FROM tbl_board WHERE board_no = ?";
        return template.queryForObject(sql, (rs, n) -> new Board(rs), boardNo);
    }

    @Override
    public boolean save(Board board) { //저장 되는지 불린값으로 확인

            String sql = "INSERT INTO tbl_board " +
                    "(title, content, writer)" +
                    "VALUES(?,?,?)";

            return template.update(sql, board.getTitle(), board.getContent(),
                    board.getWriter()) == 1;
        }

    @Override
    public boolean delete(int boardNo) {
        String sql = "DELETE FROM tbl_board WHERE board_no = ?";
        return template.update(sql, boardNo) == 1;
    }
}
