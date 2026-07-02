package org.scoula.member.service;

import org.scoula.member.dto.MemberDTO;
import org.scoula.member.dto.MemberJoinDTO;

public interface MemberService {
    // 회원 pk 중복 검사
    Boolean checkDuplicated(String username);

    MemberDTO join(MemberJoinDTO member);
}
