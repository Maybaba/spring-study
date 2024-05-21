package com.study.springstudy.database.chap02;

import com.study.springstudy.database.chap01.Person;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper //구현클래스를 작성하지 않아도 되고 바로 sql을 xml로 만든다.
//자바, jsp 파일 제외 모두 resources 디렉에 작성
public interface PersonMapper {

    //사람 전체조회
    List<Person> findAll();

    //사람 개별조회
    Person findOne(long id);

    //사람 등록
    boolean save(Person person);

    //사람 수정
    boolean update(Person newPerson);

    //사람 삭제
    boolean delete(long id);

    //사람들의 이름만 조회
    List<String> findNames();

    //사람들의 총 숫자만 조회
    int count();



}
