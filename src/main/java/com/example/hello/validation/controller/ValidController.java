package com.example.hello.validation.controller;

import com.example.hello.validation.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/valid")
public class ValidController {



    @PostMapping("/user")
    public Object user(@Valid @RequestBody User user, BindingResult bindingResult){

        // Valid의 결과가 BindingResult로 저장된다.
        if(bindingResult.hasErrors()){


            //StringBuilder : 단일 쓰레드에서 사용, StringBuffer의 경우 멀티쓰레드 ---> 동기화 보장
            StringBuilder sb = new StringBuilder();


            //return List
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError) objectError;
                String message = objectError.getDefaultMessage();

                System.out.println("field : " + field.getField());
                System.out.println(message);

                sb.append("field : " + field.getField());
                sb.append("message : " + message);
            });

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }


        return ResponseEntity.ok(user);

    }
}
