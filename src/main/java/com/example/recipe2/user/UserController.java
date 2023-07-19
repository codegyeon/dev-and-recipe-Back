package com.example.recipe2.user;

import com.example.recipe2.security.UserDetailsImpl;
import com.example.recipe2.security.dto.EmailNicknameDto;
import com.example.recipe2.user.requestdto.SignupRequestDto;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
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


    private final UserService userService;
//    private final KakaoService kakaoService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/signup")
    public ResponseEntity signup(@Valid @RequestBody SignupRequestDto signupRequestDto, BindingResult bindingResult) {
        System.out.println("signupRequestDto.getEmail() = " + signupRequestDto.getEmail());
        System.out.println("signupRequestDto.getNickname() = " + signupRequestDto.getNickname());
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
        return new ResponseEntity<>(
                new EmailNicknameDto(userDetails.getUser().getEmail(),userDetails.getUser().getNickname()),
                HttpStatus.OK
        );


    }


}
