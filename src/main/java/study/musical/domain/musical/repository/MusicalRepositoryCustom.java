package study.musical.domain.musical.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.musical.domain.musical.entity.Musical;
import study.musical.domain.musical.entity.dto.request.MusicalFindDto;

public interface MusicalRepositoryCustom {

    Page<Musical> findAllMusicalsPage(MusicalFindDto musicalFindDto, Pageable pageable);
}
