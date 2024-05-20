package com.study.springstudy.springmvc.chap03.dto;

import com.study.springstudy.springmvc.chap03.entity.Score;
import lombok.Getter;

@Getter
public class ScoreListResponseDto {
    private long stNum; //프레임워크 상에서 한글자 카멜케이스는 대문자변환이 되지 않는 경우가 있으므로 .. 한글자 sNum 은 지양
    private String maskingName; // 첫 글자 빼고 모두 별 처리
    private double average;
    private String grade;

    public ScoreListResponseDto (Score s) {
        this.stNum = s.getStuNum();
        this.maskingName = makeMaskingName(s.getStuName());
        this.average = s.getAverage();
        this.grade = s.getGrade().toString();
    }

    private String makeMaskingName(String stuName) {
        char firstLetter = stuName.charAt(0);
        String maskedName = "" + firstLetter; //char type => string type
        for (int i = 0; i <stuName.length() -1 ; i++) {
            maskedName += "*";
        }
        return maskedName;
    }
}


