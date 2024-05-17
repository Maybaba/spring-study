package com.study.springstudy.database.chap01;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
@RequiredArgsConstructor //자동 생성자 주입
public class SpringJdbc {

    private final JdbcTemplate template;

    //INSERT
    public int save(Person person) {
        String sql = "INSERT INTO tbl_person VALUES (?, ?, ?)";
        return template.update(sql, person.getId(), person.getPersonName(), person.getPersonAge());
    }

    //DELETE
    public boolean delete(long id) {
        String sql = "DELETE FROM tbl_person WHERE id = ?";
        int result = template.update(sql, id);
        return result == 1;
    }

    //UPDATE
    public boolean update(Person newPerson, int flag) {
        //이름, 나이 수정
        String sql = "UPDATE tbl_person " + "SET person_name = ?, person_age = ? " +
                "WHERE id = ?";
        template.update(sql, newPerson.getPersonName(), newPerson.getPersonAge(), newPerson.getId());
        return flag == 1;
    }

    //SELECT : 다중행 조회
    public List<Person> finalAll() {
        String sql = "SELECT * FROM tbl_person"; // functional 붙어있어서 람다 사용거능
        //List<Person> people = template.query(sql, new RowMapper());// 빈 리스트 만들어 조건 리스트 만드는 코드를 함축한 스프링 메서드
        // 내부클래스 사용하지 않아도 돌아감
        return template.query(sql, (rs, rowNum) -> new Person(rs));
    }

    //내부 클래스
//    public static class PersonMapper implements RowMapper<Person>{
//
//        @Override
//        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
//
//            Person p = new Person();
//            p.setId(rs.getInt("id"));
//            p.setPersonName(rs.getString("person_name"));
//            p.setPersonAge(rs.getInt("person_age"));
//            return p;
//        }
//    }

    //SELECT : 단일행 조회
    public Person findOne(int id) {
        String sql = "SELECT * FROM tbl_person WHERE id = ? ";
        Person p = template.queryForObject(sql, (rs, n) -> new Person(rs), id);
        return p;
    }





}
