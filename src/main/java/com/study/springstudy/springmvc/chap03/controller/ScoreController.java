package com.study.springstudy.springmvc.chap03.controller;

import com.study.springstudy.springmvc.chap03.dto.ScorePostDto;
import com.study.springstudy.springmvc.chap03.entity.Score;
import com.study.springstudy.springmvc.chap03.repository.ScoreJdbcRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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
@RequestMapping("/score") //score로 시작하는 요청
public class ScoreController {

    //db 처리 전담하는 의존 객체 설정 ; 의존 시 추상적인 인터페이스에 의존한다.
    private ScoreJdbcRepository rp = new ScoreJdbcRepository();

    //get
    @GetMapping("/list")
    public String list(Model model) {
        System.out.println("/score/list : GET");

        //저장된 db 조회하기
        List<Score> scoreList = rp.findAll();
        //수송객체에 담아서 화면에 그릴 수 있도록 model 에 전달
        model.addAttribute("sList", scoreList);

        return "/score/score-list";
    }

    //POST
    @PostMapping("/register")
    public String register(ScorePostDto dto) { //전달하는 객체가 많으니까 DTO 클래스를 따로 만들어 커맨드객체 처리
        System.out.println("/score/register : POST");
        System.out.println("dto = " + dto);

        //데이터베이스저장 (score 에 점수계산 및 wrap 위임)
        Score score = new Score(dto);
        //db 저장 위임
        rp.save(score);

        //다시 조회로 돌아가야 저장된 데이터를 볼 수 있음
        //포워딩이 아닌 리다이렉트로 재요청을 넣어야 새롭게 디비 조회 가능
        return "redirect:/score/list";

    }
    @PostMapping("/remove")
    public String remove() {
        System.out.println("/score/remove : POST");
        return "";
    }
    @GetMapping("/detail")
    public String detail(long stuNum, Model model) {
        Score scoreDetail = rp.findOne(stuNum);
        model.addAttribute("s", scoreDetail);
        System.out.println("/score/detail : GET");

        //1. 상세조회를 원하는 학번을 읽기
        //2. DB 에 상세 조회 요청
        //3 . DB 에서 조회한 회원정보 JSP에게 전달
        return "score/score-detail";
    }


}
