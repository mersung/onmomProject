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

@Controller
@RequestMapping("/onmom/freeBoard")
@Log4j2
@RequiredArgsConstructor
public class FreeBoardController {

    private final FreeBoardService freeBoardService;

    @GetMapping("/freeBoardList")
    public void freeBoard_list(FreePageRequestDTO freePageRequestDTO, Model model){

        log.info("list............." + freePageRequestDTO);

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
    public ResponseEntity<Long> freeBoardReadPost(Long free_id){
        freeBoardService.updateLike(free_id);

        FreeBoardDTO freeBoardDTO = freeBoardService.get(free_id);

        log.info(freeBoardDTO.getLike_cnt()+freeBoardDTO.getHate_cnt()+"!@#!@#!@#!#!@#!@#!@#!@#");

        log.info(freeBoardDTO );

        return new ResponseEntity<>(freeBoardDTO.getLike_cnt(), HttpStatus.OK);
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
