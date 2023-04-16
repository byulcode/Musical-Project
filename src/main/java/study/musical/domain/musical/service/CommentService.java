package study.musical.domain.musical.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.musical.domain.musical.entity.Comment;
import study.musical.domain.musical.dto.request.CommentRequestDto;
import study.musical.domain.musical.dto.response.CommentResponseDto;
import study.musical.domain.musical.repository.CommentRepository;
import study.musical.infra.exception.exceptions.CommentNotExistException;
import study.musical.infra.exception.exceptions.MusicalNotExistException;
import study.musical.domain.musical.entity.Musical;
import study.musical.domain.musical.repository.MusicalRepository;
import study.musical.infra.exception.ErrorCode;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MusicalRepository musicalRepository;
    private Logger log1;

    //댓글 등록
    @Transactional
    public void createComment(Long musicalId, CommentRequestDto commentRequestDto) {
        log.info("Comment service createComment run..");
        Musical musical = musicalRepository.findById(musicalId).orElseThrow(() -> {
            throw new MusicalNotExistException(ErrorCode.MUSICAL_NOT_EXIST);
        });

        Comment comment = Comment.builder()
                .content(commentRequestDto.getContent())
                .musical(musical)
                .build();

        log.info("comment = {}", comment);
        commentRepository.save(comment);
    }


    //뮤지컬에 달린 댓글 모두 불러오기
    @Transactional(readOnly = true)
    public Page<CommentResponseDto> getCommentsByMusicalId(Long musicalId, Pageable pageable) {
        log.info("Comment service getMusicalCommentsPage run");
        Page<Comment> commentPage = commentRepository.findAllMusicalCommentsPage(musicalId, pageable);
        return commentPage.map(CommentResponseDto::from);
    }

    //댓글 단건 조회
    @Transactional(readOnly = true)
    public CommentResponseDto getCommentById(Long musicalId, Long commentId) {
        log.info("Comment service getComment run");
        Comment comment = getCommentEntity(musicalId, commentId);
        return CommentResponseDto.from(comment);
    }


    //댓글 수정
    @Transactional
    public void modifyComment(Long musicalId, Long commentId, CommentRequestDto commentRequestDto) {
        log.info("Comment service modifyComment run..");
        Comment comment = getCommentEntity(musicalId, commentId);
        comment.modifyComment(commentRequestDto.getContent());
        log.info("modified comment : {}", comment);
        comment.setModifiedAt(LocalDateTime.now());
    }

    //댓글 삭제
    @Transactional
    public void deleteComment(Long musicalId, Long commentId) {
        log.info("Comment service deleteComment run");
        Comment comment = getCommentEntity(musicalId, commentId);
        comment.delete();
    }

    private Comment getCommentEntity(Long musicalId, Long commentId) {
        log.info("Comment Service get comment entity..");
        Musical musical = musicalRepository.findById(musicalId)
                .orElseThrow(() -> new MusicalNotExistException(ErrorCode.MUSICAL_NOT_EXIST));

        return commentRepository.findById(commentId).orElseThrow(() -> {
            throw new CommentNotExistException(ErrorCode.COMMENT_NOT_EXIST);
        });
    }
}

