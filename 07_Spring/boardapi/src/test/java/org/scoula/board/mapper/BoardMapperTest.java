package org.scoula.board.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.board.domain.BoardVO;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { RootConfig.class })
@Log4j2
class BoardMapperTest {

    @Autowired
    private BoardMapper mapper;

    @Test
    void getList() {
        List<BoardVO> list = mapper.getList();
        for (int i = 0; i < list.size(); i++) {
            log.info(list.get(i));
        }
    }

    @Test
    @DisplayName("BoardMapper의게시글읽기")
    public void get() {
        BoardVO board = mapper.get(1L);
        log.info(board);
    }

    @Test
    @DisplayName("BoardMapper의새글작성")
    public void create() {
        BoardVO board = new BoardVO();

        board.setTitle("새로작성하는글");
        board.setContent("새로작성하는내용");
        board.setWriter("user00");

        mapper.create(board);

        log.info(board);
    }

    @Test
    @DisplayName("BoardMapper의 글 수정")
    public void update() {
        BoardVO board = new BoardVO();

        board.setNo(1L);
        board.setTitle("수정된 제목");
        board.setContent("수정된 내용");
        board.setWriter("user00");

        int count = mapper.update(board);

        assertEquals(1, count);
        log.info("Update Result Int : {}", count);

    }

    @Test
    @DisplayName("BoardMapper의 글 삭제")
    public void delete() {

        int count = mapper.delete(2L);

        assertEquals(1, count);
        log.info("Delete Result Int : {}", count);

    }
}