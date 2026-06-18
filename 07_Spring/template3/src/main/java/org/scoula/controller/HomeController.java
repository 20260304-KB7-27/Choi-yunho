package org.scoula.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Component
@Log4j2
public class HomeController {

    @GetMapping("/")
    public String home() {

        log.info("===================> Home Controller /");
        return "index";
    }
}
