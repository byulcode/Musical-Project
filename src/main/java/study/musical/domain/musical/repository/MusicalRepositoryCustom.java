package study.musical.domain.musical.repository;

import org.springframework.data.domain.Page;
import study.musical.domain.likes.entity.Likes;
import study.musical.domain.musical.entity.Musical;
import study.musical.domain.musical.dto.request.MusicalFindDto;

import java.util.List;
import java.util.Set;

public interface MusicalRepositoryCustom {

    Page<Musical> findAllMusicalsPage(MusicalFindDto musicalFindDto);

    List<Musical> findAllMusicalsLiked(Set<Likes> likesSet);
}
