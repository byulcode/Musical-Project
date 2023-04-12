package study.musical.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.musical.domain.comment.entity.dto.request.CommentRequestDto;
import study.musical.domain.comment.entity.dto.response.CommentResponseDto;
import study.musical.domain.comment.service.CommentService;

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

        return new ResponseEntity<>(commentService.modifyComment(id, commentRequestDto), HttpStatus.OK);
    }

    /**
     * 댓글 삭제
     * @param id
     * @return
     */
    @PatchMapping("/comment/{id}")
    public ResponseEntity<?> deleteCoupon(@PathVariable("id") Long id) {
        commentService.deleteComment(id);
        CommentResponseDto commentResponseDto = commentService.deleteComment(id);
        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

}
