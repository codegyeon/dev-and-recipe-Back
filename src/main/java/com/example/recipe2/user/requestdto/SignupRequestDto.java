package com.example.recipe2.user.requestdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
public class SignupRequestDto {

    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    @NotBlank
    private String nickname;



    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
