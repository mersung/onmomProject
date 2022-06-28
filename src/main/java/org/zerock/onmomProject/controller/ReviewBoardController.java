package org.zerock.onmomProject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.onmomProject.config.PrincipalDetail;
import org.zerock.onmomProject.dto.ReviewBoardDTO;
import org.zerock.onmomProject.dto.ReviewPageRequestDTO;
import org.zerock.onmomProject.service.ReviewBoardService;

@Controller
@Log4j2
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewBoardController {

    private final ReviewBoardService service;

    // 게시글 등록화면 띄우기
    @GetMapping("/register")
    public void register(){

        log.info("등록화면 띄우기");
    }

    // 게시글 수정화면, 하나 불러오기
    @GetMapping({"/modify","/read"})
    public void read(long review_id, @ModelAttribute("reviewPageRequestDTO")ReviewPageRequestDTO requestDTO, Model model){

        ReviewBoardDTO reviewBoardDTO = service.get(review_id);
        log.info("reviewBoardDTO: "+reviewBoardDTO);
        model.addAttribute("dto", reviewBoardDTO);
    }

    // 게시글 삽입
    @PostMapping("/register")
    public String register(ReviewBoardDTO reviewBoardDTO, RedirectAttributes redirectAttributes){

        String str = "";
        int startIdx = reviewBoardDTO.getContent().indexOf(">")+1;
        int lastIdx = reviewBoardDTO.getContent().lastIndexOf("<");
        str += reviewBoardDTO.getContent().substring(startIdx, lastIdx);
        reviewBoardDTO.setContent(str);

        log.info(reviewBoardDTO);

        Long review_id = service.register(reviewBoardDTO);
        redirectAttributes.addFlashAttribute("msg", review_id);

        return "redirect:/onmom/review_board";

    }


}
