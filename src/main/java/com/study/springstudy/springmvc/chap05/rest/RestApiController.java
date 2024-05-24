package com.study.springstudy.springmvc.chap05.rest;

import com.study.springstudy.database.chap01.Person;
import com.study.springstudy.springmvc.chap04.common.Page;
import com.study.springstudy.springmvc.chap04.common.PageMaker;
import com.study.springstudy.springmvc.chap04.common.Search;
import com.study.springstudy.springmvc.chap04.dto.BoardListResponseDto;
import com.study.springstudy.springmvc.chap04.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequestMapping("/rest")
@RestController
@RequiredArgsConstructor
//@ResponseBody //class 위에 붙이면 requestmapping처럼 한번만 어노테이션을 선언하면 모두 적용된다.
public class RestApiController {

    private final BoardService boardService;

    @GetMapping("/hello")
//    @ResponseBody // 서버가 클라이언트에게 순수한 데이터를 전달할 때 사용
    public String hello() {
        //...

        return "안녕안녕안녕안녕";
    }

    @GetMapping("/hobby")
//    @ResponseBody
    public List<String> hobby() {
        List<String> hobbies = List.of("태권도", "장기", "댄스");
        return hobbies;
    }

    @GetMapping(value = "/person", produces = "application/json") //produces = "application/json" 이건 자동으로 들어간다.
//    @ResponseBody
    public Person person() {
        Person person = new Person(100, "자두", 9);
        return person;

    }

    @GetMapping("/board")
    public Map<String, Object> board() {

        List<BoardListResponseDto> list = boardService.findList(new Search());

        HashMap<String, Object> map = new HashMap<>();
        map.put("articles", list);
        map.put("page", new PageMaker(new Page(), boardService.getCount(new Search())));

        return map;

    }

    /*
         RestController에서 리턴타입을 ResponseEntity를 쓰는 이유

         - 클라이언트에게 응답할 때는 메시지 바디안에 들어 있는 데이터도 중요하지만
         - 상태코드와 헤더정보를 포함해야 한다
         - 근데 일반 리턴타입은 상태코드와 헤더정보를 전송하기 어렵다
     */

    @GetMapping("/people")
    public ResponseEntity<?> people() {
        Person p1 = new Person(111, "딸기공주", 30);
        Person p2 = new Person(222, "초코초코", 20);
        Person p3 = new Person(333, "냠냠냠", 40);

        List<Person> people = List.of(p1, p2, p3);

        HttpHeaders headers = new HttpHeaders();
        headers.add("my-pet", "cat");
        headers.add("money", "300");

        return ResponseEntity
//                .badRequest()
//                .internalServerError()
//                .status(404) //응답신호(번호) 커스텀 가능 ㅋㅋ
                .ok()
                .headers(headers)
                .body(people); //담고 싶은 정보
    }
    @GetMapping("/bmi")
    public ResponseEntity<?> bmi (
            @RequestParam(required = false) Double cm,
            @RequestParam(required = false) Double kg
    ) {

        if(cm == null || kg == null) {
            return ResponseEntity.badRequest().body("키와 몸무게를 입력해주세요");
        }

        double m = cm / 100;
        double bmi = kg / (m * m);

        return ResponseEntity.ok().body(bmi);

    }

}
