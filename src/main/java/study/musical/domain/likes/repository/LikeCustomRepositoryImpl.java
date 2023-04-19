package study.musical.domain.likes.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import study.musical.domain.likes.entity.Likes;

import java.util.Optional;

import static study.musical.domain.likes.entity.QLikes.likes;

public class LikeCustomRepositoryImpl implements LikeCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    public LikeCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public Optional<Likes> exist(Long musicalId, String email) {
        Likes pLike = jpaQueryFactory
                .selectFrom(likes)
                .where(likes.member.email.eq(email),
                        likes.musical.id.eq(musicalId))
                .fetchFirst();
        return Optional.ofNullable(pLike);
    }

    public long findMusicalLikeCnt(Long musicalId) {
        return jpaQueryFactory
                .selectFrom(likes)
                .where(likes.musical.id.eq(musicalId))
                .fetchCount();
    }

}
