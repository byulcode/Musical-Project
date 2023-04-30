package study.musical.infra.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import study.musical.domain.member.entity.Member;
import study.musical.infra.exception.exceptions.MusicalApiException;

import java.util.Date;
@Slf4j
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.token-validity-in-mils}")
    private int jwtExpirationInMs;

    //access token 생성
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMs); //access token 만료시간
        log.info("jwtExpirationInMs : {}", jwtExpirationInMs);
        log.info("expireDate : " + expireDate);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String generateToken(Member member) {
        String username = member.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    // token으로부터 username 가져오기
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecret).build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    // jwt token 유효성 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            throw new MusicalApiException(HttpStatus.BAD_REQUEST, "Invalid JWT Signature");
        } catch (MalformedJwtException ex) {
            throw new MusicalApiException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new MusicalApiException(HttpStatus.BAD_REQUEST, "Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new MusicalApiException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new MusicalApiException(HttpStatus.BAD_REQUEST, "JWT claims string is empty.");
        }
    }

}
