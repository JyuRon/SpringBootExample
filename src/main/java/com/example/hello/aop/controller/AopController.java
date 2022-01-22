package com.example.hello.aop.controller;

import com.example.hello.aop.annotation.Decode;
import com.example.hello.aop.annotation.Timer;
import com.example.hello.aop.dto.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aop")
public class AopController {

    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id, @RequestParam String name){
        System.out.println("RestController : Get");
        return id+" "+name;


    }
    @PostMapping("/post")
    public User post(@RequestBody User user){
        System.out.println("RestController : Post");
        return user;

    }

    @Timer
    @DeleteMapping("/delete")
    public void delete() throws InterruptedException {

        // db logic
        Thread.sleep(1000*2);

    }

    @Decode
    @PutMapping("/put")
    public User user(@RequestBody User user){
        System.out.println("put");
        System.out.println(user);
        return user;
    }
}
