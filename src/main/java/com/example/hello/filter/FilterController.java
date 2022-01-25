package com.example.hello.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/filter/user")
public class FilterController {

    @PostMapping("")
    public User user(@RequestBody User user){

        log.info("User : {}", user);

        return user;

    }
}
