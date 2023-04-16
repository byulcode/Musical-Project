package study.musical.domain.musical.repository;

import org.springframework.data.domain.Page;
import study.musical.domain.musical.entity.Musical;
import study.musical.domain.musical.dto.request.MusicalFindDto;

public interface MusicalRepositoryCustom {

    Page<Musical> findAllMusicalsPage(MusicalFindDto musicalFindDto);
}
