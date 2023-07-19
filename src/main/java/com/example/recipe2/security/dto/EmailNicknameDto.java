package com.example.recipe2.security.dto;

public class EmailNicknameDto {
    private String email;
    private String nickname;

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public EmailNicknameDto() {
    }

    public EmailNicknameDto(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }
}
