package org.scoula.docs;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.scoula.board.dto.BoardDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(tags = "게시판 API")
public interface BoardControllerDocs {

    @ApiOperation(value = "인터페이스 분리버전", notes = "이렇게하면 코드부분을 깔끔하게 유지 가능!")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공적으로 요청이 처리되었습니다.", response = BoardDTO.class),
            @ApiResponse(code = 400, message = "잘못된 요청입니다."),
            @ApiResponse(code = 500, message = "서버에서 오류가 발생했습니다.")
    })
    public ResponseEntity<List<BoardDTO>> getList2();
}