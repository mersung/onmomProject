package org.zerock.onmomProject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.onmomProject.dto.FreeBoardDTO;
import org.zerock.onmomProject.dto.FreePageRequestDTO;
import org.zerock.onmomProject.dto.MemberDTO;
import org.zerock.onmomProject.dto.ReviewPageRequestDTO;
import org.zerock.onmomProject.entity.Member;
import org.zerock.onmomProject.security.dto.OnmomAuthMemberDTO;
import org.zerock.onmomProject.service.FreeBoardService;
import org.zerock.onmomProject.service.MemberService;
import org.zerock.onmomProject.service.ReviewBoardService;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/onmom/member")
@Log4j2
@RequiredArgsConstructor
public class MyPageController {

    private final MemberService memberService;

    private final FreeBoardService freeBoardService;

    private final ReviewBoardService reviewBoardService;

    @GetMapping("/info")
    public String memberInfo(Model model, Principal principal,
                             FreePageRequestDTO freePageRequestDTO,
                             ReviewPageRequestDTO reviewPageRequestDTO, HttpSession session){

        String member_id = principal.getName();

        // 회원 정보 불러오기
        MemberDTO memberInfo = memberService.selectMember(member_id);
        model.addAttribute("memberInfo", memberInfo);

        // 내가 쓴 글 자유게시판 게시물 불러오기
        freePageRequestDTO.setSize(5);
        model.addAttribute("freeBoardDTO", freeBoardService.getMyPost(member_id, freePageRequestDTO));

        // 내가 쓴 글 추천게시판 게시물 불러오기
        reviewPageRequestDTO.setSize(5);
        model.addAttribute("reviewBoardDTO", reviewBoardService.getMyPost(member_id, reviewPageRequestDTO));

        return "onmom/member/info";

   }



        // 책대로
//    @GetMapping("/{member_id}/info")
//    public ResponseEntity<List<Member>> getList(@PathVariable("member_id") String member_id){
//        log.info("------------------------------");
//
//        List<MemberDTO> memberDTOList = memberService.getListOfMember(member_id);
//
//        return new ResponseEntity<>(memberDTOList, HttpStatus.OK);
//    }


}
