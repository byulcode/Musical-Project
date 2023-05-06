package study.musical.domain.member.dto;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@ToString
public class SignUpDto {

    @NotBlank
    @Size(min = 2, message = "이름은 2글자 이상 입력해야 합니다.")
    private String name;

    @NotBlank(message = "이메일을 입력하세요.")
    @Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    private String email;

    @NotBlank(message = "비밀번호를 입력하세요.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$")
    private String password;
}
