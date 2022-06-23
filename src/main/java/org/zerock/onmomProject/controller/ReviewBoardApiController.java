package org.zerock.onmomProject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.onmomProject.dto.ReviewBoardDTO;
import org.zerock.onmomProject.entity.ReviewBoard;
import org.zerock.onmomProject.service.ReviewBoardService;

import java.util.List;

@RestController
@RequestMapping("/api")
@Log4j2
@RequiredArgsConstructor
public class ReviewBoardApiController {

    private final ReviewBoardService service;

    // 리뷰 게시판 수정
    @PutMapping("/review/{review_id}")
    public ResponseEntity<String> update(@PathVariable("review_id") Long review_id, @RequestBody ReviewBoardDTO reviewBoardDTO){
        service.modify(reviewBoardDTO);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    // 리뷰 게시판 삭제
    @DeleteMapping("/review/{review_id}")
    public ResponseEntity<String> remove(@PathVariable("review_id") Long review_id){
        service.removeWithReplies(review_id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
