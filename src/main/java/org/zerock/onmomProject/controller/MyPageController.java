package org.zerock.onmomProject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.onmomProject.dto.*;
import org.zerock.onmomProject.service.FreeBoardService;
import org.zerock.onmomProject.service.MemberService;
import org.zerock.onmomProject.service.ReviewBoardService;

import java.security.Principal;

@Controller
@RequestMapping("/onmom/member")
@Log4j2
@RequiredArgsConstructor
public class MyPageController {

    private final MemberService memberService;

    private final FreeBoardService freeBoardService;

    private final ReviewBoardService reviewBoardService;

    // 마이페이지 (자유게시판 내가 쓴 글 포함)
    @GetMapping("/freeBoardInfo")
    public String memberInfo(Model model, Principal principal,
                             FreePageRequestDTO freePageRequestDTO) {

        String member_id = principal.getName();

        // 회원 정보 불러오기
        MemberDTO memberInfo = memberService.selectMember(member_id);
        model.addAttribute("memberInfo", memberInfo);

        // 내가 쓴 글 자유게시판 게시물 불러오기
        freePageRequestDTO.setSize(5);
        model.addAttribute("freeBoardDTO", freeBoardService.getMyPost(member_id, freePageRequestDTO));

        return "onmom/member/freeBoardInfo";
    }

    // 마이페이지 (추천게시판 내가 쓴 글 포함)
    @GetMapping("/reviewBoardInfo")
    public String reviewBoardInfo(Model model, Principal principal,
                                  ReviewPageRequestDTO reviewPageRequestDTO) {


        String member_id = principal.getName();

        // 회원 정보 불러오기
        MemberDTO memberInfo = memberService.selectMember(member_id);
        model.addAttribute("memberInfo", memberInfo);

        // 내가 쓴 글 추천게시판 게시물 불러오기
        reviewPageRequestDTO.setSize(5);
        model.addAttribute("reviewBoardDTO", reviewBoardService.getMyPost(member_id, reviewPageRequestDTO));


        return "onmom/member/reviewBoardInfo";
    }
}



