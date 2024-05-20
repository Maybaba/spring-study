package com.study.springstudy.springmvc.chap03.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ScoreModifyDto {
    private long stuNum;
    private String stuName;
    private int kor, eng, math;
}

