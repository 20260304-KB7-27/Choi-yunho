package org.scoula.board.mapper;

import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.domain.BoardVO;

import java.util.List;


public interface BoardMapper {

    List<BoardVO> getList();

    public BoardVO get(Long no);

    public void create(BoardVO board);

    public int update(BoardVO boardVO);

    public int delete(long no);

    public void createAttachment(BoardAttachmentVO attach);

    BoardAttachmentVO getAttachment(Long no);
}
