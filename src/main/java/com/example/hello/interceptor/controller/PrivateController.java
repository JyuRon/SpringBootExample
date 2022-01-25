package com.example.hello.interceptor.controller;

import com.example.hello.filter.User;
import com.example.hello.interceptor.annotation.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/interceptor/private")
@Auth
@Slf4j
public class PrivateController {

    @GetMapping("/hello")
    public String hello(){
        log.info("private hello controller");
        return "private hello";
    }

    @PostMapping("/user")
    public User user(@RequestBody User user){
        return user;
    }
}
