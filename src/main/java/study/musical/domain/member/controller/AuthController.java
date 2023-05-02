package study.musical.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.musical.domain.member.dto.LoginDto;
import study.musical.domain.member.dto.SignUpDto;
import study.musical.domain.member.entity.Member;
import study.musical.domain.member.repository.MemberRepository;
import study.musical.infra.security.jwt.JwtTokenProvider;
import study.musical.infra.security.jwt.dto.JwtAuthResponseDto;
import study.musical.infra.utils.encoder.PasswordEncoderUtils;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final MemberRepository memberRepository;
    private final PasswordEncoderUtils passwordEncoderUtils;
    private final JwtTokenProvider jwtTokenProvider;


    @PostMapping("signin")
    public ResponseEntity<?> authenticateMember(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getMemberNameOrEmail(),
                        loginDto.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // get token from jwtTokenProvider
        String token = jwtTokenProvider.generateToken(authentication);

        //        return new ResponseEntity<>("User signed in successfully!", HttpStatus.OK);
        return ResponseEntity.ok(new JwtAuthResponseDto(token));
    }

    @PostMapping("/signup/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody SignUpDto signUpDto) {
        if (memberRepository.existsByName(signUpDto.getName())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        if (memberRepository.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // create member object
        Member member = Member.builder()
                .name(signUpDto.getName())
                .email(signUpDto.getEmail())
                .password(passwordEncoderUtils.encodePassword(signUpDto.getPassword()))
                .role(Member.RoleType.ADMIN)
                .build();

        memberRepository.save(member);

        return new ResponseEntity<>("Member registered successfully!", HttpStatus.OK);
    }

    @PostMapping("/signup/user")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
        if (memberRepository.existsByName(signUpDto.getName())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        if (memberRepository.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // create member object
        Member member = Member.builder()
                .name(signUpDto.getName())
                .email(signUpDto.getEmail())
                .password(passwordEncoderUtils.encodePassword(signUpDto.getPassword()))
                .role(Member.RoleType.USER)
                .build();

        memberRepository.save(member);

        return new ResponseEntity<>("Member registered successfully!", HttpStatus.OK);
    }
}
