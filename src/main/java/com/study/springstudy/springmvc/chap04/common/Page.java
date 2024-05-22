package com.study.springstudy.springmvc.chap04.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Page {

    private int pageNo; // 클라이언트가 요청한 페이지 번호
    private int amount; // 클라이언트가 요청한 게시물 목록

    public int getPageStart() { //필드가 없어도 getter로 착각한다. 그래서 이 이름으로 사용할 수 있다 ?

        /*
        만약 한 페이지에 게시물을 10개씩 렌더링한다면
        1페이지 -> LIMIT 0, 10
        2 -> 10, 10
        3 -> 20, 10

        6개씩 렌더링
        1 -> 0, 6
        6, 6
        12, 6
        ...

        n개씩

        1 - 0, n
        2    n, n
        3    2n, n
        4    3n, n
        page    (page-1)n, n

         */
        return (this.pageNo -1) * this.amount;
    }

}
