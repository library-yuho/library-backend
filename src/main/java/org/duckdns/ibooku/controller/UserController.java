package org.duckdns.ibooku.controller;

import lombok.RequiredArgsConstructor;
import org.duckdns.ibooku.dto.request.user.JoinRequestDTO;
import org.duckdns.ibooku.dto.request.user.LoginRequestDTO;
import org.duckdns.ibooku.dto.request.user.SendEmailRequestDTO;
import org.duckdns.ibooku.dto.request.user.ValidateEmailRequestDTO;
import org.duckdns.ibooku.dto.response.user.LoginResponseDTO;
import org.duckdns.ibooku.entity.user.User;
import org.duckdns.ibooku.service.user.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/send-email")
    public boolean sendEmail(@RequestBody SendEmailRequestDTO sendEmailRequest) {
        return userService.sendEmail(sendEmailRequest.getEmail());
    }

    @PostMapping("/validate-email")
    public boolean validateEmail(@RequestBody ValidateEmailRequestDTO validateEmailRequest) {
        return userService.validateEmail(validateEmailRequest);
    }

    @PostMapping("/join")
    public boolean join(@RequestBody JoinRequestDTO joinRequest) {
        userService.join(joinRequest);
        return true;
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

    @GetMapping("email-check")
    public boolean emailCheck(@RequestParam String email) {
        return userService.emailCheck(email);
    }
}
