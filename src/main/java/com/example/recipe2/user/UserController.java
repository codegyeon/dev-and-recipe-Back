package com.example.recipe2.user;

import com.example.recipe2.jwt.JwtUtil;
import com.example.recipe2.security.JwtAuthorizationFilter;
import com.example.recipe2.security.UserDetailsImpl;
import com.example.recipe2.security.dto.EmailNicknameDto;
import com.example.recipe2.user.requestdto.SignupRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@Slf4j
@RestController
//@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
//    private final KakaoService kakaoService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/signup")
    public ResponseEntity signup(@Valid @RequestBody SignupRequestDto signupRequestDto, BindingResult bindingResult) {
        logger.error(signupRequestDto.getEmail());
        logger.error(signupRequestDto.getNickname());
        logger.error(signupRequestDto.getPassword());
        logger.error("회원가입 시도");
        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (fieldErrors.size() > 0) {
            return new ResponseEntity<>(new ResultResponseEntity("회원 가입 실패"), HttpStatus.UNAUTHORIZED);
        }
        try {
            userService.signup(signupRequestDto);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(new ResultResponseEntity("회원 가입 성공"), HttpStatus.OK);
    }


    @GetMapping("/token")
    public ResponseEntity getToken(@AuthenticationPrincipal UserDetailsImpl userDetails){
        logger.error("토큰인증 성공");
        return new ResponseEntity<>(
                new EmailNicknameDto(userDetails.getUser().getEmail(),userDetails.getUser().getNickname()),
                HttpStatus.OK
        );


    }


}
