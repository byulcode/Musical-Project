package study.musical.infra.security.jwt.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthResponse { //accessToken을 담을 dto

    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
