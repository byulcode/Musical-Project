package study.musical.domain.musical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.musical.domain.musical.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRespositoryCustom {
}
