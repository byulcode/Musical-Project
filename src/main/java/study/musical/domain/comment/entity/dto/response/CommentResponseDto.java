package study.musical.domain.comment.entity.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import study.musical.domain.comment.entity.Comment;
import study.musical.domain.comment.entity.enums.CommentStatus;

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
    private final Long musicalId;

    public static CommentResponseDto from(Comment comment) {
        return CommentResponseDto.builder()
                .content(comment.getContent())
                .commentStatus(comment.getCommentStatus())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .musicalId(comment.getMusical().getId())
                .build();
    }
}
