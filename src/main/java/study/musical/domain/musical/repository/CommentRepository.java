package study.musical.domain.musical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.musical.domain.musical.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRespositoryCustom {

    List<Comment> findByMusicalId(Long musicalId);
}
