package org.scoula.board.controller;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.scoula.utils.UploadFiles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
@Slf4j
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    final private BoardService service;


    /**
     * list.jsp 로 이동하는 핸들러 메소드
     * @param model
     */
     @GetMapping("/list")
     public void list(Model model) {

         model.addAttribute("list", service.getList());
     }

    /**
     * create.jsp 로 이동하는 핸들러 메소드
     */
    @GetMapping("/create")
     public void create() {
         log.info("/board/create 로 요청 들어옴 ....");
     }

    /**
     * 게시글 등록 작업을 수행하는 핸들러
     *
     * @param board writer / title / content
     * @return board/list로 리다이렉트
     */
    @PostMapping("/create")
    public String create(BoardDTO board, RedirectAttributes ra) {
        log.info("/board/create 로 요청 들어옴 ....");

        service.create(board);

        // (1회성) 데이터를 세션에 잠간 저장했다가 리다이렉트된 다음요청에서 한번만 꺼내쓰고 사라짐
        ra.addFlashAttribute("result", board.getNo());

        return "redirect:/board/list";
    }

    /**
     * (get.jsp or update.jsp) 로 전달해주는 핸들러
     * @param no : 조회할 게시글 번호
     * @param model : 조회된 게시글 (BoardDTO)
     */
    @GetMapping({"/get", "/update"})
    public void get(@RequestParam("no") Long no, Model model) {

        model.addAttribute("board", service.get(no));
    }

//    @GetMapping("/update")
//    public void update(@RequestParam("no") Long no, Model model) {
//
//        model.addAttribute("board", service.get(no));
//    }

    /**
     * 게시글 수정 작업을 수행하는 핸들러
     * @param board : 수정할 board 정보
     * @return : board/list로 리다이렉트
     */
    @PostMapping("/update")
    public String update(BoardDTO board) {

        service.update(board);

        return "redirect:/board/list";
    }

    // Get -> 조회한다 의미 / 요청 브라우저, 크롤러 자동으로 요청 보내기 가능
    /**
     * 게시글 삭제 작업을 수행하는 핸들러
     * @param no : 삭제될 게시글의 no
     * @return : 삭제후 /board/list로 리다이렉트
     */
    @PostMapping("/delete")
    public String delete(@RequestParam("no") Long no) {

        log.info("delete 요청들어옴 ....");
        service.delete(no);

        return "redirect:/board/list";
    }

    @GetMapping("/download/{no}")
    @ResponseBody // view 반환이 아닌 데이터 전달 핸들러임
    public void download(@PathVariable Long no, HttpServletResponse response) throws IOException {

        // DB 파일 경로 가져오기
        BoardAttachmentVO attach = service.getAttachment(no);

        // 파일 객체 만들기
        File file = new File(attach.getPath());

        // download 메소드
        UploadFiles.download(response, file, attach.getFilename());
    }
}
