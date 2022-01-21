package com.example.hello.controller;

import com.example.hello.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    @GetMapping(path="/hello")  // http://localhost:9090/api/get/hello
    public String hello(){
        return "get hello";
    }

    @RequestMapping(path = "/hi", method = RequestMethod.GET)  // get / post / put / delete
    public String hi(){
        return "hi";
    }

    // http://localhost:9090/api/get/path-variable/{name}
    // @PathVariable String 변수 부분에서 어쩔 수 없이 다른 변수 명을 사용할 경우 직접 name 지정을 한다
    // 정석 --> @PathVariable String name
    @GetMapping("/path-variable/{name}")
    public String pathVariable(@PathVariable(name = "name") String pathName){

        System.out.println("PathVariable : "+pathName);

        return pathName;
    }

    // ?key=value&key2=value2
    // http://localhost:9090/api/get/query-param?user=jyuka&email=abcd@gmai.com&age=30
    @GetMapping(path = "/query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam){

        StringBuilder sb = new StringBuilder();

        queryParam.entrySet().forEach(entry->{
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            sb.append(entry.getKey()+" = " + entry.getValue() +"\n");
        });
        return sb.toString();
    }


    // param을 명확하게 기준을 잡을 경우
    @GetMapping("/query-param02")
    public String queryParam02(@RequestParam String user,
                               @RequestParam String email,
                               @RequestParam int age
    ){
        System.out.println(user);
        System.out.println(email);
        System.out.println(age);
        return user + " " + email + " " + age;


    }

    // 현업에서 가장 많이 쓰는 RequestParam
    // @RequestParam을 붙이지 않는다
    // url에서 key의 개수가 오버 되는 경우 파싱하지 않는다.
    @GetMapping("/query-param03")
    public String queryParam03(UserRequest userRequest){
        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());
        return userRequest.toString();


    }


}
