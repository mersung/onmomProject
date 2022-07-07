package org.zerock.onmomProject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.onmomProject.dto.FreeBoardDTO;
import org.zerock.onmomProject.dto.FreePageRequestDTO;
import org.zerock.onmomProject.entity.FreeBoard;
import org.zerock.onmomProject.service.FreeBoardService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
@RequestMapping("/onmom/freeBoard")
@Log4j2
@RequiredArgsConstructor
public class FreeBoardController {

    private final FreeBoardService freeBoardService;

    @GetMapping("/freeBoardList")
    public void freeBoard_list(FreePageRequestDTO freePageRequestDTO,Long free_id,Model model){

        log.info("list............." + freePageRequestDTO);

        Integer replyCount = freeBoardService.replyCount(free_id);

        model.addAttribute( "result", freeBoardService.getList(freePageRequestDTO));
    }

    @GetMapping("/freeBoardRegister")
    public void freeBoardRegister(){
        log.info("regiser get...");
    }

    @PostMapping("/freeBoardRegister")
    public String freeBoardRegisterPost(FreeBoardDTO dto, RedirectAttributes redirectAttributes){

        log.info("dto..." + dto);
        //새로 추가된 엔티티의 번호
        Long free_id = freeBoardService.register(dto);

        log.info("BNO: " + free_id);

        redirectAttributes.addFlashAttribute("msg", free_id);
        return "redirect:/onmom/freeBoard/freeBoardList";
    }

    @ResponseBody
    @GetMapping("/freeBoardLike")
    public ResponseEntity<Long> freeBoardReadLike(HttpServletRequest request, HttpServletResponse response, Principal principal, Long free_id){

        log.info("로그인 아이디 =" + principal.getName());
        String Id = principal.getName();

        log.info("게시판 번호 =" + free_id);
        String Bno = String.valueOf(free_id);

        //쿠키생성
        Cookie cookie = new Cookie(Bno,Id);
        cookie.setPath("/");
        cookie.setMaxAge(60*60*24*7);
        log.info("NEW 쿠키쿠키 ="+cookie);

        response.addCookie(cookie);
        Cookie[] cookies = request.getCookies();

        for (Cookie ck:cookies){
            log.info("쿠키 명 =" + ck.getName());
            log.info("쿠키 값 =" + ck.getValue());
            String cookieName = ck.getName();
            String cookieValue = ck.getValue();

            if (Bno.equals(cookieName)) {
                if (cookieValue != Id) {
                    freeBoardService.updateLike(free_id);
                } else if (cookieValue.equals(Id) && Bno.equals(cookieName)) {

                    log.info("중복된 아이디입니다.");

                    break;
                }
            }
        }

        FreeBoardDTO freeBoardDTO = freeBoardService.get(free_id);

        log.info(freeBoardDTO.getLike_cnt()+freeBoardDTO.getHate_cnt()+"!@#!@#!@#!#!@#!@#!@#!@#");

        log.info(freeBoardDTO );

        return new ResponseEntity<>(freeBoardDTO.getLike_cnt(), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/freeBoardHate")
    public ResponseEntity<Long> freeBoardReadHate(Long free_id){
        freeBoardService.updateHate(free_id);

        FreeBoardDTO freeBoardDTO = freeBoardService.get(free_id);

        log.info(freeBoardDTO.getLike_cnt()+freeBoardDTO.getHate_cnt()+"!@#!@#!@#!#!@#!@#!@#!@#");

        log.info(freeBoardDTO );

        return new ResponseEntity<>(freeBoardDTO.getHate_cnt(), HttpStatus.OK);
    }

    @GetMapping({"/freeBoardRead", "/freeBoardModify"})
    public void freeBoardRead(@ModelAttribute("requestDTO") FreePageRequestDTO freePageRequestDTO, Long free_id, Model model){

        log.info("bno: " + free_id);

        FreeBoardDTO freeBoardDTO = freeBoardService.get(free_id);

        log.info(freeBoardDTO);

        model.addAttribute("dto", freeBoardDTO);

    }

    @PostMapping("/freeBoardRemove")
    public String freeBoardRemove(long free_id, RedirectAttributes redirectAttributes){


        log.info("bno: " + free_id);

        freeBoardService.removeWithReplies(free_id);

        redirectAttributes.addFlashAttribute("msg", free_id);

        return "redirect:/onmom/freeBoard/freeBoardList";

    }

    @PostMapping("/freeBoardModify")
    public String freeBoardModify(FreeBoardDTO dto,
                         @ModelAttribute("requestDTO") FreePageRequestDTO freePageRequestDTO,
                         RedirectAttributes redirectAttributes){


        log.info("post modify.........................................");
        log.info("dto: " + dto);

        freeBoardService.modify(dto);

        redirectAttributes.addAttribute("page",freePageRequestDTO.getPage());
        redirectAttributes.addAttribute("type",freePageRequestDTO.getType());
        redirectAttributes.addAttribute("keyword",freePageRequestDTO.getKeyword());

        redirectAttributes.addAttribute("free_id",dto.getFree_id());

        return "redirect:/onmom/freeBoard/freeBoardRead";

    }

}
