package study.musical.domain.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.musical.domain.member.service.MemberService;
import study.musical.domain.musical.dto.response.MusicalInfoDto;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/musicals")
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/member/{id}/like/list")
    public ResponseEntity<?> getAllMemberLikeList(@PathVariable Long id) {
        log.info("User control getAllMemberLikeList run..");
        List<MusicalInfoDto> musicalInfoDtoList = memberService.getAllMusicalsLiked(id);
        return new ResponseEntity<>(musicalInfoDtoList, HttpStatus.OK);
    }
}
