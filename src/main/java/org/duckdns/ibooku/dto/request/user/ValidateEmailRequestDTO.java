package org.duckdns.ibooku.dto.request.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ValidateEmailRequestDTO {
    private String email;
    private String code;
}
