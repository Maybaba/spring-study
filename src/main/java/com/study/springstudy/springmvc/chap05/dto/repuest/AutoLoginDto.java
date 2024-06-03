package com.study.springstudy.springmvc.chap05.dto.repuest;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Locale;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutoLoginDto {

    private String sessionId; //자동로그인 쿠키값
    private LocalDateTime limitTime;
    private  String account; //바꿀 계정명
}
