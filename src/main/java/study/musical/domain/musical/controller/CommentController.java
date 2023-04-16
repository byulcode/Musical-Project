package study.musical.domain.musical.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.musical.domain.musical.dto.request.CommentRequestDto;
import study.musical.domain.musical.dto.response.CommentResponseDto;
import study.musical.domain.musical.service.CommentService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/musicals")
public class CommentController {

    private final CommentService commentService;

    /**
     * 댓글 작성
     */
    @PostMapping("/{musicalId}/comments")
    public ResponseEntity<CommentResponseDto> createComment(
            @PathVariable("musicalId") Long musicalId,
            @RequestBody CommentRequestDto createRequest) {
        log.info("Comment controller createComment run");
        commentService.createComment(musicalId, createRequest);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    /**
     * 특정 뮤지컬에 해당하는 댓글 전체 조회
     */
    @GetMapping("/{musicalId}/comments")
    public ResponseEntity<?> getCommentsByMusicalId(
            @PathVariable("musicalId") Long musicalId,
            @PageableDefault(size = 5) Pageable pageable
    ) {
        log.info("Comment controller getCommentsByMusicalId run");
        Page<CommentResponseDto> commentResponseDtoPage = commentService.getCommentsByMusicalId(musicalId, pageable);
        return new ResponseEntity<>(commentResponseDtoPage, HttpStatus.OK);
    }

    /**
     * 댓글 단건 조회
     */
    @GetMapping("/{musicalId}/comments/{id}")
    public ResponseEntity<CommentResponseDto> getCommentById(
            @PathVariable Long musicalId,
            @PathVariable(value = "id") Long commentId) {
        log.info("Comment controller getCommentById run..");
        CommentResponseDto commentResponseDto = commentService.getCommentById(musicalId, commentId);
        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    /**
     * 댓글 수정
     */
    @PutMapping("/{musicalId}/comments/{id}")
    public ResponseEntity<CommentResponseDto> modifyComment(
            @PathVariable Long musicalId,
            @PathVariable("id") Long commentId,
            @RequestBody CommentRequestDto commentRequestDto
    ) {
        log.info("Comment controller modifyComment run..");
        commentService.modifyComment(musicalId, commentId, commentRequestDto);
        return ResponseEntity.ok(null);
    }

    /**
     * 댓글 삭제
     */
    @PatchMapping("/{musicalId}/comments/{id}")
    public ResponseEntity<?> deleteComment(
            @PathVariable Long musicalId,
            @PathVariable(value = "id") Long commentId) {
        log.info("Comment controller deleteComment run..");
        commentService.deleteComment(musicalId, commentId);
        return new ResponseEntity<>("comment delete successfully", HttpStatus.OK);
    }

}
