package study.musical.domain.likes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.musical.domain.likes.entity.Likes;
import study.musical.domain.member.entity.Member;
import study.musical.domain.musical.entity.Musical;

import java.util.Optional;
import java.util.Set;

public interface LikeRepository extends JpaRepository<Likes, Long> {

    void deleteLikesByMusicalAndMember(Musical musical, Member member);

    Optional<Likes> findByMusicalAndMember(Musical musical, Member member);

    Optional<Set<Likes>> findAllByMember(Member member);

    void deleteLikesByMusicalId(Long musicalId);
}
