package com.soe.langya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ServletComponentScan
@SpringBootApplication
public class LangyaApplication {

    public static void main(String[] args) {
        SpringApplication.run(LangyaApplication.class, args);
    }


}
