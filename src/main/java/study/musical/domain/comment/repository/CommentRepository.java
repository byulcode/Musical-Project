package study.musical.domain.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.musical.domain.comment.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRespositoryCustom {
}
