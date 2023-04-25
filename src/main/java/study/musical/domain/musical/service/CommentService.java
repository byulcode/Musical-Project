package study.musical.domain.musical.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import study.musical.domain.member.entity.Member;
import study.musical.domain.member.repository.MemberRepository;
import study.musical.domain.musical.dto.request.CommentRequestDto;
import study.musical.domain.musical.dto.response.CommentDetailDto;
import study.musical.domain.musical.entity.Comment;
import study.musical.domain.musical.entity.Musical;
import study.musical.domain.musical.repository.CommentRepository;
import study.musical.domain.musical.repository.MusicalRepository;
import study.musical.infra.exception.ErrorCode;
import study.musical.infra.exception.exceptions.MusicalApiException;
import study.musical.infra.exception.exceptions.ResourceNotFoundException;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MusicalRepository musicalRepository;
    private final MemberRepository memberRepository;

    //댓글 등록
    @Transactional
    public void createComment(Long musicalId, CommentRequestDto commentRequestDto) {
        log.info("Comment service createComment run..");
        Musical musical = musicalRepository.findById(musicalId)
                .orElseThrow(() -> new ResourceNotFoundException("Musical", "id", musicalId));

        // 테스트용. 삭제 필요
        Long l = 1L;
        Member member = memberRepository.findById(l).orElseThrow(); //테스트용


        Comment parentComment = null;
        if (!ObjectUtils.isEmpty(commentRequestDto.getParentId())) {
            parentComment = commentRepository.findById(commentRequestDto.getParentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentRequestDto.getParentId()));
            log.info("parentComment = {}", parentComment);
        }

        Comment comment = Comment.of(commentRequestDto, musical, member);

        if (parentComment != null) {
            parentComment.addChildComment(comment);
        }
        log.info("comment = {}", comment);
        commentRepository.save(comment);
    }


    //뮤지컬에 달린 댓글 모두 불러오기
    @Transactional(readOnly = true)
    public Page<CommentDetailDto> getCommentsByMusicalId(Long musicalId, Pageable pageable) {
        log.info("Comment service getMusicalCommentsPage run");
        Page<Comment> commentPage = commentRepository.findAllMusicalCommentsPage(musicalId, pageable);
        return commentPage.map(CommentDetailDto::from);
    }

    //댓글 단건 조회
    @Transactional(readOnly = true)
    public CommentDetailDto getCommentById(Long musicalId, Long commentId) {
        log.info("Comment service getComment run");
        Comment comment = getCommentEntity(musicalId, commentId);
        return CommentDetailDto.from(comment);
    }


    //댓글 수정
    @Transactional
    public void modifyComment(Long musicalId, Long commentId, CommentRequestDto commentRequestDto) {
        log.info("Comment service modifyComment run..");
        Comment comment = getCommentEntity(musicalId, commentId);
        comment.modifyComment(commentRequestDto.getContent());
        log.info("modified comment : {}", comment);
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
                .orElseThrow(() -> new ResourceNotFoundException("Musical", "id", musicalId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> {
            throw new ResourceNotFoundException("Comment", "id", commentId);
        });
        if (!comment.getMusical().getId().equals(musical.getId())) {
            throw new MusicalApiException(ErrorCode.NO_TARGET);
        }
        return comment;
    }
}

