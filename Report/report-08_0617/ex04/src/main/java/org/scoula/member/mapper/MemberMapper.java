package org.scoula.member.mapper;

import org.scoula.member.domain.MemberVO;
import java.util.List;

public interface MemberMapper {

    // 회원 등록 (Create)
    int insert(MemberVO member);

    // 회원 목록 조회 (Read - List)
    List<MemberVO> getList();

    // 회원 단일 조회 (Read - Single)
    MemberVO read(Long no);

    // 회원 정보 수정 (Update)
    int update(MemberVO member);

    // 회원 삭제 (Delete)
    int delete(Long no);
}
