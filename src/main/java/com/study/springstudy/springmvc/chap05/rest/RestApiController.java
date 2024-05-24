package com.study.springstudy.springmvc.chap05.rest;

import com.study.springstudy.database.chap01.Person;
import com.study.springstudy.springmvc.chap04.common.Page;
import com.study.springstudy.springmvc.chap04.common.PageMaker;
import com.study.springstudy.springmvc.chap04.common.Search;
import com.study.springstudy.springmvc.chap04.dto.BoardListResponseDto;
import com.study.springstudy.springmvc.chap04.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
