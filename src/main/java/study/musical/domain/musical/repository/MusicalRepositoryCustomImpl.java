package study.musical.domain.musical.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;
import study.musical.domain.musical.entity.Musical;
import study.musical.domain.musical.entity.dto.request.MusicalFindDto;
import study.musical.domain.musical.entity.enums.PerfStatus;

import java.util.List;

import static study.musical.domain.musical.entity.QMusical.musical;

public class MusicalRepositoryCustomImpl implements MusicalRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public MusicalRepositoryCustomImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<Musical> findAllMusicalsPage(MusicalFindDto musicalFindDto, Pageable pageable) {
        List<Musical> content = queryFactory
                .selectFrom(musical)
                .where(
                        statusEq(musicalFindDto.getPerfStatus()),
                        titleEq(musicalFindDto.getTitle()))
                .orderBy(musical.likeCount.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Musical> countQuery = queryFactory
                .selectFrom(musical)
                .where(statusEq(musicalFindDto.getPerfStatus()),
                        titleEq(musicalFindDto.getTitle()));
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    private BooleanExpression statusEq(PerfStatus perfStatus) {
        return perfStatus == null ? null : musical.perfStatus.eq(perfStatus);
    }

    private BooleanExpression titleEq(String title) {
        return StringUtils.hasText(title) ? musical.title.eq(title) : null;
    }
}
