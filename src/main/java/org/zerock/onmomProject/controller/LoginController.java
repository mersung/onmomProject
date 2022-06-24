package org.zerock.onmomProject.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.onmomProject.security.dto.OnmomAuthMemberDTO;

@Controller
@RequestMapping("/onmom")
@Log4j2
public class LoginController {

    @GetMapping("/login")
    public void login(){}

    // 로그인된 사용자 정보 출력
    @GetMapping("/member")
    public void Member(@AuthenticationPrincipal OnmomAuthMemberDTO onmomAuthMember){
        log.info("**************** Member *****************");
        log.info(onmomAuthMember);
    }
}
