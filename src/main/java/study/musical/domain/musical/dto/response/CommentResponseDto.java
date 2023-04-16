package study.musical.domain.musical.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import study.musical.domain.musical.entity.Comment;
import study.musical.domain.musical.entity.enums.CommentStatus;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentResponseDto {

    private final String content;
    private final CommentStatus commentStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime modifiedAt;

    public static CommentResponseDto from(Comment comment) {
        return CommentResponseDto.builder()
                .content(comment.getContent())
                .commentStatus(comment.getCommentStatus())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .build();
    }
}
