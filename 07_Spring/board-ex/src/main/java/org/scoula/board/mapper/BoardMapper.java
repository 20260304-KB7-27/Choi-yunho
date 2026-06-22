package org.scoula.board.mapper;

import org.scoula.board.domain.BoardVO;
import org.scoula.board.dto.BoardDTO;

import java.util.List;

public interface BoardMapper {
    // 게시글 전체 조회
    public List<BoardVO> getList();

    // 게시글 단건 조회
    public BoardVO get(Long no);

    // 게시글 생성
    public void create(BoardVO board);

    // 게시글 수정
    public int update(BoardVO board);

    // 게시글 삭제
    public int delete(long no);

}