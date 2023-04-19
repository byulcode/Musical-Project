package study.musical.domain.likes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.musical.domain.likes.entity.Likes;

public interface LikeRepository extends JpaRepository<Likes, Long>, LikeCustomRepository {

    void deleteLikesByMemberIdAndMusicalId(Long memberId, Long musicalId);
}
