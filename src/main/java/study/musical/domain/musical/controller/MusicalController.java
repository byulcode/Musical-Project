package study.musical.domain.musical.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import study.musical.domain.musical.dto.request.MusicalFindDto;
import study.musical.domain.musical.dto.response.MusicalDetailsDto;
import study.musical.domain.musical.dto.response.MusicalInfoDto;
import study.musical.domain.musical.entity.enums.PerfStatus;
import study.musical.domain.musical.service.MusicalService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/musical")
public class MusicalController {

    private final MusicalService musicalService;

    /**
     * 전체 뮤지컬 리스트
     * @param title 제목으로 검색
     * @param perfStatus 공연 상태로 검색
     * @param pageable
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<Page<MusicalInfoDto>> getAllMusicalsPage(
            @RequestParam(value = "title", defaultValue = "", required = false) String title,
            @RequestParam(value = "perfStatus", defaultValue = "ONGOING", required = false) String perfStatus,
            @PageableDefault(size = 5) Pageable pageable
    ) {
        MusicalFindDto musicalFindDto = MusicalFindDto.builder()
                .title(title)
                .perfStatus(PerfStatus.valueOf(perfStatus))
                .build();
        Page<MusicalInfoDto> musicalInfos = musicalService.getAllMusicalsPage(musicalFindDto, pageable);
        return new ResponseEntity<>(musicalInfos, HttpStatus.OK);
    }


    /**
     * 뮤지컬 상세 보기
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<MusicalDetailsDto> getMusicalDetail(@PathVariable("id") Long id) {
        MusicalDetailsDto musicalDetailsDto = musicalService.getMusicalDetailById(id);
        return new ResponseEntity<>(musicalDetailsDto, HttpStatus.OK);
    }

}
