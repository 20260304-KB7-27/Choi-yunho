package org.scoula.member.mapper;

import org.scoula.security.account.domain.AuthVO;
import org.scoula.security.account.domain.MemberVO;

public interface MemberMapper {

    // username 중복 체크 할 때 사용함
    MemberVO findByUsername(String username);

    // 단건조회 -> 권한까지 전부
    MemberVO get(String username);

    // 회원 정보 추가
    int insert(MemberVO member);

    // 회원 권한 정보 추가
    int insertAuth(AuthVO auth);
}
