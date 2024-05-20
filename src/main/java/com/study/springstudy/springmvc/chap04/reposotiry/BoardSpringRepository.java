package com.study.springstudy.springmvc.chap04.reposotiry;

import com.study.springstudy.springmvc.chap04.BoardRepository;
import com.study.springstudy.springmvc.chap04.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //spring이 관리하는 클래스
@RequiredArgsConstructor //생성자 값 final 선언
public class BoardSpringRepository implements BoardRepository {

    //templete : 데이터베이스에 추가할 객체 생성 -> 의존할 개체 주입 -> 객체의 불변성 유지
    private final JdbcTemplate template;

    @Override
    public List<Board> findAll() {
        String sql = "SELECT * FROM tbl_board ";
//        return template.query(sql, new RowMapper<Board> () {
//        @Override
//        public Board mapRow(ResulrSet rs, int rm)
//        ... 값을 직접 지정하여 코드를 작성할 수도 있지만 resultSet을 엔터티클래스에 세팅해서 값을 바로 가져올 수 있다.

        return template.query(sql, (rs, n) -> new Board(rs)); //새로운 내용 추가해서 리턴하기
        }

    @Override
    public Board findOne(int boardNo) {
        String selectSql = "SELECT *, view_count + 1 AS updated_view_count FROM tbl_board WHERE board_no = ?";
        String updateSql = "UPDATE tbl_board SET view_count = view_count + 1 WHERE board_no = ?";

        template.update(updateSql, boardNo);
        return template.queryForObject(selectSql, (rs, n) -> new Board(rs), boardNo);
    }

    @Override
    public boolean save(Board board) { //저장 되는지 불린값으로 확인

            String sql = "INSERT INTO tbl_board " +
                    "(title, content, writer)" +
                    "VALUES(?,?,?)";

            return template.update(sql,
                    board.getTitle(),
                    board.getContent(),
                    board.getWriter()) == 1;
        }

    @Override
    public boolean delete(int boardNo) {
        String sql = "DELETE FROM tbl_board WHERE board_no = ?";
        return template.update(sql, boardNo) == 1;
    }
}
