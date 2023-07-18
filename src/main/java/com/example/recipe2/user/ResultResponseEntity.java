package com.example.recipe2.user;

import lombok.Getter;

//@Getter
public class ResultResponseEntity {
    private final String msg;
    public ResultResponseEntity(String msg){
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }


}

