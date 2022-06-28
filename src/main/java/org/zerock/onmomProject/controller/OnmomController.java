package org.zerock.onmomProject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.zerock.onmomProject.dto.ReviewPageRequestDTO;
import org.zerock.onmomProject.dto.ReviewPageResultDTO;
import org.zerock.onmomProject.service.ReviewBoardService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/onmom")
@Log4j2
@RequiredArgsConstructor
public class OnmomController {

    private final ReviewBoardService reviewBoardService;

    @GetMapping("/index")
    public void index(String area, ReviewPageRequestDTO pageRequestDTO, Model model){

        pageRequestDTO.setArea(area);

        if (area == null) {
            pageRequestDTO.setArea("area");
        }

        log.info("area : "+ area);

//        if(area != null){
//            pageRequestDTO.setArea(area);
//            log.info("parameter  : "+area);
//            model.addAttribute("result", reviewBoardService.getList(pageRequestDTO));
//            log.info("review1 : " +reviewBoardService.getList(pageRequestDTO).getDtoList());
//        }else if(area == null){
//            model.addAttribute("result", reviewBoardService.getList(pageRequestDTO));
//            log.info("review2 : " +reviewBoardService.getList(pageRequestDTO).getDtoList());
//        }

        model.addAttribute("result",reviewBoardService.getList(pageRequestDTO));
        model.addAttribute("area", pageRequestDTO.getArea());

        log.info("parameter : " + reviewBoardService.getList(pageRequestDTO).getDtoList());
        log.info("model : " + model.getAttribute("result"));
    }

//    @GetMapping("/index/{area}")
//    public String index(ReviewPageRequestDTO pageRequestDTO, @PathVariable String area, RedirectAttributes redirectAttributes){
//
//        pageRequestDTO.setArea(area);
//        log.info("area : " + pageRequestDTO);
////        model.addAttribute("result", reviewBoardService.getList(pageRequestDTO));
//        log.info("page : " +reviewBoardService.getList(pageRequestDTO).getDtoList());
//
//        redirectAttributes.addFlashAttribute("result", pageRequestDTO);
//
//        return "redirect:/onmom/index";
//    }


}
