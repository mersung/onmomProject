package org.zerock.onmomProject.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/onmom")
@Log4j2
public class TestController {

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

    // 후기게시판 리스트 페이지 테스트
    @GetMapping("/review_board")
    public void review_board(){

    }

}
