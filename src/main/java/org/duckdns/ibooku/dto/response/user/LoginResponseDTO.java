package org.duckdns.ibooku.dto.response.user;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponseDTO {
    private String email;
    private String gender;
    private String birth;
    private String nickname;
    private boolean result;

    @Builder
    public LoginResponseDTO(String email, String gender, String birth, String nickname, boolean result) {
        this.email = email;
        this.gender = gender;
        this.birth = birth;
        this.nickname = nickname;
        this.result = result;
    }
}
