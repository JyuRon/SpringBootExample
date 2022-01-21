package com.example.hello.basic.controller;

import com.example.hello.basic.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// 웹페이지의 정보를 내려주는 controller
// 그러면 json은 어떻게??
@Controller
public class ResponseController {
    @RequestMapping("/main")
    public String main(){
        return "main.html";
    }

    // ResponseEntity를 사용 하는 방법과 아래의 방법이 존재
    // RequestBody와 구별할것!!
    @ResponseBody
    @GetMapping("/user")
    public User user(){
        var user = new User();
        user.setName("stevve");


        return user;
    }
}
