package com.study.springstudy.springmvc.chap05.service;

import com.study.springstudy.springmvc.chap05.mapper.ReactionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReactionService {

    private final ReactionMapper reactionMapper;

    //반응 남김기면 해당 값 추가하기

    //반응을 이미 했는데 다시 dislike로 바꾸기


}
