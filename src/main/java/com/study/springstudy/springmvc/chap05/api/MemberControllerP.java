package com.study.springstudy.springmvc.chap05.api;

import com.study.springstudy.springmvc.chap05.dto.repuest.SignUpDto;
import com.study.springstudy.springmvc.chap05.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/members")
@Slf4j
@RequiredArgsConstructor
public class MemberControllerP {

    private final MemberService memberService;

    // 회원가입 양식 열기
    @GetMapping("/sign-up")
//    @ResponseBody
    // url이 jsp경로와 같으면 void로 설정해도 알아서 위에꺼 가져와서 열기 가능
    public void signUp(){
        log.info("/members/sign-up GET : forwarding to sign-up.jsp");
        //return "members/sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(@Validated SignUpDto dto) { //클라이언트에서 뚫리면 서버에서도 막아야 한다.
        log.info("/members/sign-up POST ");
        log.debug("parameter : {}", dto);

        boolean flag = memberService.join(dto);
        return flag ? "redirect:/board/list" : "redirect:/members/sign-up";
    }

    //아이디, 이메일 중복검사 비동기 요청 처리
    @GetMapping("/check")
    @ResponseBody //rest 컨이 아니라 일반 컨이라 해당 어노테이션 필요
    public ResponseEntity<?> check(String type, String keyword) {
        boolean flag = memberService.checkIdentifier(type, keyword);
        log.debug("{}중복체크결과?{}", type, flag);
        return ResponseEntity
                .ok()
                .body(flag);
    }
}
