package study.musical.infra.utils.encoder;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncoderUtils {

    private final PasswordEncoder passwordEncoder;

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean checkPassword(String inputPassword, String storedPassword) {
        return passwordEncoder.matches(inputPassword, storedPassword);
    }
}
