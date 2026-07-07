package org.scoula.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.mapper.BoardMapper;
import org.scoula.common.pagination.Page;
import org.scoula.common.pagination.PageRequest;
import org.scoula.utils.UploadFiles;
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
    private final static String BASE_DIR = UploadFiles.BASE_DIR + "/board";

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
                .orElseThrow(()->new NoSuchElementException("🍣no : " + no + "번 게시글이 없습니다"));
    }

    @Transactional // 2가지의 insert문중 하나라도 예외가발생하면 rollback
    @Override
    public BoardDTO create(BoardDTO board) {

        BoardVO boardVo = board.toVo();

        boardMapper.create(boardVo); // 게시글 생성

        // 만약 첨부파일들이 있으면 저장
        List<MultipartFile> files = board.getFiles();

        if(files != null && !files.isEmpty()) {
            // 첨부파일이 있을 경우
            upload(boardVo.getNo(), files);
        }

        return get(boardVo.getNo());
    }

    // 파일 업로드
    private void upload(Long bno, List<MultipartFile> files) {
        for(MultipartFile part: files) {
            if(part.isEmpty()) continue;

            // 파일을 직접 저장 -> IOException
            try {
                // 1. 실제 파일을 서버에 저장
                String uploadPath = UploadFiles.upload(BASE_DIR, part);

                // 2. 데이터베이스에 저장
                BoardAttachmentVO attach = BoardAttachmentVO.of(part, bno, uploadPath);
                boardMapper.createAttachment(attach);

            } catch (IOException e) {
                throw new RuntimeException();
                // -> @Transaction에서 감지할 수 있도록
                // 예외가 발생하면 RollBack (Transaction)
            }

        }

    }


    @Override
    public BoardDTO update(BoardDTO board) {

        int result = boardMapper.update(board.toVo());

        // 파일 업로드 처리
        List<MultipartFile> files = board.getFiles();
        if(files != null && !files.isEmpty()) {
            upload(board.getNo(), files);
        }

        return get(board.getNo()); // 실제 서비스 만들때는 수정 필요
    }

    @Override
    public BoardDTO delete(Long no) {
        BoardDTO board = get(no);
        boardMapper.delete(no);
        return board;
    }


    @Override
    public BoardAttachmentVO getAttachment(Long no) {

        return boardMapper.getAttachment(no);
    }

    @Override
    public boolean deleteAttachment(Long no) {
        return boardMapper.deleteAttachment(no) == 1;
    }

    // 페이징 게시글 조회
    @Override
    public Page getPage(PageRequest pageRequest) {

        // 1. 페이징된 게시글 조회
        List<BoardVO> boards = boardMapper.getPage(pageRequest);

        // 2. 전체 게시글 수 조회
        int totalCount = boardMapper.getTotalCount();

        // 3. VO를 DTO로 변환해서 Page 객체 생성
        return Page.of(pageRequest, totalCount,
                boards.stream().map(BoardDTO::of).toList());
    }


}
