package org.zerock.onmomProject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.onmomProject.dto.ReviewPageRequestDTO;
import org.zerock.onmomProject.service.ReviewBoardService;

@Controller
@RequestMapping("/onmom")
@Log4j2
@RequiredArgsConstructor
public class OnmomController {

    private final ReviewBoardService reviewBoardService;

    @GetMapping("/index")
    public void index(ReviewPageRequestDTO pageRequestDTO, Model model){

//        model.addAttribute("result", reviewBoardService.getList(pageRequestDTO));

        log.info("************** 온몸 첫 페이지 실행 **************");
    }

    @GetMapping("/index/{area}")



}
