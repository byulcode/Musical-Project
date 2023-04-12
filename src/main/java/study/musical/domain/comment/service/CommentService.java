package study.musical.domain.comment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.musical.domain.comment.entity.Comment;
import study.musical.domain.comment.entity.dto.request.CommentRequestDto;
import study.musical.domain.comment.entity.dto.response.CommentResponseDto;
import study.musical.domain.comment.repository.CommentRepository;
import study.musical.domain.musical.entity.Musical;
import study.musical.domain.musical.repository.MusicalRepository;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MusicalRepository musicalRepository;

    //댓글 등록
    @Transactional
    public CommentResponseDto createComment(Long musicalId, CommentRequestDto commentCreateRequest) {
        Musical musical = musicalRepository.findById(musicalId).orElseThrow();
        //만약 해당 뮤지컬이 존재한다면
        log.info("musical : {}", musical);

        Comment comment = Comment.builder()
                .content(commentCreateRequest.getContent())
                .musical(musical)
                .build();

        commentRepository.save(comment);

        return CommentResponseDto.from(comment);
    }

    @Transactional(readOnly = true)
    public CommentResponseDto getComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        return CommentResponseDto.from(comment);
    }

    //뮤지컬에 달린 댓글 모두 불러오기
    @Transactional(readOnly = true)
    public Page<CommentResponseDto> getMusicalCommentsPage(Long musicalId, Pageable pageable) {
        Page<Comment> commentPage = commentRepository.findAllMusicalCommentsPage(musicalId, pageable);
        return commentPage.map(CommentResponseDto::from);
    }

    //댓글 수정
    @Transactional
    public CommentResponseDto modifyComment(Long id, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        comment.modifyComment(commentRequestDto.getContent());
        comment.setModifiedAt(LocalDateTime.now());
        return CommentResponseDto.from(comment);
    }

    //댓글 삭제
    @Transactional
    public CommentResponseDto deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        comment.delete();
        return CommentResponseDto.from(comment);
    }
}
