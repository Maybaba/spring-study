package com.study.springstudy.springmvc.chap05.mapper;

import com.study.springstudy.springmvc.chap04.common.Page;
import com.study.springstudy.springmvc.chap05.entity.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {

    //댓글 등록
    boolean save(Reply reply);

    //댓글 수정
    boolean modify(Reply reply);

    //댓글 삭제
    boolean delete(long replyNo);

    //특정 댓글 목록 조회 : mybatis에서 두개의 param을 쓰는 방법 : @Param을 사용해서 둘을 구분하기 위해 별칭을 쓴다.
    List<Reply> findAll(@Param("bno")long boardNo, @Param("p") Page page);

    //총 댓글 수 조회 : 데이터수를 최대한 크게 잡는다.
    int count(long boardNo);

    //댓글 번호로 원본글번호 찾기
    long findBno(long rno);

}

