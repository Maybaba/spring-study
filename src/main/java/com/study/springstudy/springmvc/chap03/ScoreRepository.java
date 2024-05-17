package com.study.springstudy.springmvc.chap03;

import com.study.springstudy.springmvc.chap03.entity.Score;

import java.util.List;

// 적당한 저장소에 CRUD 하기 : 특정 저장소에 의존하면 안되므로!
public interface ScoreRepository {

    //저장소에 데이터 추가하기
    //1대1로 매칭되는 클래스 : 엔터티 클래스 혿은 도메인 클래스 -> entity.Score
    boolean save(Score score);  //어떤 방식으로 ? 어쨌든 save 함수만 만들어.

    //저장소에서 데이터 전체 조회하기
    List<Score> findAll(); //걍 리스트로 갖다 줘. 뿌엥.

    //저장소에서 데이터 단일 개별조회하기
    Score findOne(long stuNum); //내가 학번 조회를 해볼게!

    // 데이터 석차 조회하기
    int[] findRankbyOne(long stuNum); //내가 해당 학생의 정보를 조회해서 계산쓰해볼게~


    //저장소에서 데이터 삭제하기
    List<Score> Delete(long stuNum); //걍 우선 만들어. 딜리트.




}
