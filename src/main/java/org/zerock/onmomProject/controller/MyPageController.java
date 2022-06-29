package org.zerock.onmomProject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.onmomProject.dto.MemberDTO;
import org.zerock.onmomProject.security.dto.OnmomAuthMemberDTO;
import org.zerock.onmomProject.service.MemberService;

import java.security.Principal;

@Controller
@RequestMapping("/onmom/member")
@Log4j2
@RequiredArgsConstructor
public class MyPageController {

    private final MemberService memberService;



    @GetMapping("/info")

    public String memberInfo(Model model, Principal principal){
        String member_id = principal.getName();
        MemberDTO dto = memberService.selectMember(member_id);

        model.addAttribute("dto", dto);

        return "onmom/member/info";


    }
}
