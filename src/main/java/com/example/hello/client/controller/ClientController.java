package com.example.hello.client.controller;


import com.example.hello.client.dto.Req;
import com.example.hello.client.dto.UserResponse;
import com.example.hello.client.service.RestTemplateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final RestTemplateService restTemplateService;

    public ClientController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @GetMapping("/hello")
    public Req<UserResponse> getHello(){
//        return restTemplateService.hello();
        return restTemplateService.genericExchange();
    }
}
