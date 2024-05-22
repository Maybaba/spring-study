package com.study.springstudy.springmvc.chap04.controller;


import com.study.springstudy.springmvc.chap04.common.Page;
import com.study.springstudy.springmvc.chap04.common.PageMaker;
import com.study.springstudy.springmvc.chap04.dto.BoardDetailResponseDto;
import com.study.springstudy.springmvc.chap04.dto.BoardListResponseDto;
import com.study.springstudy.springmvc.chap04.dto.BoardPostDto;

import com.study.springstudy.springmvc.chap04.service.BoardService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
private final BoardService service;

    //1. 목록조회요청(/board/list : GET)
    @GetMapping("/list")
    public String list(Page page, Model model) {  //@RequestParam(defaultValue = "num") String sort, Model model
        System.out.println("/board/list : GET");

        // 서비스에게 조회 요청 위임
        List<BoardListResponseDto> bList = service.findList(page);
        //페이지 정보를 생성하여 JSP에게 전송 (다음으로 넘어가는 버튼 구현하려고.)
        PageMaker maker = new PageMaker(page, service.getCount());

        // 3. JSP파일에 해당 목록데이터를 보냄
        model.addAttribute("page", page);
        model.addAttribute( "bList", bList);
        model.addAttribute( "maker", maker);

        return "board/list";
    }

    //2. 글쓰기 양식 화면 요청 (/board/write : GET)
    @GetMapping("/write")
    public String textView() {
        System.out.println("/board/write");

        return "/board/write";
    }

    //3. 게시글 등록 요청 (/board/write : POST)
    // -> 목록조회 요청 리다이렉션
    @PostMapping("/write")
    public String write(BoardPostDto dto) {
        System.out.println("/board/write POST! ");

        // 1. 브라우저가 전달한 게시글 내용 읽기
        System.out.println("dto = " + dto);

        service.insert(dto);

        return "redirect:/board/list";
    }

    //4. 게시글 삭제요청 (/board/delete : GET)
    @GetMapping("/delete")
    public String delete(@RequestParam("bno") int bno) {
        System.out.println("/board/delete : GET");

        service.remove(bno);

        return "redirect:/board/list";
    }


    //5. 게시글 상세 조회 (/board/detail : GET)
    @GetMapping("/detail")
    public String detail(@RequestParam("bno") int bno, Model model) {
        System.out.println("/board/detail : GET");

        // 1. 상세조회하고 싶은 글번호를 읽기
        System.out.println("bno = " + bno);

        // 2. 데이터베이스로부터 해당 글번호 데이터 조회하기
        BoardDetailResponseDto dto = service.detail(bno);

        // 3. JSP파일에 조회한 데이터 보내기
        model.addAttribute("bbb", dto);

        return "/board/detail";
    }


}
