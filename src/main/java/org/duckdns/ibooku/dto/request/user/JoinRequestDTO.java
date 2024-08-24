package org.duckdns.ibooku.dto.request.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JoinRequestDTO {
    private String email;
    private String password;
    private String gender;
    private String birth;
    private String nickname;
}
