package com.study.springstudy.springmvc.chap05.entity;

/*
-- 회원 관리 테이블
CREATE TABLE tbl_member (
                            account VARCHAR(50),
                            password VARCHAR(150) NOT NULL, #암호화를 위해서 길게 저장
                            name VARCHAR(50) NOT NULL,
                            email VARCHAR(100) NOT NULL UNIQUE,
                            auth VARCHAR(20) DEFAULT 'COMMON',
                            reg_date DATETIME DEFAULT current_timestamp,
                            CONSTRAINT pk_member PRIMARY KEY (account) #계정명
);
 */
import lombok.*;

import java.time.LocalDateTime;

@Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    private String account;
    @Setter //필요에 의해서만 setter를 만드는 게 좋다. 비밀번호 암호화하여 저장할때가 그 예시이다.
    private String password;
    private String name;
    private String email;
    private Auth auth;
    private LocalDateTime regDate;
    private String sessionId;
    private LocalDateTime limitTime;

}
