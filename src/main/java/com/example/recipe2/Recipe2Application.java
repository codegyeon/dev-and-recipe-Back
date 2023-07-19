package com.example.recipe2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing
@SpringBootApplication
public class Recipe2Application {

    public static void main(String[] args) {
        SpringApplication.run(Recipe2Application.class, args);
    }

}
