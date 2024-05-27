package com.study.springstudy.springmvc.chap05.service;

import com.study.springstudy.springmvc.chap05.dto.response.ReplyDetailDto;
import com.study.springstudy.springmvc.chap05.dto.repuest.ReplyPostDto;
import com.study.springstudy.springmvc.chap05.entity.Reply;
import com.study.springstudy.springmvc.chap05.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyService {

    private final ReplyMapper replyMapper;

    //댓글 목록 전체조회
    public List<ReplyDetailDto> getReplies(long boardNo) {
        List<Reply> replies = replyMapper.findAll(boardNo);
        return replies
                .stream()
                .map(reply -> new ReplyDetailDto(reply))
                .collect(Collectors.toList());

    }

    //댓글 입력
    public boolean register(ReplyPostDto dto) {

        Reply reply = Reply.builder()
                .replyText(dto.getText())
                .replyWriter(dto.getAuthor())
                .boardNo(dto.getBno())
                .build();

        //Reply r = new Reply();
        //r.set ... 이 과정을 위의 코드로 간략화 시킴

        boolean flag = replyMapper.save(reply);
        if(flag) log.info("댓글 등록 성공 ! -{}", dto);
        else log.warn("댓글 등록 실패");

        return flag;
//       return replyMapper.save(reply);

    }


    //댓글 수정
    public void modify() {

    }

    //댓글 삭제
    @Transactional
    public List<ReplyDetailDto> remove(long rno) {
        long bno = replyMapper.findBno(rno);
        //삭제 후 삭제된 목록을 리턴
        boolean flag = replyMapper.delete(rno);
        //댓글 번호로 원본 글번호 찾아서 bno에 할당하기
        return flag ? getReplies(bno) : Collections.emptyList();
    }
}
