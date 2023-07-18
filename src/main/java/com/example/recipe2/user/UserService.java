package com.example.recipe2.user;

import com.example.recipe2.user.requestdto.SignupRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
//@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void signup(SignupRequestDto signupRequestDto) {
       String email = signupRequestDto.getEmail();
       String password = passwordEncoder.encode(signupRequestDto.getPassword());
       String nickname = signupRequestDto.getNickname();

        // 회원 중복(email) 확인
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("등록된 사용자 Email 입니다.");
        }

        // 닉네임 확인
        Optional<User> checkNickname = userRepository.findByNickname(nickname);
        if (checkNickname.isPresent()) {
            throw new IllegalArgumentException("중복된 닉네임이 존재합니다.");
        }

        //사용자 등록
        User user = new User(email, password, nickname, UserRoleEnum.USER);

        userRepository.save(user);
    }
}
