package com.study.springstudy.database.chap01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class SpringJdbcTest {

    //각 테스트 전에 공통으로 실행할 코드
    @BeforeEach
    void bulkInsert() {
        for (int i = 0; i <20 ; i++) {
            Person p = new Person(i + 1111, "test person" + i, 22);
            springJdbc.save(p);
        }
    }

    @Autowired
    SpringJdbc springJdbc;

    // 단위 테스트 프레임워크 : JUnit5
    // 테스트 == 단언 (Assertion)
    @Test
    @DisplayName("사람의 정보를 입력하면 데이터베이스에 반드시 저장되어야 한다.")
    void saveTest() {
        // gwt 패턴
        // given : 테스트에 주어질 데이터
        Person p = new Person(1, "일일", 111);

        // when : 테스트 상황
        int result = springJdbc.save(p);

        // then : 테스트 결과 단언
        assertEquals(1, result);
    }



    @Test
    @DisplayName("아이디가 주어지면 해당 아이디의 사람정보가" +
            " 데이터베이스로부터 삭제되어야 한다.")
    void deleteTest() {
        //given
        int id = 77;
        //when
        boolean flag = springJdbc.delete(id);
        //then
        assertTrue(flag);
    }


    @Test
    @DisplayName("새로운 이름과 나이를 전달하면 사람의 정보가 " +
            "데이터베이스에서 수정된다." )
    void modifyTest() {
        //given
        Person person
                = new Person(77, "팔팔이", 8);
        //when
        boolean flag = springJdbc.update(person);
        //then
        assertTrue(flag);
    }


    @Test
    @DisplayName("사람 정보를 전체조회하면 결과" +
            " 건수는 4건이며, 첫번째 사람의 이름은 \'팔팔이\'다.")
    void findAllTest() {
        //given

        //when
        List<Person> people = springJdbc.findAll();
        //then
        people.forEach(System.out::println);

        assertEquals(4, people.size());
        assertEquals("팔팔이", people.get(0).getPersonName());
    }



    @Test
    @DisplayName("사람 정보를 아이디로 단일조회시 아이디가 300인 " +
            "사람의 이름은 삼백이이고 나이는 34이다.")
    void findOneTest() {
        //given
        int id = 300;
        //when
        Person person = springJdbc.findOne(id);
        //then
        System.out.println("person = " + person);

        assertNotNull(person);
        assertEquals("삼백이", person.getPersonName());
        assertEquals(34, person.getPersonAge());
    }




}


//package com.study.springstudy.database.chap01;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
//class SpringJdbcTest {
//
//    @Autowired //test시 default로 필드 주입
//    SpringJdbc springJdbc;
//
//    //기능 단위 테스트 framework JUnit5
//    // test = assertion (단언)
//    @Test
//    @DisplayName("사람의 정보를 입력하면 데이터베이스에 반드시 저장되어야 한다. ")
//    void saveTest() {
//        //gwt 패턴
//        //given : 테스트에 주어질 데이터
//        Person p = new Person(333, "육", 444);
//
//        //when : 테스트 상황
//        int result = springJdbc.save(p);
//
//        //then : 테스트 결과 단언
//        assertEquals(1, result);
//    }
//
//    @Test
//    @DisplayName("아이디가 주어지면 해당 아이디의 사람정보가" +
//    "데이터베이스로부터 삭제되어야 한다. ")
//    void deleteTest() {
//        //given
//        int id = 6;
//        //when
//        boolean flag = springJdbc.delete(id);
//        //then
//        assertTrue(flag);
//    }
//    @Test
//    @DisplayName("새로운 이름과 나이를 전달하면 그 아이디의 정보가 데이터베이스에서 수정된다. ")
//    void modifyTest() {
//        //given
//        Person ps = new Person(33, "삼삼이", 3);
//        //when
//        boolean flag = springJdbc.delete(Person ps);
//
//        //then
//        assertTrue(flag);
//    }
//
//    @Test
//    @DisplayName("사람 정보를 전체조회하면 결과 건수는 4건이며 첫번째 사람의 이름은 '부' 이다.")
//    void findAllTest() {
//        //given
//
//        //when
//        List<Person> people = springJdbc.finalAll();
//
//        //then
//        people.forEach((System.out::println));
//
//        assertEquals(2, people.size());
//        assertEquals("바부", people.get(0).getPersonName());
//    }
//    @Test
//    @DisplayName("사람 정보를 아이디로 단일조회시 아이디가 1인 사람의 이름은 '부' 이다. 나이는 11살이다. ")
//    void findIneTest() {
//        //given
//        int id = 1;
//
//        //when
//        Person person = springJdbc.findOne(id);
//
//        //then
//        System.out.println("person = " + person);
//
//        assertNotNull(person);
//        assertEquals("바부", person.getPersonName());
//        assertEquals(11, person.getPersonAge());
//    }
//
//}