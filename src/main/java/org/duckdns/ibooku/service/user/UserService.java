package org.duckdns.ibooku.service.user;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.duckdns.ibooku.dto.request.user.JoinRequestDTO;
import org.duckdns.ibooku.dto.request.user.LoginRequestDTO;
import org.duckdns.ibooku.dto.request.user.ValidateEmailRequestDTO;
import org.duckdns.ibooku.entity.user.Email;
import org.duckdns.ibooku.entity.user.User;
import org.duckdns.ibooku.repository.EmailRepository;
import org.duckdns.ibooku.repository.UserRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Random;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EmailRepository emailRepository;
    private final JavaMailSender javaMailSender;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean sendEmail(String email) {
        String code = generateCode();

        emailRepository.save(Email.builder()
                        .email(email)
                        .code(code)
                        .build());

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(email); // 메일 수신자
            mimeMessageHelper.setSubject("아이북유 이메일 인증 코드입니다."); // 메일 제목
            mimeMessageHelper.setText("이메일 인증 코드는 [" + code + "] 입니다.", false); // 메일 본문 내용, HTML 여부
            javaMailSender.send(mimeMessage);

            log.info("email email success: " + email);
            return true;
        } catch (MessagingException e) {
            log.info("email send fail: " + email);
            throw new RuntimeException(e);
        }
    }

    public boolean validateEmail(ValidateEmailRequestDTO validateEmailRequest) {
        ZonedDateTime utcNow = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        ZonedDateTime fiveMinutesAgoUtc = utcNow.minusMinutes(5);

        Email email = emailRepository.findLatestEmailByEmailWithinFiveMinutes(validateEmailRequest.getEmail(), fiveMinutesAgoUtc);

        if (email != null) {
            if (email.getCode().equals(validateEmailRequest.getCode())) {
                return true;
            }
        }

        return false;
    }

    private String generateCode() {
        Random random = new Random();
        StringBuffer code = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            if (random.nextBoolean()) {
                code.append((char) ((int) (random.nextInt(26)) + 97));
            } else {
                code.append((random.nextInt(10)));
            }
        }

        return code.toString();
    }

    public boolean join(JoinRequestDTO joinRequest) {
        if (userRepository.findByEmail(joinRequest.getEmail()) != null) {
            return false;
        }

        User user = User.builder()
                .email(joinRequest.getEmail())
                .password(passwordEncoder.encode(joinRequest.getPassword()))
                .gender(joinRequest.getGender())
                .birth(joinRequest.getBirth())
                .nickname(joinRequest.getNickname())
                .build();

        userRepository.save(user);

        return true;
    }

    public User login(LoginRequestDTO loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());

        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return user;
        }

        return null;
    }

    public boolean emailCheck(String email) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            return false;
        }

        return true;
    }
}
