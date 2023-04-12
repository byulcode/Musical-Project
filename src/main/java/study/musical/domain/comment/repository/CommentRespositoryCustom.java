package study.musical.domain.comment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.musical.domain.comment.entity.Comment;

public interface CommentRespositoryCustom {

    Page<Comment> findAllMusicalCommentsPage(Long musicalId, Pageable pageable);
}
