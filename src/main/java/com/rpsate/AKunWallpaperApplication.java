package com.rpsate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AKunWallpaperApplication {

    public static void main(String[] args) {
        SpringApplication.run(AKunWallpaperApplication.class, args);
    }

}
