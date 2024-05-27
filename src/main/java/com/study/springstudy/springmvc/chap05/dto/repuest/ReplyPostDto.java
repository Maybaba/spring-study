package com.study.springstudy.springmvc.chap05.dto.repuest;

import lombok.*;
import javax.validation.constraints.*;

@Getter @Setter
@ToString
@EqualsAndHashCode
@Builder
public class ReplyPostDto {

    @NotBlank // : 아래 둘 다 안됨
    // @NotNull : null 허용 안됨 @NotEmpty : null 가능하나 빈문자열 안됨
    @Size(min = 1, max = 300)
    private String text; //댓글내용
    @NotBlank
    @Size(min = 2, max = 8)
    private String author;
    @NotNull
    private Long bno;//원본 글번호 받기

}
