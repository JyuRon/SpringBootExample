package com.example.hello.swagger.controller;

import com.example.hello.swagger.dto.UserReq;
import com.example.hello.swagger.dto.UserRes;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;


// Controller의  정보제공
@Api(tags = {"API 정보를 제공하는 Controller"})
@RestController
@RequestMapping("/swagger")
public class SwaggerController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/plus/{x}")
    public int plus(
            @ApiParam(value = "x값")
            @PathVariable int x,

            @ApiParam(value = "y값")
            @RequestParam int y
    ){
        return x + y;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "x", value = "x 값", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "y", value = "y 값", required = true, paramType = "query", dataType = "int")
    })
    @GetMapping("/plus2/{x}")
    public int plus2(@PathVariable int x, @RequestParam int y
    ){
        return x + y;
    }


    @ApiResponse(code = 502, message = "사용자의 나이가 10살 이하일때")
    @ApiOperation(value = "사용자의 이름과 나이를 echo하는 메소드")
    @GetMapping("/user")
    public UserRes user(UserReq userReq){
        return new UserRes(userReq.getName(), userReq.getAge());
    }


    @PostMapping("/user")
    public UserRes userPost(@RequestBody UserReq req){
        return new UserRes(req.getName(), req.getAge());
    }

}
