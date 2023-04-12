package study.musical.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.musical.domain.comment.entity.dto.request.CommentCreateRequest;
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
            @RequestBody CommentCreateRequest createRequest) {
        CommentResponseDto commentResponseDto = commentService.createComment(musicalId, createRequest);
        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }

    /**
     * 특정 뮤지컬에 해당하는 댓글 전체 조회
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
}
