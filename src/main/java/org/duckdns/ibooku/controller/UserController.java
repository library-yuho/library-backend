package org.duckdns.ibooku.controller;

import lombok.RequiredArgsConstructor;
import org.duckdns.ibooku.dto.request.user.JoinRequestDTO;
import org.duckdns.ibooku.dto.request.user.LoginRequestDTO;
import org.duckdns.ibooku.dto.response.user.LoginResponseDTO;
import org.duckdns.ibooku.entity.user.User;
import org.duckdns.ibooku.service.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public String join(@RequestBody JoinRequestDTO joinRequest) {
        userService.join(joinRequest);
        return "success";
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loignRequest) {
        User user = userService.login(loignRequest);

        if (user == null) {
            return LoginResponseDTO.builder()
                    .result(false)
                    .build();
        }

        return LoginResponseDTO.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .gender(user.getGender())
                .birth(user.getBirth())
                .result(true)
                .build();
    }
}
