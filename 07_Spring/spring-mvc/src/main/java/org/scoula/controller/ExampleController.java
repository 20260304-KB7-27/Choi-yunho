package org.scoula.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Log4j2
@RequestMapping("/example")
public class ExampleController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView hello(Model model) {

        log.info("==============> hello");

//        model.addAttribute("message", "신규 핸들러 메소드");
        /*
        * Model
        * - 컨트롤러가 View로 데이터를 전달할 때 사용하는 객체 (Key - Value) -> 직관적, 역할 분리
        *
        * ModelAndView
        * - 데이터와 이동할 View 이름을 같이 반환
        * */
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "신규 핸들러 메소드!");
        mav.setViewName("page/mappingResult");

        return mav;

//        return "page/mappingResult";
    }
}
