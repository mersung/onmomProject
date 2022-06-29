package org.zerock.onmomProject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.onmomProject.dto.FreeBoardCommentDTO;
import org.zerock.onmomProject.service.FreeBoardCommentService;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/replies/")
@Log4j2
@RequiredArgsConstructor
public class FreeBoardCommentController {
    private final FreeBoardCommentService freeBoardCommentService;

    @GetMapping(value = "/freeBoard/{free_id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FreeBoardCommentDTO>> getListByFreeBoard(@PathVariable("free_id")Long free_id){
        log.info("free_id: " + free_id);

        return new ResponseEntity<>(freeBoardCommentService.getList(free_id), HttpStatus.OK);
    }

    @PostMapping("/freeBoard/{free_id}")
    public ResponseEntity<Long> register(@RequestBody FreeBoardCommentDTO freeBoardCommentDTO){

        log.info(freeBoardCommentDTO);

        Long comment_id = freeBoardCommentService.register(freeBoardCommentDTO);

        return new ResponseEntity<>(comment_id,HttpStatus.OK);
    }
//
//    @DeleteMapping("/{comment_id}")
//    public ResponseEntity<String> remove(@PathVariable ("comment_id") Long comment_id){
//
//        log.info("Comment_id: " + comment_id);
//
//        freeBoardCommentService.remove(comment_id);
//
//        return new ResponseEntity<>("success", HttpStatus.OK);
//    }
//
//    @PutMapping("/{comment_id}")
//    public ResponseEntity<String> modify(@RequestBody FreeBoardCommentDTO freeBoardCommentDTO){
//
//        log.info(freeBoardCommentDTO);
//
//        freeBoardCommentService.modify(freeBoardCommentDTO);
//
//        return new ResponseEntity<>("success",HttpStatus.OK);
//    }
}

