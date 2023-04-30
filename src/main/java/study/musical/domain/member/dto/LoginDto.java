package study.musical.domain.member.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LoginDto {

    private String memberNameOrEmail;
    private String password;
}
