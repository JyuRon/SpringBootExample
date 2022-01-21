package com.example.hello.basic.controller;

import com.example.hello.basic.dto.PutRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PutApiController {

    //  http://localhost:9090/api/put/3

    //{
    //    "name" : "jyuka",
    //        "age" : 13,
    //        "car_list" : [
    //    {"name" : "sonata", "car_number" : 123},
    //    {"name" : "sonata", "car_number" : 456}
    //   ]
    //}
    @PutMapping("/put/{userId}")
    public PutRequestDto put(@RequestBody PutRequestDto requestDto, @PathVariable(name = "userId") Long id){
        System.out.println(requestDto);
        System.out.println(id);
        return requestDto;

    }
}
