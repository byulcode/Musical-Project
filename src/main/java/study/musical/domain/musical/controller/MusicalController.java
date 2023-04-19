package study.musical.domain.musical.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import study.musical.domain.musical.dto.request.MusicalCreateReqDto;
import study.musical.domain.musical.dto.request.MusicalFindDto;
import study.musical.domain.musical.dto.request.MusicalModifyReqDto;
import study.musical.domain.musical.dto.response.MusicalDetailsDto;
import study.musical.domain.musical.dto.response.MusicalInfoDto;
import study.musical.domain.musical.entity.enums.PerfStatus;
import study.musical.domain.musical.service.MusicalService;
import study.musical.infra.utils.pagination.PageResponseDto;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/musicals")
public class MusicalController {

    private final MusicalService musicalService;

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
            @RequestParam(value = "perfStatus", defaultValue = "", required = false) String perfStatus,
            MusicalFindDto findDto
    ) {
        log.info("Musical controller getAllMusicalPage run..");

        findDto = MusicalFindDto.builder()
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
     * 뮤지컬 등록
     *
     * @param reqDto
     * @return
     */
    @PostMapping
    public ResponseEntity<MusicalDetailsDto> createMusical(@RequestBody MusicalCreateReqDto reqDto) {
        log.info("Musical controller createMusical run..");
        musicalService.createMusical(reqDto);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    /**
     * 뮤지컬 수정
     * @param id
     * @param reqDto
     * @return
     */
    @PutMapping("{id}")
    public ResponseEntity<MusicalDetailsDto> modifyMusical(
            @PathVariable Long id,
            @RequestBody MusicalModifyReqDto reqDto) {
        log.info("Musical controller modifyMusical run..");
        musicalService.modifyMusical(id, reqDto);
        return ResponseEntity.ok(null);
    }

}
