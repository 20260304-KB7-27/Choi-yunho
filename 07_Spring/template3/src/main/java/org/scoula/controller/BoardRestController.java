package org.scoula.controller;

import org.scoula.domain.BoardVO;
import org.scoula.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Controller
//@ResponseBody
@RestController
public class BoardRestController {

    // 모든 게시글을 응답하는 api
    // 1. Mapping 방식
    // 2. Annotation 방식

    private BoardService boardService;

    @Autowired
    public BoardRestController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/api/mapper/board")
    public List<BoardVO> getBoardList() {

        return boardService.getAllBoardByMapper();
    }

    @GetMapping("/api/annotation/board")
    public List<BoardVO> getBoardList2() {

        return boardService.getAllBoardByAnnotation();
    }


}