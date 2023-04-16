package study.musical.domain.musical.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import study.musical.domain.musical.entity.Comment;
import study.musical.domain.musical.entity.enums.CommentStatus;

import java.util.List;

import static study.musical.domain.comment.entity.QComment.comment;
import static study.musical.domain.musical.entity.QMusical.musical;

public class CommentRepositoryImpl implements CommentRespositoryCustom{

    private final JPAQueryFactory queryFactory;

    public CommentRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<Comment> findAllMusicalCommentsPage(Long musicalId, Pageable pageable) {
        List<Comment> content = queryFactory
                .selectFrom(comment)
                .join(comment.musical, musical).fetchJoin()
                .where(
                        comment.commentStatus.eq(CommentStatus.REGISTERED),
                        musical.id.eq(musicalId)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Comment> countQuery = queryFactory
                .selectFrom(comment)
                .join(comment.musical, musical).fetchJoin()
                .where(
                        comment.commentStatus.eq(CommentStatus.REGISTERED),
                        musical.id.eq(musicalId)
                );
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }
}
