package com.study.springstudy.springmvc.chap03.service;

/*
컨트롤러와 레포지토리 사이에 위치해야 중간 처리를 담당
ex) 트랜잭션 처리, 데이터 가공 처리
-의존관계
controller - service - repository
 */

import com.study.springstudy.springmvc.chap03.ScoreRepository;
import com.study.springstudy.springmvc.chap03.dto.ScoreListResponseDto;
import com.study.springstudy.springmvc.chap03.dto.ScorePostDto;
import com.study.springstudy.springmvc.chap03.entity.Score;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor //다 가공해서 가지고 와 -,.=
@Service //중간처리도 스프링에서 관리하는 객체가 된다.
public class ScoreService {

    private final ScoreRepository repo;

    //목록 조회 중간 처리
    //DB에서 조회한  성적정보는 민감한 정보를 모두 포함하고 있는데 이를 컨트롤러에 직접 넘기면
    // 불필요한 정보까지 화면으로 넘어갈 수 있기 때문에
    //숨길건 숨기고 뺄건 빼는 데이터 가공을 처리한다.

   public List<ScoreListResponseDto> getList(String sort) {
        List<Score> scoreList = repo.findAll(sort); //정제되지 않은 레포지토리
       return scoreList.stream()
               .map(s -> new ScoreListResponseDto(s))
               .collect(Collectors.toList());
    }
    //저장 중간처리
    public boolean insert(ScorePostDto dto) {
       return repo.save(new Score(dto));
    }

    //삭제 중간처리
    public boolean deleteScore(long stuNum) {
       return repo.delete(stuNum);
    }

    // 개별조회 중간처리
    public Score retrieve(long stuNum) {
        return repo.findOne(stuNum);
    }

}
