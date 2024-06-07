package com.study.springstudy.springmvc.chap03.controller;

import com.study.springstudy.springmvc.chap03.dto.ScoreDetailResponseDto;
import com.study.springstudy.springmvc.chap03.dto.ScoreListResponseDto;
import com.study.springstudy.springmvc.chap03.dto.ScoreModifyDto;
import com.study.springstudy.springmvc.chap03.dto.ScorePostDto;
import com.study.springstudy.springmvc.chap03.entity.Score;
import com.study.springstudy.springmvc.chap03.repository.*;
import com.study.springstudy.springmvc.chap03.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

   /*
    # 요청 URL
    1. 학생 성적정보 등록화면을 보여주고 및 성적정보 목록조회 처리
    - /score/list : GET

    2. 성적 정보 등록 처리 요청
    - /score/register : POST

    3. 성적정보 삭제 요청
    - /score/remove : POST

    4. 성적정보 상세 조회 요청
    - /score/detail : GET
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/score") //score로 시작하는 요청
public class ScoreController {

    private final ScoreSpringJdbcRepository rp;
    private final ScoreService service;

    //get
    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "num") String sort, Model model) {  //sort param이 없을경우 기본값 설정
        System.out.println("/score/list : GET");


//        List<Score> scoreList = rp.findAll(sort);

        //저장된 dto db 조회하기
        List<ScoreListResponseDto> dtos = service.getList(sort);
        model.addAttribute("sList", dtos);

        return "score/score-list";

    }

    //POST
    @PostMapping("/register")
    public String register(ScorePostDto dto) { //전달하는 객체가 많으니까 DTO 클래스를 따로 만들어 커맨드객체 처리
        System.out.println("/score/register : POST");
        System.out.println("dto = " + dto);

        service.insert(dto);

        return "redirect:/score/list";

    }
    @GetMapping("/remove") // post -> get으로 변경
    public String remove(@RequestParam("sn") long stuNum) {
        System.out.println("/score/remove : GET");
        service.deleteScore(stuNum);
        //리턴 결과에 따라 삭제가 끝나면 리스트 리다이렉트
        return "redirect:/score/list";
    }

    @GetMapping("/detail")
    public String detail(long stuNum, Model model) {
        //1. 상세조회를 원하는 학번을 읽기
        Score score = rp.findOne(stuNum);

        //2. DB 에 상세 조회 요청
        model.addAttribute("s", score);
        System.out.println("/score/detail : GET");
        /*
        int[] result = rp.findRankbyOne(stuNum);
//        model.addAttribute("rank", result[0]);
//        model.addAttribute("count", result[1]);

        ScoreDetailResponseDto sdto = service.retrieve(stuNum);

        model.addAttribute("s",sdto);

         */

        // 1. 상세조회를 원하는 학번을 읽기
        // 2. DB에 상세조회 요청
        // 3. DB에서 조회한 회원정보 JSP에게 전달
        // 4. rank 조회
        ScoreDetailResponseDto dto = service.retrieve(stuNum);

        model.addAttribute("s", dto);

        return "score/score-detail";
    }

    //수정 화면 열기 요청
    @GetMapping("/modify")
    public String modify(long stuNum, Model model) {
        ScoreDetailResponseDto dto = service.retrieve(stuNum);
        model.addAttribute("s", dto);
        return "score/score-modify";
    }


    //수정 데이터 반영 요청
    @PostMapping("/modify")
    public String modifySave(ScoreModifyDto dto) {
        //수정을 원하는 새로운 데이터 읽기
        System.out.println("dto = " + dto);
        //서비스에게 수정 위임
        service.update(dto);

        return "redirect:/score/detail?stuNum=" + dto.getStuNum();
    }


}
