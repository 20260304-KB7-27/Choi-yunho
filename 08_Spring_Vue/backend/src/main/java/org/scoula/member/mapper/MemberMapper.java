package org.scoula.member.mapper;

import org.scoula.member.dto.ChangePasswordDTO;
import org.scoula.security.account.domain.AuthVO;
import org.scoula.security.account.domain.MemberVO;

public interface MemberMapper {

    // username 중복체크할때 사용함
    MemberVO findByUsername(String username);

    // 단건조회 -> 권한까지 전부
    MemberVO get(String username);

    // 회원정보 추가
    int insert(MemberVO member);

    // 회원 권한 정보 추가
    int insertAuth(AuthVO auth);

    // 회원 정보 변경
    int update(MemberVO memberVO);

    // 패스워드 업데이트
    int updatePassword(ChangePasswordDTO changePasswordDTO);

}
