package org.scoula.member.service;

import org.scoula.member.dto.ChangePasswordDTO;
import org.scoula.member.dto.MemberDTO;
import org.scoula.member.dto.MemberJoinDTO;
import org.scoula.member.dto.MemberUpdateDTO;

public interface MemberService {

    // 회원 pk 중복검사
    Boolean checkDuplicate(String username);

    // 회원가입
    MemberDTO join(MemberJoinDTO member);

    // 회원정보 변경
    MemberDTO update(MemberUpdateDTO member);

    // 패스워드 변경
    void changePassword(ChangePasswordDTO changePassword);
}
