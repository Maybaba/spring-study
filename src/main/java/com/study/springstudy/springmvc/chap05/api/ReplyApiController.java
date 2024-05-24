package com.study.springstudy.springmvc.chap05.api;

import com.study.springstudy.springmvc.chap05.dto.ReplyDetailDto;
import com.study.springstudy.springmvc.chap05.dto.repuest.ReplyPostDto;
import com.study.springstudy.springmvc.chap05.entity.Reply;
import com.study.springstudy.springmvc.chap05.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/replies")
@Slf4j
public class ReplyApiController {

    private final ReplyService replyService;

    //댓글 목록 조회 요청        /?bno=원본글번호
    //URL : /api/v1/replies/원본글번호 - GET -> 목록조회
    // @PathVariable : URL에 붙어있는 변수 값을 읽는 어노테이션
    @GetMapping("/{bno}")
    public ResponseEntity<?> list(@PathVariable("bno") long bno) {

        if(bno == 0) {
            String message = "글 번호는 0번이 될 수 없습니다 !!!!! warn!!!! ";

            log.warn(message);

            return ResponseEntity
                    .badRequest()
                    .body(message);
        }

        log.info("/api/v1/replies/{} : GET", bno);

        List<ReplyDetailDto> replies = replyService.getReplies(bno);
        log.debug("first reply : {}", replies.get(0));

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
    @PostMapping
    public ResponseEntity<?> posts(@RequestBody ReplyPostDto dto) { //@RequestBody : 통일된 데이터 양식을 JSON으로 받아서 파싱한다.
        log.info("/api/v1/replies : POST");
        log.debug("parameter: {}", dto);

        boolean flag = replyService.register(dto);

        if(!flag) return ResponseEntity.internalServerError().body(" !!! 댓글 등록 실패 👻");

        return ResponseEntity
                .ok()
                .body(replyService.getReplies(dto.getBno()));
    }
}
