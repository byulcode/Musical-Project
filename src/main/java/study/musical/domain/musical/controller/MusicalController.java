package study.musical.domain.musical.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import study.musical.domain.likes.service.LikeService;
import study.musical.domain.member.dto.PrincipalDetails;
import study.musical.domain.musical.dto.request.MusicalCreateReqDto;
import study.musical.domain.musical.dto.request.MusicalFindDto;
import study.musical.domain.musical.dto.request.MusicalModifyReqDto;
import study.musical.domain.musical.dto.response.MusicalDetailsDto;
import study.musical.domain.musical.dto.response.MusicalInfoDto;
import study.musical.domain.musical.entity.enums.PerfStatus;
import study.musical.domain.musical.service.MusicalService;
import study.musical.infra.utils.pagination.PageResponseDto;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/musicals")
public class MusicalController {

    private final MusicalService musicalService;
    private final LikeService likeService;

    /**
     * 전체 뮤지컬 리스트
     *
     * @param title      제목으로 검색
     * @param perfStatus 공연 상태로 검색
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<?> getAllMusicalsPage(
            @RequestParam(value = "title", defaultValue = "", required = false) String title,
            @RequestParam(value = "perfStatus", defaultValue = "", required = false) String perfStatus
    ) {
        log.info("Musical controller getAllMusicalPage run..");

        MusicalFindDto findDto = MusicalFindDto.builder()
                .title(title)
                .perfStatus(perfStatus.equals("") ? null : PerfStatus.valueOf(perfStatus))
                .build();
        Page<MusicalInfoDto> musicalInfos = musicalService.getAllMusicalsPage(findDto);
        return new ResponseEntity<>(PageResponseDto.of(musicalInfos), HttpStatus.OK);
    }

    /**
     * 뮤지컬 상세 보기
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<MusicalDetailsDto> getMusicalDetail(@PathVariable("id") Long id) {
        log.info("Musical controller getMusicalDetail run..");
        MusicalDetailsDto musicalDetailsDto = musicalService.getMusicalDetailById(id);
        return new ResponseEntity<>(musicalDetailsDto, HttpStatus.OK);
    }

    /**
     * 좋아요 누르기
     */
    @PostMapping("/{id}")
    public ResponseEntity<?> pushLikeBtn(
            @PathVariable("id") Long musicalId,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        log.info("Musical controller pushLikeBtn run..");
        likeService.pushLikeButton(musicalId, principalDetails.getMember().getEmail());
        return ResponseEntity.ok(null);
    }

    //멤버가 좋아요한 뮤지컬 목록 조회
    @GetMapping("/member/like/list")
    public ResponseEntity<?> getAllMemberLikeList(
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        log.info("Musical control getAllMemberLikeList run..");
        List<MusicalInfoDto> musicalInfoDtoList = musicalService.getAllMusicalsLiked(principalDetails.getMember());
        return new ResponseEntity<>(musicalInfoDtoList, HttpStatus.OK);
    }

    /**
     * 뮤지컬 등록
     *
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<MusicalDetailsDto> createMusical(
            @RequestBody MusicalCreateReqDto reqDto,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        log.info("Musical controller createMusical run..");
        log.info("createMusical principalDetails: {}", principalDetails);

        musicalService.createMusical(reqDto);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    /**
     * 뮤지컬 수정
     *
     * @param id
     * @param reqDto
     * @return
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<MusicalDetailsDto> modifyMusical(
            @PathVariable Long id,
            @RequestBody MusicalModifyReqDto reqDto,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        log.info("Musical controller modifyMusical run..");
        log.info("modifyMusical principalDetails: {}", principalDetails);
        musicalService.modifyMusical(id, reqDto);
        return ResponseEntity.ok(null);
    }

    /**
     * 뮤지컬 삭제
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("{id}")
    public ResponseEntity<?> deleteMusical(
            @PathVariable Long id,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        log.info("Musical controller deleteMusical run..");
        log.info("deleteMusical principalDetails: {}", principalDetails);
        musicalService.deleteMusical(id);
        return new ResponseEntity<>("Musical delete successfully", HttpStatus.OK);
    }
}
