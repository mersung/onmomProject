package org.zerock.onmomProject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.onmomProject.dto.ReviewPageRequestDTO;
import org.zerock.onmomProject.service.ReviewBoardService;

@Controller
@RequestMapping("/onmom")
@Log4j2
@RequiredArgsConstructor
public class TestController {

    private final ReviewBoardService reviewBoardService;

    // 후기게시판 리스트 페이지 테스트
    @GetMapping("/review_board")
    public void index(ReviewPageRequestDTO pageRequestDTO, Model model){
        // result 출력을 9로 함
        pageRequestDTO.setSize(9);
        model.addAttribute("result", reviewBoardService.getList(pageRequestDTO));
    }

    // 후기게시판 등록 페이지 테스트
    @GetMapping("/review_register")
    public void review_register(){
    }

    // 후기게시판 수정 페이지 테스트
    @GetMapping("/review_modify")
    public void review_modify(){
    }

    // 후기게시판 읽기 페이지 테스트
    @GetMapping("/review_read")
    public void review_read(){
    }


    // 자유게시판 목록 페이지 테스트
//    @GetMapping("/freeBoard_list")
//    public void freeBoard_list(){
//    }

    // 자유게시판 등록 페이지 테스트
//    @GetMapping("/freeBoard_register")
//    public void freeBoard_register(){
//    }

    // 자유게시판 수정 페이지 테스트
    @GetMapping("/freeBoard_modify")
    public void freeBoard_modify(){
    }

    // 자유게시판 읽기 페이지 테스트
    @GetMapping("/freeBoard_read")
    public void freeBoard_read(){
    }

}
