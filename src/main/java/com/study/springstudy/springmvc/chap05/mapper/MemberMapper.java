package com.study.springstudy.springmvc.chap05.mapper;

import com.study.springstudy.springmvc.chap05.dto.repuest.AutoLoginDto;
import com.study.springstudy.springmvc.chap05.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {

    //회원가입
    boolean save(Member member);

    //회원정보 개별 조회
    Member findOne(String account);

    /**
     *
     * @param type - 어떤걸 중복검사할지
     * @param keyword - 중복검사할 실제값
     * @return - 중복이면 true 아니면 false
     */

    //중복확인(아이디, 이메일)
    boolean existsById(      @Param("type") String type,
                            @Param("keyword") String keyword);


    //자동로그인 쿠키값
    void updateAutoLogin(AutoLoginDto dto);

    //세션 아이디로 회원정보 조회
    Member findMemberBySessionId(String sessionId);

}
