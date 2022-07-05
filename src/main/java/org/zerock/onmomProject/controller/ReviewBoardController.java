package org.zerock.onmomProject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@Log4j2
@RequestMapping("/onmom/review")
@RequiredArgsConstructor
public class ReviewBoardController {

    private final ReviewBoardService service;

    // 후기게시판 리스트 페이지
    @GetMapping("/list")
    public void list(String area, ReviewPageRequestDTO pageRequestDTO, Model model){
        // 지역(area) 설정
        pageRequestDTO.setArea(area);

        if (area == null) {
            pageRequestDTO.setArea("area");
        }

        // result 출력을 9로 함
        pageRequestDTO.setSize(9);
        // getListReview 사용해 index 페이지와는 sort 처리를 달리함
        model.addAttribute("result", service.getListReview(pageRequestDTO));
        model.addAttribute("area", pageRequestDTO.getArea());
    }

    // 게시글 등록화면 띄우기
    @GetMapping("/register")
    public void register(){

        log.info("등록화면 띄우기");
    }

    // 게시글 수정화면, 하나 불러오기
    @GetMapping({"/modify","/read","/remove"})
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

        return "redirect:/onmom/review/list";

    }

    // 게시글 삭제
    @PostMapping("/remove")
    public String remove(long review_id, RedirectAttributes redirectAttributes){

        log.info("review_id : "+review_id);
        service.removeWithReplies(review_id);

        redirectAttributes.addFlashAttribute("msg", review_id);

        return "redirect:/onmom/review/list";
    }

    // 게시글 수정
    @PostMapping("/modify")
    public String modify(ReviewBoardDTO reviewBoardDTO, @ModelAttribute("reviewPageRequestDTO")ReviewPageRequestDTO reviewPageRequestDTO,
            RedirectAttributes redirectAttributes){
        log.info("post modify..................................");
        log.info("dto : "+reviewBoardDTO);

        service.modify(reviewBoardDTO);

        redirectAttributes.addAttribute("page", reviewPageRequestDTO.getPage());
        redirectAttributes.addAttribute("type", reviewPageRequestDTO.getType());
        redirectAttributes.addAttribute("keyword",reviewPageRequestDTO.getKeyword());

        redirectAttributes.addAttribute("review_id",reviewBoardDTO.getReview_id());

        return "redirect:/onmom/review/read";
    }

    @PostMapping("/{review_id}/like")
    public void likeCookie(HttpServletResponse response, Long review_id, String check_id){
        // 쿠키 객체 생성
        Cookie rememberLike = new Cookie("review_id", String.valueOf(review_id));
        Cookie rememberId = new Cookie("check_id", check_id);

        rememberLike.setPath("/");
        rememberLike.setMaxAge(60*60*24); // 하루
        rememberId.setPath("/");
        rememberId.setMaxAge(60*60*24); // 하루

        response.addCookie(rememberLike);
        response.addCookie(rememberId);
    }


}
