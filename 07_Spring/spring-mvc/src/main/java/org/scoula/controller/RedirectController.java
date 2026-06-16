package org.scoula.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
public class RedirectController {

    @GetMapping("/result")
    public String resultPage() {

        return "page/mappingResult";
    }

    /*
    * RedirectAttributes
    * - 리다이렉트 시 데이터를 임시로 전달
    *
    * Redirect
    * - Return에 prefix로 [ redirect : ] 표시를 해놓으면 redirect로 전달됨.
    * */

    @GetMapping("/redirect")
    public String redirect(RedirectAttributes redirectAttributes) {
        log.info("리다이렉트 ......");

        redirectAttributes.addAttribute("키", "값");

        return "redirect:/result"; // 클라이언트에게 "/result"로 리다이렉트 지시
    }
}
