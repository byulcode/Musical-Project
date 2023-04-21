package study.musical.domain.musical.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.musical.domain.musical.entity.Comment;

public interface CommentRespositoryCustom {

    Page<Comment> findAllMusicalCommentsPage(Long musicalId, Pageable pageable);
}
