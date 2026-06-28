package org.scoula.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.mapper.BoardMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    @Override
    public List<BoardDTO> getList() {
        return boardMapper.getList().stream()
                .map(BoardDTO::of)
                .toList();
    }

    @Override
    public BoardDTO get(Long no) {
        BoardVO vo = boardMapper.get(no);

        BoardDTO dto = BoardDTO.of(vo);

        return Optional.ofNullable(dto)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    @Transactional
    public void create(BoardDTO board) {

        BoardVO boardVo = board.toVo();

        boardMapper.create(boardVo); // 게시글 생성

        // 만약 첨부파일이 있으면 저장
        List<MultipartFile> files = board.getFiles();
        if(files != null && !files.isEmpty()) {
            // 첨부파일이 있을 경우
            upload(boardVo.getNo(), files);
        }

        board.setNo(boardVo.getNo());
    }

    // 첨부파일을 DB BLOB 컬럼에 저장
    private void upload(Long bno, List<MultipartFile> files) {
        for(MultipartFile part: files) {
            if (part.isEmpty()) continue;

            try {
                BoardAttachmentVO attach = BoardAttachmentVO.of(part, bno);
                boardMapper.createAttachment(attach);

            } catch (IOException e) {
                throw new RuntimeException(e);
                // -> @Transaction에서 감지할 수 있도록
                // 예외가 발생하면 RollBack (Transaction)
            }
        }
    }


    @Override
    public boolean update(BoardDTO board) {

        int result = boardMapper.update(board.toVo());

        return result == 1;
    }

    @Override
    public boolean delete(Long no) {

        int result = boardMapper.delete(no);

        return result == 1;
    }

    @Override
    public BoardAttachmentVO getAttachment(Long no) {

        return boardMapper.getAttachment(no);
    }
}
