package org.duckdns.ibooku.service.user;

import lombok.RequiredArgsConstructor;
import org.duckdns.ibooku.dto.request.user.JoinRequestDTO;
import org.duckdns.ibooku.dto.request.user.LoginRequestDTO;
import org.duckdns.ibooku.entity.user.User;
import org.duckdns.ibooku.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void join(JoinRequestDTO joinRequest) {
        User user = User.builder()
                .email(joinRequest.getEmail())
                .password(passwordEncoder.encode(joinRequest.getPassword()))
                .gender(joinRequest.getGender())
                .birth(joinRequest.getBirth())
                .nickname(joinRequest.getNickname())
                .build();

        userRepository.save(user);
    }

    public User login(LoginRequestDTO loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());

        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return user;
        }

        return null;
    }
}
