package org.zerock.onmomProject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.onmomProject.dto.ReviewBoardCommentDTO;
import org.zerock.onmomProject.service.ReviewCommentService;

import java.util.List;

@RestController
@RequestMapping("/reviews") // ajax용 mapping
@Log4j2
@RequiredArgsConstructor
public class ReviewCommentController {
    private final ReviewCommentService reviewCommentService;

    // 결과 데이터 = ReviewBoardCommentDTO , 해당 ReviewBoard의 모든 ReviewBoardComment 반환
    @GetMapping("/{review_id}/all")
    public ResponseEntity<List<ReviewBoardCommentDTO>> getList(@PathVariable("review_id") Long review_id){
        log.info("======================list======================");
        log.info("후기게시판 번호 : "+review_id);

        List<ReviewBoardCommentDTO> reviewBoardCommentDTOList = reviewCommentService.getListOfReviewBoard(review_id);

        return new ResponseEntity<>(reviewBoardCommentDTOList, HttpStatus.OK);
    }

    // 결과 데이터 = 생성된 ReviewBoardComment 번호, 새로운 댓글 등록
    @PostMapping("/{review_id}")
    public ResponseEntity<Long> addComment(@RequestBody ReviewBoardCommentDTO reviewBoardCommentDTO){ // @Requestbody : ajax로 넘어오는 data 받음
        log.info("======================add ReviewComment======================");
        log.info("reviewBoardCommentDTO : "+reviewBoardCommentDTO);

        Long comment_id = reviewCommentService.register(reviewBoardCommentDTO);

        return new ResponseEntity<>(comment_id, HttpStatus.OK);
    }

    // 결과 데이터 = 댓글의 수정 성공 여부, 댓글 수정
    @PutMapping("/{review_id}/{comment_id}")
    public ResponseEntity<Long> modifyComment(@PathVariable Long comment_id, @RequestBody ReviewBoardCommentDTO reviewBoardCommentDTO){
        log.info("======================modify ReviewComment======================");
        log.info("reviewBoardCommentDTO : "+reviewBoardCommentDTO);

        reviewCommentService.modify(reviewBoardCommentDTO);

        return new ResponseEntity<>(comment_id, HttpStatus.OK);
    }

    // 결과 데이터 = 댓글 삭제
    @DeleteMapping("/{review_id}/{comment_id}")
    public ResponseEntity<Long> removeComment(@PathVariable Long comment_id){
        log.info("======================delete ReviewComment======================");
        log.info("comment_id : "+comment_id);

        reviewCommentService.remove(comment_id);

        return new ResponseEntity<>(comment_id, HttpStatus.OK);
    }
}
