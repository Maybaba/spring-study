package com.study.springstudy.springmvc.chap04.controller;

import com.study.springstudy.springmvc.chap04.BoardRepository;
import com.study.springstudy.springmvc.chap04.dto.BoardListResponseDto;
import com.study.springstudy.springmvc.chap04.dto.BoardPostDto;
import com.study.springstudy.springmvc.chap04.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/board")

public class BoardController {
    private BoardRepository rp;
    @Autowired
    public BoardController (BoardRepository repo) {
        this.rp = repo;
    }

    //1. 목록조회요청(/board/list : GET)
    @GetMapping("/list")
    public String list(Model model) {  //@RequestParam(defaultValue = "num") String sort, Model model
        System.out.println("/board/list : GET");

        List<Board> boardList = rp.findAll();
        boardList.forEach(System.out::println); // 데이터 전달 로그 출력

        List<BoardListResponseDto> dtoList = new ArrayList<>();

        for (Board b : boardList) {
            BoardListResponseDto d = new BoardListResponseDto(b);
            dtoList.add(d);
        }
//
//        List<Object> dtoList = boardList.stream()
//                .map(b -> new BoardListResponseDto(b))
//                .collect(Collectors.toList());


        model.addAttribute("boardList", boardList);

        return "/board/list";
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
    public String saveText(BoardPostDto dto) { //전달하는 객체가 많으니까 DTO 클래스를 따로 만들어 커맨드객체 처리
        //dto에 게시판 값을 저장해야 하는디?
        System.out.println("/board/write : POST");
        System.out.println("board = " + dto);

        //데이터베이스저장
//        Board board = new Board(dto);
        Board b = dto.toEntity();
//        board.setBoardNo(dto.);

        //db 저장 위임
//        rp.save(board);
        rp.save(b);

        return "redirect:/board/list";
    }


    //4. 게시글 삭제요청 (/board/delete : GET)
    @GetMapping("/delete")
    public String delete(@RequestParam("bno") int bno) {
        System.out.println("/board/delete : GET");

        rp.delete(bno);

        return "redirect:/board/list";
    }


    //5. 게시글 상세 조회 (/board/detail : GET)
    @GetMapping("/detail")
    public String detail(@RequestParam("bno") int bno, Model model) {
        System.out.println("/board/detail : GET");

        Board board = rp.findOne(bno);
        System.out.println(board);

        model.addAttribute("b", board);

        return "/board/detail";
    }
}
