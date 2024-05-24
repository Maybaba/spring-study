package com.study.springstudy.springmvc.chap04.service;
import com.study.springstudy.springmvc.chap04.common.Search;
import com.study.springstudy.springmvc.chap04.dto.*;
import com.study.springstudy.springmvc.chap04.entity.Board;
import com.study.springstudy.springmvc.chap04.mapper.BoardMapper;
import com.study.springstudy.springmvc.chap05.entity.Reply;
import com.study.springstudy.springmvc.chap05.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    //service는 여러개의 매퍼를 사용해도 됨 : 여러 개의 의존을 주입 할 수 있음
    private final BoardMapper boardMapper;
    private final ReplyMapper replyMapper;

    // 목록 조회 요청 중간처리
    public List<BoardListResponseDto> findList(Search page) {
        List<BoardFindAllDto> boardList = boardMapper.findAll(page);

        List<BoardListResponseDto> dtoList = boardList.stream()
                .map(b -> new BoardListResponseDto(b))
                .collect(Collectors.toList());

        //검색 요청 중간 처리 게시물 리스트에서 각 게시물들의type을 확인하여
        //쿼리실행

        return dtoList;
    }

    // 등록 요청 중간처리
    public boolean insert(BoardPostDto dto) {
        Board b = dto.toEntity();
        return boardMapper.save(b);
    }

    // 삭제 요청 중간처리
    public boolean remove(int boardNo) {
        return boardMapper.delete(boardNo);
    }

    // 상세 조회 요청 중간처리 -> 댓글정보까지 같이 불러와서 jsp에 넣기
    public BoardDetailResponseDto detail(int bno) {
        Board b = boardMapper.findOne(bno);
        if (b != null) boardMapper.upViewCount(bno);
        //댓글 목록 조회
        List<Reply> replies = replyMapper.findAll(bno);

        BoardDetailResponseDto rsd = new BoardDetailResponseDto(b);
        rsd.setReplies(replies);

        return new BoardDetailResponseDto(b);
    }

    public int getCount(Search search) {
        //db
        return boardMapper.count(search);
    }



}