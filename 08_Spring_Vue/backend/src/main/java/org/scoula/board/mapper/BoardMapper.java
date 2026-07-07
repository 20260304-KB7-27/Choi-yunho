package org.scoula.board.mapper;

import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.domain.BoardVO;
import org.scoula.common.pagination.PageRequest;

import java.util.List;


public interface BoardMapper {

    List<BoardVO> getList();

    public BoardVO get(Long no);

    public void create(BoardVO board);

    public int update(BoardVO boardVO);

    public int delete(long no);

    void createAttachment(BoardAttachmentVO attach);

    BoardAttachmentVO getAttachment(Long no);

    List<BoardAttachmentVO> getAttachmentList(Long bno);

    int deleteAttachment(Long no);


    List<BoardVO> getPage(PageRequest pageRequest);

    int getTotalCount();
}
