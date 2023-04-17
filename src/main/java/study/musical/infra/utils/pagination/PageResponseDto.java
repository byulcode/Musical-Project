package study.musical.infra.utils.pagination;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Slf4j
@Getter
@Builder
@ToString
public class PageResponseDto<T> {

    // 페이징 content
    private List<T> content;

    // 현재 페이지 번호
    private int page;
    // 페이지 목록 사이즈
    private int size;
    // 게시글 총 개수
    private int totalCount;
    // 시작 페이지 번호, 끝 페이지 번호
    private int start, end;
    // 현재 페이지 기준 이전 페이지, 다음 페이지
    private boolean prev;
    private boolean next;
    //현재 페이지 기준 이전, 다음 페이지
    private Integer prevPage, nextPage;

    public static <T> PageResponseDto<T> of(Page<T> pageInfo) {
        Pageable pageable = pageInfo.getPageable();
        int totalPage = pageInfo.getTotalPages();

        int page = pageable.getPageNumber() + 1; // 0부터 시작하므로 1 추가
        int size = pageable.getPageSize();
        log.info("page : {}", page);
        log.info("size : {}", size);

        int tempEnd = (int) (Math.ceil(page / (double) size)) * size; // 화면 상에서 보이는 마지막 번호

        int start = tempEnd - (size - 1);
        int end = Math.min(totalPage, tempEnd);
        boolean prev = start > 1;
        boolean next = totalPage > tempEnd;

        Integer prevPage = page - 1 <= 0 ? null : page - 1;
        Integer nextPage = page + 1 > totalPage ? null : page + 1;

        log.info("start : {}, end : {}", start, end);
        log.info("prev : {}, next : {}", prev, next);
        log.info("prevPage : {}", prevPage);
        log.info("nextPage : {}", nextPage);

        return PageResponseDto.<T>builder()
                .content(pageInfo.getContent())
                .page(page)
                .size(size)
                .totalCount(pageInfo.getTotalPages())
                .start(start)
                .end(end)
                .prev(prev)
                .next(next)
                .prevPage(prevPage)
                .nextPage(nextPage)
                .build();
    }
}
