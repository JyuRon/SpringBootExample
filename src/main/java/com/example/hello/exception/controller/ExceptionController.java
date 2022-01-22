package com.example.hello.exception.controller;

import com.example.hello.exception.dto.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/api/user")

// get method에 선언된 validation을 수행하기 위해 사용
@Validated
public class ExceptionController {

    @GetMapping
    public User get(
            @Size(min=2)
            @RequestParam String name,

            @NotNull
            @Min(1)
            @RequestParam Integer age){
        User user = new User();
        user.setName(name);
        user.setAge(age);

        int a = 10 + age;


        return user;
    }


    @RequestMapping
    public User post(@Valid @RequestBody User user){

        System.out.println(user);
        return user;

    }


//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public ResponseEntity notMethodArgumentNotValidException(MethodArgumentNotValidException e){
//
//        System.out.println("api Controller");
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//
//
//    }
}
