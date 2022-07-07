package org.zerock.onmomProject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.onmomProject.config.PrincipalDetail;
import org.zerock.onmomProject.dto.ReviewBoardDTO;
import org.zerock.onmomProject.dto.ReviewPageRequestDTO;
import org.zerock.onmomProject.service.ReviewBoardService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.sql.Array;

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

    // 좋아요 관련
    @ResponseBody
    @GetMapping("/reviewBoardLike")
    public ResponseEntity<Long> reviewBoardLike(HttpServletRequest request, HttpServletResponse response, Principal principal, Long review_id){
        log.info(principal.getName());

        // 쿠키 배열 cookies 선언하여 cookie들 전부 수집
        Cookie[] cookies = request.getCookies();

        // flag 선언하여 각 cookie들에 대한 true, false 판별
        boolean flag = false;

        for(Cookie c: cookies){
            String cookieName = c.getName(); // 쿠키 명, review_id 값
            String cookieValue = c.getValue(); // 쿠키 값, principal.getName() 값
            log.info("cookieName = "+cookieName+"// cookieValue = "+cookieValue+"// review_id = "+review_id+"// principal.getName = "+principal.getName());

            if(cookieValue.equals(principal.getName()) && cookieName.equals(String.valueOf(review_id)) ){ // 쿠키명 = 아이디명 && 쿠키번호 = 글번호
                flag = true;
                break;
            }
        }

        if(flag){ // 쿠키 추가하면 안되는 경우
            log.info("!!!WE DON'T NEED NEW COOKIE -- CASE 0 !!!");

        }else{ // 쿠키를 추가해도 되는 경우
            log.info("!!!WE NEED NEW COOKIE -- CASE 1 !!!");
            // 쿠키 선언
            Cookie rememberLike = new Cookie(String.valueOf(review_id), principal.getName());

            // 쿠키 설정
            rememberLike.setPath("/");
            rememberLike.setMaxAge(60*60*24); // 하루

            // 쿠키 생성
            response.addCookie(rememberLike);
            // updateLike 관련 실행
            service.updateLike(review_id);
        }

        ReviewBoardDTO reviewBoardDTO = service.get(review_id);
        return new ResponseEntity<>(reviewBoardDTO.getLike_cnt(), HttpStatus.OK);
    }

    // 싫어요 관련
    @ResponseBody
    @GetMapping("/reviewBoardHate")
    public ResponseEntity<Long> reviewBoardHate(HttpServletRequest request, HttpServletResponse response, Principal principal, Long review_id){

        // 쿠키 배열 cookies 선언하여 cookie들 전부 수집
        Cookie[] cookies = request.getCookies();

        // flag 선언하여 각 cookie들에 대한 true, false 판별
        boolean flag_hate = false;

        for(Cookie c: cookies){
            String cookieName = c.getName(); // 쿠키 명, review_id 값
            String cookieValue = c.getValue(); // 쿠키 값, principal.getName() 값
            log.info("cookieName = "+cookieName+"// cookieValue = "+cookieValue+"// review_id = "+review_id+"// principal.getName = "+principal.getName());

            if(cookieValue.equals(principal.getName()) && cookieName.equals(String.valueOf(review_id)) ){ // 쿠키명 = 아이디명 && 쿠키번호 = 글번호
                flag_hate = true;
                break;
            }
        }

        if(flag_hate){ // 쿠키 추가하면 안되는 경우
            log.info("!!!WE DON'T NEED NEW COOKIE -- CASE 0 !!!");

        }else{ // 쿠키를 추가해도 되는 경우
            log.info("!!!WE NEED NEW COOKIE -- CASE 1 !!!");
            // 쿠키 선언
            Cookie rememberHate = new Cookie(String.valueOf(review_id), principal.getName());

            // 쿠키 설정
            rememberHate.setPath("/");
            rememberHate.setMaxAge(60*60*24); // 하루

            // 쿠키 생성
            response.addCookie(rememberHate);
            // updateLike 관련 실행
            service.updateHate(review_id);
        }

        ReviewBoardDTO reviewBoardDTO = service.get(review_id);
        return new ResponseEntity<>(reviewBoardDTO.getHate_cnt(), HttpStatus.OK);
    }


}
