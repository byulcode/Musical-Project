package study.musical.infra.security.jwt.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JwtAuthResponseDto { //Token을 담을 dto

    private String tokenType = "Bearer";
    private String accessToken;

    public JwtAuthResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }

}
