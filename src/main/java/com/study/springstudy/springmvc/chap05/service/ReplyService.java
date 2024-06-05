package com.study.springstudy.springmvc.chap05.service;

import com.study.springstudy.springmvc.LoginUtil;
import com.study.springstudy.springmvc.chap04.common.Page;
import com.study.springstudy.springmvc.chap04.common.PageMaker;
import com.study.springstudy.springmvc.chap05.dto.response.ReplyListDto;
import com.study.springstudy.springmvc.chap05.dto.repuest.ReplyModifyDto;
import com.study.springstudy.springmvc.chap05.dto.response.ReplyDetailDto;
import com.study.springstudy.springmvc.chap05.dto.repuest.ReplyPostDto;
import com.study.springstudy.springmvc.chap05.entity.Reply;
import com.study.springstudy.springmvc.chap05.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyService {

    private final ReplyMapper replyMapper;

    //댓글 목록 전체조회
    public ReplyListDto getReplies(long boardNo, Page page) {
        List<Reply> replies = replyMapper.findAll(boardNo, page);

        List<ReplyDetailDto> dtoList = replies.stream()
                .map(r -> new ReplyDetailDto(r))
                .collect(Collectors.toList());

        return ReplyListDto.builder()
                .replies(dtoList)
                .pageInfo(new PageMaker(page, replyMapper.count(boardNo)))
                .build();
    }

    //댓글 입력
    public boolean register(ReplyPostDto dto, HttpSession session) {

        Reply reply = Reply.builder()
                .replyText(dto.getText())
                .writer(dto.getAuthor())
                .boardNo(dto.getBno())
                .account(LoginUtil.getLoggedInUserAccount(session)) //이거 오늘 벨로그에 넣기
//                .account(session.getId())
                .replyWriter(dto.getAuthor())
                .build()
                ;

        //Reply r = new Reply();
        //r.set ... 이 과정을 위의 코드로 간략화 시킴

        boolean flag = replyMapper.save(reply);
        if(flag) log.info("댓글 등록 성공 ! -{}", dto);
        else log.warn("댓글 등록 실패");

        return flag;
//       return replyMapper.save(reply);

    }


    // 댓글 수정
    public ReplyListDto modify(ReplyModifyDto dto) {

        replyMapper.modify(dto.toEntity());

        return getReplies(dto.getBno(), new Page(1, 10));
    }

    //댓글 삭제
    @Transactional
    public ReplyListDto remove(long rno) {
        // 댓글 번호로 원본 글번호 찾기
        long bno = replyMapper.findBno(rno);
        boolean flag = replyMapper.delete(rno);
        // 삭제 된 후 줄어든 새로운 목록을 리턴
        return flag ? getReplies(bno, new Page(1, 10)) : null;
    }

//    @Transactional
//    public List<ReplyDetailDto> remove(long rno) {
//        long bno = replyMapper.findBno(rno);
//        //삭제 후 삭제된 목록을 리턴
//        boolean flag = replyMapper.delete(rno);
//        //댓글 번호로 원본 글번호 찾아서 bno에 할당하기
//        return flag ? getReplies(bno, new Page(1, 10)) : Collections.emptyList();
//    }
}
