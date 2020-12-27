package com.devops.user;

import com.devops.base.annotation.MyApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients({"com.user.*"})
@SpringBootApplication
@ComponentScan(basePackages = {"com.devops.base.aspect", "com.devops.base.annotation", "com.devops.user.*"})
@MyApplication
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}