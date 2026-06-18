package org.scoula.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.mapper.BoardMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor // final, @notnull이 붙은 필드만 포함하는 생성자 생성
public class BoardServiceImpl implements BoardService{

    private final BoardMapper boardMapper;

    @Override
    public List<BoardDTO> getList() {
        return List.of();
    }

    @Override
    public BoardDTO get(Long no) {
        BoardVO vo = boardMapper.get(no);
        BoardDTO dto = BoardDTO.of(vo);

        // dto가 null이면 예외 처리
        return Optional.ofNullable(dto)
                .orElseThrow(() -> new NoSuchElementException());
    }

    @Override
    public void create(BoardDTO board) {
        // userDetail 정보를 가져와서 Board에 같이 외래키로 작성해줌

        // 전달받은 BoardDTO -> BoardVO로 변환
        BoardVO boardVO = board.toVo();

        boardMapper.create(boardVO);
    }

    @Override
    public boolean update(BoardDTO board) {
        // 작성자만 작성한 게시글을 수정할 수 있음

        int result = boardMapper.update(board.toVo()); // n행에 영향을 끼쳤는지 n 응답됨.

        return result == 1;
    }

    @Override
    public boolean delete(BoardDTO board) {
        return false;
    }
}
