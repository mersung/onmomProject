package org.zerock.onmomProject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/onmom")
@Log4j2
@RequiredArgsConstructor
public class mapController {

    @GetMapping("/map")
    public void map(){
        log.info("map.............");
    }
}
