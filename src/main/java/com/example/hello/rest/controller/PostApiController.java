package com.example.hello.rest.controller;

import com.example.hello.rest.dto.PostRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostApiController {

    @PostMapping("/post")
    public void test(@RequestBody Map<String,Object> requestData){

        requestData.forEach((key, value) -> {
            System.out.println("key : " + key);
            System.out.println("value : " + value);
        });

    }

    // Get Method와는 다르게 객체를 자료형으로 받을때에  RequestBody를 써준다
    // Get Method의 경우 @RequestParam 을 쓰지 않는다.
//    {
//        "account" : "JyuKa",
//            "email" : "test@gmail.com",
//            "address" : "korea",
//            "password" : "1234",
//            "phone_number" : "01012345678",
//            "OTP" : "1234"
//    }
    @PostMapping("/post2")
    public void test2(@RequestBody PostRequestDto requestData){

        System.out.println(requestData);

    }


}
