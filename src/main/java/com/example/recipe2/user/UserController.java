package com.example.recipe2.user;

import com.example.recipe2.user.requestdto.SignupRequestDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//@Slf4j
@RestController
//@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {


    private final UserService userService;
//    private final KakaoService kakaoService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/signup")
    public ResponseEntity signup(@Valid @RequestBody SignupRequestDto signupRequestDto, BindingResult bindingResult) {
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


}