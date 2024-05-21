//package com.study.springstudy.database.chap02;
//
//import com.study.springstudy.database.chap01.Person;
//import com.sun.source.tree.AssertTree;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
//class PersonMapperTest {
//
//    @Autowired
//    PersonMapper mapper;
//
//    @Test
//    @DisplayName("매퍼로 사람 정보 등록하기")
//    void saveTest() {
//        //given
//        Person p = new Person(9998, "뫄뫄", 79);
//
//        //when
//        boolean flag = mapper.save(p);
//
//        //then
//        assertTrue(flag);
//    }
//
//    @Test
//    @DisplayName("아이디로 사람의 정보 삭제")
//    void delTest() {
//        //given
//        long id = 9998;
//
//        //when
//        boolean flag = mapper.delete(id);
//
//        //then
//        assertTrue(flag);
//    }
//
//    @Test
//    @DisplayName("아이디가 4인 사람의 정보를 수정한다")
//    void updateTest() {
//        //given
//        Person p = new Person(4, "뉴바뷰", 400);
//
//        //when
//        boolean flag = mapper.update(p);
//
//        //then
//        assertTrue(flag);
//    }
//
//    @Test
//    @DisplayName("전체조회하면 결과 건수가 2건이 되게 하기")
//    void findAllTest() {
//        //given
//
//        //when
//        List<Person> people = mapper.findAll();
//        //then
//        people.forEach(System.out::println);
//        assertEquals(2, people.size());
//    }
//
//    @Test
//    @DisplayName("id로 사람 정보를 개별 조회한다")
//    void findOneTest() {
//        //given
//        long id = 100;
//        //when
//        Person person = mapper.findOne(id);
//        //then
//        System.out.println("person = " + person);
//        assertEquals("부", person.getPersonName());
//    }
//
//    @Test
//    @DisplayName("사람수와 이름리스트를 조회")
//    void findNAmesT() {
//        //given
//
//        //when
//        List<String> names = mapper.findName();
//        int count = mapper.count();
//
//        //then
//        names.forEach(System.out::println);
//        System.out.println();
//        System.out.println("count = " + count);
//    }
//
//
//
//
//
//
//}