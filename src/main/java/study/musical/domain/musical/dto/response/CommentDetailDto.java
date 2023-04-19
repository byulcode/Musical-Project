package study.musical.domain.musical.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import study.musical.domain.musical.entity.Comment;
import study.musical.domain.musical.entity.enums.CommentStatus;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentDetailDto {

    private final String content;
    private final CommentStatus commentStatus;
    private final String email;
    private final Long musicalId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime modifiedAt;

    public static CommentDetailDto from(Comment comment) {
        return CommentDetailDto.builder()
                .content(comment.getContent())
                .commentStatus(comment.getCommentStatus())
                .email(comment.getMember().getEmail())
                .musicalId(comment.getMusical().getId())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .build();
    }
}
