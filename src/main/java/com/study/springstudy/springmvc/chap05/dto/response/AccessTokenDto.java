package com.study.springstudy.springmvc.chap05.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter @ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccessTokenDto {
    @JsonProperty("access_token") // 이름 자동 변환이 되지 않아서 문서 참조햐여 이름 설정
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;


}
