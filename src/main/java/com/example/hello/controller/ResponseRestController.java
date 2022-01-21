package com.example.hello.controller;

import com.example.hello.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ResponseRestController {

    // type : text
    @GetMapping("/text")
    public String text(@RequestParam String account){
        return account;

    }

    //Json
    @PostMapping("/json")
    public User json(@RequestBody User user){
        return user;    // 200 ok

    }

    // put의 경우 200 201 둘다
    // 201에 대한 응답은>??
    @PutMapping("/put")
    public ResponseEntity<User> put(@RequestBody User user){

        // 응답에 대한 custom이 필요한 경우
        //json으로 출력
        return ResponseEntity.status(HttpStatus.CREATED).body(user);

    }

    @PutMapping("/put2")
    public User put2(@RequestBody User user){

        // 응답에 대한 custom이 필요한 경우
        //json으로 출력
        return user;

    }
}
