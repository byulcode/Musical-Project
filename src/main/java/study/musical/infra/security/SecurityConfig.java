package study.musical.infra.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import study.musical.infra.security.jwt.JwtAuthenticationEntryPoint;
import study.musical.infra.security.jwt.JwtAuthenticationFilter;

@Slf4j
@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가(SecurityConfig.class) 스프링 필터체인에 등록이 된다.
@EnableGlobalMethodSecurity(securedEnabled = true) // secure 어노테이션 활성화
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint authenticationEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                // jwt
                .exceptionHandling()    // 예외처리
                .authenticationEntryPoint(authenticationEntryPoint)

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //spring security에서 Session을 생성하거나 사용하지 않도록 설정

                .and()
                .authorizeRequests()    //인증 진행할 uri 설정
                .antMatchers(HttpMethod.GET, "/musicals/**").permitAll()
                .antMatchers("/api/auth/**", "/test").permitAll()
                .anyRequest().authenticated();
        //jwt 필터를 usernamepassword 인증 전에 실행
        http
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        log.info("securityConfig");

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
