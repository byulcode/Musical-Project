package study.musical.infra.utils.pagination;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Data
public abstract class PageRequestDto {

    @NotNull
    private int page;
    @NotNull
    private int size;

    public PageRequestDto() {
        this.page = 1;
        this.size = 5;
    }

    public Pageable getPageable() {
        return PageRequest.of(page - 1, size);
    }
}
