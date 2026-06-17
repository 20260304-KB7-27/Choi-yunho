package org.scoula.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MemberVO {
    Long no;
    String username;
    String password;
    String email;
    Integer birthYear;
    Date regDate;
    Date updatedDate;
}