package com.study.springstudy.springmvc.chap05.api;

import com.study.springstudy.springmvc.chap04.common.Page;
import com.study.springstudy.springmvc.chap05.dto.ReplyListDto;
import com.study.springstudy.springmvc.chap05.dto.response.ReplyDetailDto;
import com.study.springstudy.springmvc.chap05.dto.repuest.ReplyPostDto;
import com.study.springstudy.springmvc.chap05.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/replies")
@Slf4j
@CrossOrigin //CORS 정책 허용범위 설정
public class ReplyApiController {

    private final ReplyService replyService;

    //댓글 목록 조회 요청        /?bno=원본글번호
    //URL : /api/v1/replies/원본글번호/page/페이지번호 - GET -> 목록조회
    // @PathVariable : URL에 붙어있는 변수 값을 읽는 어노테이션!!!!!!
    @GetMapping("/{bno}/page/{pageNo}")
    public ResponseEntity<?> list(
            @PathVariable int pageNo,
            @PathVariable("bno") long bno) {

        if(bno == 0) {
            String message = "글 번호는 0번이 될 수 없습니다 !!!!! warn!!!! ";

            log.warn(message);

            return ResponseEntity
                    .badRequest()
                    .body(message);
        }

        log.info("/api/v1/replies/{} : GET", bno);

        ReplyListDto replies = replyService.getReplies(bno, new Page(pageNo, 5));
//        log.debug("first reply : {}", replies.get(0)); 목록 조회 요청 - 댓글 없는 경우 에러나기때무네...
//        try {
//
//        } catch (Exception e) {
//
//        }
        return ResponseEntity
                .ok()
                .body(replies);
    }

    //댓글 생성 요청
    //@Validated 검증 요청 자카르타에서 진행
    @PostMapping
    public ResponseEntity<?> posts(@Validated @RequestBody ReplyPostDto dto //@RequestBody : 통일된 데이터 양식을 JSON으로 받아서 파싱한다.
    , BindingResult result //입력값 검증 결과 데이터를 갖고 있는 객체
    ) {

        log.info("/api/v1/replies : POST");
        log.debug("parameter: {}", dto);

        log.debug(result.toString());

        if(result.hasErrors()) {

            Map<String, String> errors = makeValidationMessageMap(result);

            return ResponseEntity
                    .badRequest()
                    .body(errors);
        }

        boolean flag = replyService.register(dto);

        if(!flag) return ResponseEntity.internalServerError().body(" !!! 댓글 등록 실패 👻");

        return ResponseEntity
                .ok()
                .body(replyService.getReplies(dto.getBno(), new Page(1, 5)));
    }

    private Map<String, String> makeValidationMessageMap(BindingResult result) {

        Map<String, String> errors = new HashMap<>();

        // 에러정보가 모여있는 리스트
        List<FieldError> fieldErrors = result.getFieldErrors();

        for (FieldError error : fieldErrors) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return errors;
    }
    //삭제 처리 요청 restAPI : DELETE http 요청 메시지로 구분할 수 있다 .
    @DeleteMapping("/{rno}")
    public ResponseEntity<?> delete(@PathVariable long rno) {

        ReplyListDto dtoList = replyService.remove(rno);

        return ResponseEntity
                .ok()
                .body(dtoList); //삭제 성공했으면 삭제 성공 매시지로 삭제된 리스트 리턴
    }
}
