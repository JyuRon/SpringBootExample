package com.example.hello.rest.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DeleteApiController {

//    http://localhost:9090/api/delete/3?account=JyuKa
    @DeleteMapping("/delete/{userId}")
    public void delete(@PathVariable String userId, @RequestParam String account){

        System.out.println(userId);
        System.out.println(account);


        // delete -> 리소스 삭제 200 ok
        // 삭제 했거나 이미 없기 때문에 항상 200
    }
}
