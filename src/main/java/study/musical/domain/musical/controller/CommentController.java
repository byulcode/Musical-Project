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
@RequestMapping("/musical")
public class CommentController {

    private final CommentService commentService;

    /**
     * 댓글 작성
     *
     * @param musicalId
     * @return
     */
    @PostMapping("/{musicalId}/comment")
    public ResponseEntity<?> createComment(
            @PathVariable("musicalId") Long musicalId,
            @RequestBody CommentRequestDto createRequest) {
        log.info("Comment controller createComment run");
        CommentResponseDto commentResponseDto = commentService.createComment(musicalId, createRequest);
        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }

    /**
     * 특정 뮤지컬에 해당하는 댓글 전체 조회
     *
     * @param musicalId
     * @param pageable
     * @return
     */
    @GetMapping("/{musicalId}/comment")
    public ResponseEntity<?> getCommentsPage(
            @PathVariable("musicalId") Long musicalId,
            @PageableDefault(size = 5) Pageable pageable
    ) {
        log.info("Comment controller getCommentsPage run");
        Page<CommentResponseDto> commentResponseDtoPage = commentService.getMusicalCommentsPage(musicalId, pageable);
        return new ResponseEntity<>(commentResponseDtoPage, HttpStatus.OK);
    }

    /**
     * 댓글 단건 조회
     * @param id
     * @return
     */
    @GetMapping("/comment/{id}")
    public ResponseEntity<?> getCommentById(@PathVariable("id") Long id) {
        log.info("Comment controller getCommentById run");
        CommentResponseDto commentResponseDto = commentService.getComment(id);
        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    /**
     * 댓글 수정
     *
     * @param id
     * @param commentRequestDto
     * @return
     */
    @PutMapping("/comment/{id}")
    public ResponseEntity<?> modifyComment(
            @PathVariable("id") Long id,
            @RequestBody CommentRequestDto commentRequestDto
    ) {
        log.info("Comment controller modifyComment run");
        return new ResponseEntity<>(commentService.modifyComment(id, commentRequestDto), HttpStatus.OK);
    }

    /**
     * 댓글 삭제
     * @param id
     * @return
     */
    @PatchMapping("/comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id) {
        log.info("Comment controller deleteComment run");
        commentService.deleteComment(id);
        CommentResponseDto commentResponseDto = commentService.deleteComment(id);
        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

}
