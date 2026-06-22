package org.scoula.board.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.board.dto.BoardDTO;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j2
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    public void getList() {
        List<BoardDTO> list = boardService.getList();

        assertFalse(list.isEmpty());           // 목록이 비어있지 않은지 확인

        for(BoardDTO board : list) {
            log.info("게시글: {}", board);
            assertNotNull(board.getNo());      // 게시글 번호 존재 확인
            assertNotNull(board.getTitle());   // 제목 존재 확인
        }
    }

    @Test
    void get() {
        Long testNo = 1L;
        BoardDTO board = boardService.get(testNo);

        assertNotNull(board);                          // 조회 결과 존재 확인
        assertEquals(testNo, board.getNo());           // 번호 일치 확인

        log.info("조회된 게시글: {}", board);
    }

    @Test
    @DisplayName("존재하지 않는 게시글 조회 시 NoSuchElementException 발생")
    void getNotFound() {
        Long notExistNo = 999999L;

        NoSuchElementException e = assertThrows(NoSuchElementException.class, () -> {
            boardService.get(notExistNo);
        });

        log.info("발생한 예외: {}", e.toString());
    }

    @Test
    void create() {
        BoardDTO board = BoardDTO.builder()
                .title("새로운 테스트 제목")
                .content("새로운 테스트 제목")
                .writer("testUser")
                .build();

        boardService.create(board);


        log.info("생성된 게시글 번호 {}", board.getNo());
    }

    @Test
    void update() {
        BoardDTO board = boardService.get(1L);

        board.setTitle("수정된 제목");
        board.setContent("수정된 내용");

        boolean result = boardService.update(board);

        assertTrue(result);
    }

    @Test
    void delete() {

        long testNo = 3L;

        assertDoesNotThrow(() -> boardService.get(testNo));

        boolean result = boardService.delete(testNo);
        assertTrue(result);

        assertThrows(NoSuchElementException.class, () -> {
            boardService.get(testNo);
        });

    }
}