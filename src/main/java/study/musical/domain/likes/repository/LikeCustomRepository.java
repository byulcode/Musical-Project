package study.musical.domain.likes.repository;

import study.musical.domain.likes.entity.Likes;

import java.util.Optional;

public interface LikeCustomRepository {

    Optional<Likes> exist(Long musicalId, Long memberId);

    long findMusicalLikeCnt(Long musicalId);
}
