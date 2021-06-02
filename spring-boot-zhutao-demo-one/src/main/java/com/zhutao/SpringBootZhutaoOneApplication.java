package com.zhutao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@MapperScan("com.zhutao.mapper")
@EnableScheduling
public class SpringBootZhutaoOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootZhutaoOneApplication.class, args);
    }

}
