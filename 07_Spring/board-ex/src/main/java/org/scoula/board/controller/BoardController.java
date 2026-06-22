package org.scoula.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    final private BoardService service;

    /**
     * list.jsp로 이동하는 핸들러 메소드
     *
     * @param model
     */
    @GetMapping("/list")
    public void list(Model model) {
        model.addAttribute("list", service.getList());
    }

    /**
     * create.jsp로 이동하느 핸들러 메소드
     */
    @GetMapping("/create")
    public void create() {
        log.info("/board/create로 욧청 들어옴......");
    }

    /**
     * 게시글 등록 작업을 수행하는 핸들러
     *
     * @param board writer/ title/ content
     * @return board/list로 리다이렉트
     */
    @PostMapping("/create")
    public String createt(BoardDTO board, RedirectAttributes ra) {
        log.info("/board/create로 요청 들어옴......");
        service.create(board);

        // (1회용) 데이터를 세션에 잠깐 저장했다가 리다이렉트된 다음 요청에서 한번만 꺼내쓰고 사라짐
        ra.addFlashAttribute("result", board.getNo());
        return "redirect:/board/list";
    }

    /**
     * 상세조회 페이지(get.jsp)로 전달해주는 핸들러
     *
     * @param no    : 조회할 게시글 번호
     * @param model : 조회된 게시글 (BoardDTO)
     */
    @GetMapping({"/get", "/update"})
    public void get(@RequestParam("no") Long no, Model model) {
        model.addAttribute("board", service.get(no));
    }

//    @GetMapping({"/update"})
//    public void update(@RequestParam("no") Long no, Model model) {
//        model.addAttribute("board", service.get(no));
//    }

    @PostMapping("/update")
    public String update(BoardDTO board) {
        service.update(board);

        return "redirect:/board/list";
    }

    // Get요청 브라우저, 크롤러 자동으로 요청 보내기 가능
    /**
     * 게시글 삭제 작업을 수행하는 핸들러
     * @param no : 삭제될 게시글의 no
     * @return : 삭제 후 /board/list로 리다이렉트
     */
    @PostMapping("/delete")
    public String delete(@RequestParam("no") Long no) {
        service.delete(no);

        return "redirect:/board/list";
    }
}
