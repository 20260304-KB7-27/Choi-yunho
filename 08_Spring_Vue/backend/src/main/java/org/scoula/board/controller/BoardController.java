package org.scoula.board.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/*
* @Controller
* @ResponseBody -> body 영역에 값을 직접 작성하겠다
* */
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor // final / not null로 된 필드를 포함하는 생성자를 만들어줌
public class BoardController{

    private final BoardService service;

    @ApiOperation(value = "게시글 목록", notes = "게시글 목록을 얻는 API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공적으로 요청이 처리되었습니다.", response = BoardDTO.class),
            @ApiResponse(code = 400, message = "잘못된 요청입니다."),
            @ApiResponse(code = 500, message = "서버에서 오류가 발생했습니다.")
    })
    // 전체 조회
    @GetMapping("/list")
    public List<BoardDTO> getList() {
        return service.getList();
    }

    // ResponseEntity : Header, Body에 원하는 내용을 작성하고 싶을때
    @GetMapping("")
    public ResponseEntity<List<BoardDTO>> getList2() {
        return ResponseEntity.ok(service.getList());
    }

    // 단건 조회 api/board/{no}
    @GetMapping("/{no}")
    public ResponseEntity<BoardDTO> getByID(@PathVariable Long no) {
        return ResponseEntity.ok(service.get(no));
    }

    // [Post] /api/board -> 생성
    // 요청에 대한 명시
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BoardDTO> create(
            // "data" key에 담긴 값은 application/json으로 받음
            @RequestPart("data") BoardDTO boardDTO, // application/json
            // "files" key에 담긴 값은 MultipartFile 목록으로 받음
            @RequestPart("files") List<MultipartFile> files  // MultipartFile
    ) {
        // DTO에 파일 삽입
        boardDTO.setFiles(files);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(boardDTO));
    }


    @PutMapping("/{no}")
    public ResponseEntity<BoardDTO> update(@PathVariable Long no, @RequestBody BoardDTO boardDTO) {
        boardDTO.setNo(no);

        return ResponseEntity.ok(service.update(boardDTO));
    }

    @DeleteMapping("/{no}")
    public ResponseEntity<BoardDTO> delete(@PathVariable Long no) {

        return ResponseEntity.ok(service.delete(no));
    }
}
