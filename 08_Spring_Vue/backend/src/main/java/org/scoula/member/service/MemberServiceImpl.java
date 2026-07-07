package org.scoula.member.service;

import lombok.RequiredArgsConstructor;
import org.scoula.member.dto.ChangePasswordDTO;
import org.scoula.member.dto.MemberDTO;
import org.scoula.member.dto.MemberJoinDTO;
import org.scoula.member.dto.MemberUpdateDTO;
import org.scoula.member.exception.PasswordMismatchException;
import org.scoula.member.mapper.MemberMapper;
import org.scoula.security.account.domain.AuthVO;
import org.scoula.security.account.domain.MemberVO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    final MemberMapper mapper;
    final PasswordEncoder passwordEncoder;


    // 중복체크
    @Override
    public Boolean checkDuplicate(String username) {
        MemberVO memberVo = mapper.findByUsername(username);
        return memberVo != null ? true : false;
    }

    @Transactional
    @Override
    public MemberDTO join(MemberJoinDTO dto) {

        MemberVO member = dto.toVO();

        // 1. 비밀번호 암호화
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        // 2. 회원정보 저장
        mapper.insert(member);

        // 3. 권한정보 저장
        AuthVO auth = new AuthVO();
        auth.setUsername(member.getUsername());
        auth.setAuth("ROLE_MEMBER"); // 기본권한 설정
        mapper.insertAuth(auth);

        // 4. 아바타 파일 저장
        saveAvatar(dto.getAvatar(), member.getUsername());

        return get(member.getUsername());
    }

    @Override
    public MemberDTO update(MemberUpdateDTO member) {
        MemberVO vo = mapper.get(member.getUsername());
        if(!passwordEncoder.matches(member.getPassword(),vo.getPassword())) { // 비밀번호 일치 확인
            throw new PasswordMismatchException();
        }

        mapper.update(member.toVO());
        saveAvatar(member.getAvatar(), member.getUsername());
        return get(member.getUsername());
    }

    @Override
    public void changePassword(ChangePasswordDTO changePassword) {
        MemberVO member = mapper.get(changePassword.getUsername());

        if(!passwordEncoder.matches(changePassword.getOldPassword(), member.getPassword())) {
            throw new PasswordMismatchException();
        }

        changePassword.setNewPassword(passwordEncoder.encode(changePassword.getNewPassword()));

        mapper.updatePassword(changePassword);
    }


    // 단건(상세조회)
    private MemberDTO get(String username) {
        MemberVO memberVO = Optional.ofNullable(mapper.get(username))
                .orElseThrow(NoSuchElementException::new);

        return MemberDTO.of(memberVO);
    }

    // 아바타 파일 저장
    private void saveAvatar(MultipartFile avatar, String username) {
        if(avatar != null && !avatar.isEmpty()) {

            File dest = new File("c:/upload/avatar", username + ".png");
            try {
                avatar.transferTo(dest); // 파일 저장
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }






}
