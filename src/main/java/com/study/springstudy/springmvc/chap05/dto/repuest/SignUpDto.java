package com.study.springstudy.springmvc.chap05.dto.repuest;

import com.study.springstudy.springmvc.chap05.entity.Member;
import lombok.*;
import org.checkerframework.checker.units.qual.A;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class SignUpDto {

    @NotBlank
    @Size(min = 4, max = 14)
    private String account;
    @NotBlank
//    @Size(min = 2, max = 6)
    private String password;
    @NotBlank
    @Size(min = 2, max = 6)
    private String name;
    @NotBlank
    @Email
    private String email;


    //retutn new Member로 작성하게 되면 생성자패턴에서 null 등을 꼭 넣어서 모든 값을 채워넣어 값을 생성해야 한다. . .
    //빌더 패턴 차이 찾아보기
    public Member toEntity() {
        return Member.builder().
                account(this.account)
                .password(this.password)
                .email(this.email)
                .name(this.name)
                .build();
    }
    //권한 같은 경우는 이미 존재하고 있는 걸 수정하는 경우임.

}
