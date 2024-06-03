package com.study.springstudy.springmvc.chap05.entity;

import lombok.*;
import org.checkerframework.checker.units.qual.A;

import java.time.LocalDateTime;

@Getter
@Setter @ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ViewLog {

    private long id;
    private String account;
    private long boardNo;
    private LocalDateTime viewTime;

}
