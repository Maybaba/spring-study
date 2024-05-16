package com.study.springstudy.springmvc.chap02;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@Controller
@RequestMapping("/hw/*")
public class LoginController {
    /*
        1번요청: 로그인 폼 화면 열어주기
        - 요청 URL : /hw/s-login-form : GET
        - 포워딩 jsp파일 경로:  /WEB-INF/views/chap03/s-form.jsp
        - html form: 아이디랑 비번을 입력받으세요.
     */
    @GetMapping("/s-login-form")
    public String login() {
        return "mvc/s-form";
    }
    /*
        2번요청: 로그인 검증하기
        - 로그인 검증: 아이디를 grape111이라고 쓰고 비번을 ggg9999 라고 쓰면 성공
        - 요청 URL : /hw/s-login-check : POST
        - 포워딩 jsp파일 경로:  /WEB-INF/views/chap03/s-result.jsp
        - jsp에게 전달할 데이터: 로그인 성공여부, 아이디 없는경우, 비번 틀린경우
     */
    @PostMapping("/s-login-check")
    public String loginCheck(
            @RequestParam String id,
            @RequestParam String pw,
            Model model) {
        // 로그인 데이터 읽어오기
        //로그인 성공여부
        /*
        String message;
        if 아이디 맞을때
            if 비밀번호 맞을때
            message = 성공
            비번 틀리면
            message = 실패
          아이디 틀리면
          message = 실패

          model.add ~ ("message", message) -> jsp(조건문 없이) 에 메시지 전달하여 더 간결화 할 수 있다.
         */
        String message;
        if (id.equals("grape111") && pw.equals("ggg9999")) {
            message = "로그인 성공!";
            System.out.println("message = " + message);
            model.addAttribute("result", message);
            return "mvc/s-result";
        }
        //아이디 없는 경우
        else if (id.isEmpty()) {
            message = "아이디가 없습니다. ";
            System.out.println("message = " + message);
            model.addAttribute("result", message);
            return "mvc/s-result";
        }
        //비밀번호가 일치하지 않는 경우
        else
            message = "비밀번호가 일치하지 않습니다. ";
        System.out.println("message = " + message);
        model.addAttribute("result", message);
        return "mvc/s-result";
    }
}
