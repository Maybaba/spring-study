package com.study.springstudy.springmvc.chap05.api;

import com.study.springstudy.springmvc.chap05.dto.ReplyDetailDto;
import com.study.springstudy.springmvc.chap05.entity.Reply;
import com.study.springstudy.springmvc.chap05.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/replies")
public class ReplyApiController {

    private final ReplyService replyService;

    //댓글 목록 조회 요청        /?bno=원본글번호
    //URL : /api/v1/replies/원본글번호 - GET -> 목록조회
    // @PathVariable : URL에 붙어있는 변수 값을 읽는 어노테이션
    @GetMapping("/{bno}")
    public ResponseEntity<?> list(@PathVariable("bno") long bno) {

        List<ReplyDetailDto> replies = replyService.getReplies(bno);

        return ResponseEntity
                .ok()
                .body(replies);
    }
}
