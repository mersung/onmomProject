package org.zerock.onmomProject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.onmomProject.dto.FreePageRequestDTO;
import org.zerock.onmomProject.service.FreeBoardService;

@Controller
@RequestMapping("/onmom/")
@Log4j2
@RequiredArgsConstructor
public class FreeBoardController {

    private final FreeBoardService freeBoardService;

    @GetMapping("/freeBoardList")
    public void list(FreePageRequestDTO freePageRequestDTO, Model model){

        model.addAttribute( "result",
        freeBoardService.getList(freePageRequestDTO));
    }

//    @GetMapping("/")
}
