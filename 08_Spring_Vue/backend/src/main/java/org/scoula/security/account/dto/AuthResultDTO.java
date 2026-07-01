package org.scoula.security.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResultDTO {

    private String token;
    private UserInfoDTO user;
}
