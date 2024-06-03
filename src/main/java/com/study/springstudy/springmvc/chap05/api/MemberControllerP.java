package com.study.springstudy.springmvc.chap05.api;

import com.study.springstudy.springmvc.chap05.dto.repuest.LoginDto;
import com.study.springstudy.springmvc.chap05.dto.repuest.SignUpDto;
import com.study.springstudy.springmvc.chap05.service.LoginResult;
import com.study.springstudy.springmvc.chap05.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    public void signUp() {
        log.info("/members/sign-up GET : forwarding to sign-up.jsp");
        //return "members/sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(@Validated SignUpDto dto) { //클라이언트에서 뚫리면 서버에서도 막아야 한다.
        log.info("/members/sign-up POST ");
        log.debug("parameter : {}", dto);

        boolean flag = memberService.join(dto);
        return flag ? "redirect:/members/sign-in" : "redirect:/members/sign-up";
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

    // 로그인 양식 열기
    @GetMapping("/sign-in")
    public String signIn(HttpSession session
    , @RequestParam(required = false) String redirect) {
        //로그인을 한 사람이 이 요청을 보내면 돌려보낸다
//        LoginUserInfoDto login = (LoginUserInfoDto) session.getAttribute("login");

        //로그인 한 사람이면 객체에 데이터가 담긴 경우이고, 아니라면 담기지 않을 것이다.
//        if (LoginUtil.isLoggedIn(session)) {
//            return "redirect:/";
//        }

        session.setAttribute("redirect", redirect);

        log.info("/members/sign-in GET : forwarding to sign-in.jsp");
        return "members/sign-in";
    }

    // 로그인 요청 처리
    @PostMapping("/sign-in")
    public String signIn(LoginDto dto,
                         RedirectAttributes ra,
                         HttpServletRequest request,
                         HttpServletResponse response) {
        log.info("/members/sign-in POST");
        log.debug("parameter: {}", dto);

        // 세션 얻기
        HttpSession session = request.getSession();

        LoginResult result = memberService.authenticate(dto, session, response);

        // 로그인 검증 결과를 JSP에게 보내기
        // Redirect시에 Redirect된 페이지에 데이터를 보낼 때는
        // Model객체를 사용할 수 없음
        // 왜냐면 Model객체는 request객체를 사용하는데 해당 객체는
        // 한번의 요청이 끝나면 메모리에서 제거된다. 그러나 redirect는
        // 요청이 2번 발생하므로 다른 request객체를 jsp가 사용하게 됨

//        model.addAttribute("result", result); // (X)
        ra.addFlashAttribute("result", result);

        if (result == LoginResult.SUCCESS) {

            //세션에 리다이렉트 URL이 있다면
            String redirect = (String) session.getAttribute("redirect");
            if(redirect != null) {
                session.removeAttribute("redirect");
                return "redirect" + redirect;
            }

            return "redirect:/index"; // 로그인 성공시
        }

        return "redirect:/members/sign-in";
    }

    @GetMapping("/sign-out")
    public String signOut(HttpSession session
//                       ,HttpServletRequest request
                          ) {
        //세션에서 로그인 기록 삭제
//        HttpSession session = request.getSession();
        session.removeAttribute("login");
        //세션을 초기화(reset)
        session.invalidate();
        //홈으로 보내기
        return "redirect:/";
    }
}


