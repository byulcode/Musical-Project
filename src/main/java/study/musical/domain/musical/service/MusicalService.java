package study.musical.domain.musical.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.musical.domain.musical.entity.Musical;
import study.musical.domain.musical.entity.dto.request.MusicalFindDto;
import study.musical.domain.musical.entity.dto.response.MusicalDetailsDto;
import study.musical.domain.musical.entity.dto.response.MusicalInfoDto;
import study.musical.domain.musical.repository.MusicalRepository;
import study.musical.infra.exception.ErrorCode;
import study.musical.infra.exception.exceptions.MusicalNotExistException;

@Service
@RequiredArgsConstructor
public class MusicalService {

    private final MusicalRepository musicalRepository;

    /**
     * 뮤지컬 전체 조회(페이징). 뮤지컬 제목, 상태별로 조회 가능
     *
     * @param musicalFindDto
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    public Page<MusicalInfoDto> getAllMusicalsPage(MusicalFindDto musicalFindDto, Pageable pageable) {
        Page<Musical> musicalPage = musicalRepository.findAllMusicalsPage(musicalFindDto, pageable);
        return musicalPage.map(MusicalInfoDto::from);
    }

    @Transactional(readOnly = true)
    public MusicalDetailsDto getMusicalDetailById(Long id) {
        Musical musical = musicalRepository.findById(id).orElseThrow(() ->{
            throw new MusicalNotExistException(ErrorCode.MUSICAL_NOT_EXIST);
        });
        return MusicalDetailsDto.from(musical);
    }

}
