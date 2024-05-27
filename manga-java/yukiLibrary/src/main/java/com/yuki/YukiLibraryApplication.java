package com.yuki;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.yuki.mapper")
public class YukiLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(YukiLibraryApplication.class, args);
    }

}
