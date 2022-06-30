package org.zerock.onmomProject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.onmomProject.dto.MemberDTO;
import org.zerock.onmomProject.entity.Member;
import org.zerock.onmomProject.security.dto.OnmomAuthMemberDTO;
import org.zerock.onmomProject.service.MemberService;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/onmom/member")
@Log4j2
@RequiredArgsConstructor
public class MyPageController {

    private final MemberService memberService;



    @GetMapping("/info")
    public String memberInfo(Model model, Principal principal, HttpSession session){
//기존        String member_id = principal.getName();
//        MemberDTO dto = memberSrvice.selectMember(member_id);
//
//        model.addAttribute("dto", dto);

        /////////////////////////////////////////////////////내가 쓴 글 수정중
        //MemberDTO dto = (MemberDTO)session.getAttribute("memberDTO");
        //String member_id = dto.getMember_id();
        //위에 두 줄 코드를 변경해야함. 지금은 그냥 이메일값 직접 대입해봄.
        String member_id = principal.getName();

        MemberDTO memberInfo = memberService.selectMember(member_id);
        model.addAttribute("memberInfo", memberInfo);
        ///////////////////////////////////////////////////////내가 쓴 글 수정중


        return "onmom/member/info";

   }

//    @GetMapping("/update")
//    public String userUpdate() {
//        return "onmom/member/update";
//    }

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