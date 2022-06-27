package org.zerock.onmomProject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.onmomProject.dto.FreeBoardDTO;
import org.zerock.onmomProject.dto.FreePageRequestDTO;
import org.zerock.onmomProject.service.FreeBoardService;

@Controller
@RequestMapping("/onmom/")
@Log4j2
@RequiredArgsConstructor
public class FreeBoardController {

    private final FreeBoardService freeBoardService;

    @GetMapping("/freeBoard_list")
    public void freeBoard_list(FreePageRequestDTO freePageRequestDTO, Model model){

        log.info("list............." + freePageRequestDTO);

        model.addAttribute( "result", freeBoardService.getList(freePageRequestDTO));
    }

    @GetMapping("/freeBoard_register")
    public void freeBoard_register(){
        log.info("regiser get...");
    }

    @PostMapping("/freeBoard_register")
    public String registerPost(FreeBoardDTO freeBoardDTO, RedirectAttributes redirectAttributes){

        log.info("dto..." + freeBoardDTO);
        //새로 추가된 엔티티의 번호
        Long free_id = freeBoardService.register(freeBoardDTO);

        log.info("BNO: " + free_id);

        redirectAttributes.addFlashAttribute("msg", free_id);

        return "redirect:/board/freeBoard_list";
    }

}
