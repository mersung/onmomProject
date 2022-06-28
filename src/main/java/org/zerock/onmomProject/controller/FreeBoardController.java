package org.zerock.onmomProject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String freeBoard_registerPost(FreeBoardDTO dto, RedirectAttributes redirectAttributes){

        log.info("dto..." + dto);
        //새로 추가된 엔티티의 번호
        Long free_id = freeBoardService.register(dto);

        log.info("BNO: " + free_id);

        redirectAttributes.addFlashAttribute("msg", free_id);

        return "redirect:/onmom/freeBoard_list";
    }

    @GetMapping({"/freeBoard_read", "/freeBoard_modify" })
    public void freeBoard_read(@ModelAttribute("requestDTO") FreePageRequestDTO freePageRequestDTO, Long free_id, Model model){

        log.info("bno: " + free_id);

        FreeBoardDTO freeBoardDTO = freeBoardService.get(free_id);

        log.info(freeBoardDTO);

        model.addAttribute("dto", freeBoardDTO);

    }


    @PostMapping("/freeBoard_remove")
    public String freeBoard_remove(long free_id, RedirectAttributes redirectAttributes){


        log.info("bno: " + free_id);

        freeBoardService.removeWithReplies(free_id);

        redirectAttributes.addFlashAttribute("msg", free_id);

        return "redirect:/onmom/freeBoard_list";

    }

    @PostMapping("/freeBoard_modify")
    public String modify(FreeBoardDTO dto,
                         @ModelAttribute("requestDTO") FreePageRequestDTO freePageRequestDTO,
                         RedirectAttributes redirectAttributes){


        log.info("post modify.........................................");
        log.info("dto: " + dto);

        freeBoardService.modify(dto);

        redirectAttributes.addAttribute("page",freePageRequestDTO.getPage());
        redirectAttributes.addAttribute("type",freePageRequestDTO.getType());
        redirectAttributes.addAttribute("keyword",freePageRequestDTO.getKeyword());

        redirectAttributes.addAttribute("bno",dto.getFree_id());

        return "redirect:/onmom/freeBoard_read";

    }

}
