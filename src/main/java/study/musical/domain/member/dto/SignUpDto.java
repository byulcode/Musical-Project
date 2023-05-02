package study.musical.domain.member.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SignUpDto {

    private String name;
    private String email;
    private String password;
}
