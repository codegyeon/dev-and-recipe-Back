package com.example.recipe2.user.requestdto;

import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
public class LoginRequestDto {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
