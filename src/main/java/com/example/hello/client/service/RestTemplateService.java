package com.example.hello.client.service;


import com.example.hello.client.dto.Req;
import com.example.hello.client.dto.UserRequest;
import com.example.hello.client.dto.UserResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    // http://localhost/api/server/hello
    // response를 받아옴
    // pathVariable의 경우 http://localhost:9090/{a}/{b}
    // 로 선언 되 있다면 .expand("test1", test2")
    public UserResponse hello(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                .queryParam("name","steve1111")
                .queryParam("age",1011)
                .encode()
                .build()
                .toUri();

        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate();
        //client가 server에 붙는 순간 Get Method
        // 매개변수 : 요청할 URI, 서버로부터 받는 내용을 지정한 클래스로 받겠다
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);

        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        return result.getBody();

    }


    public UserResponse post(){

        // http://localhost:9090/api/server/user/{userId}/name/{userName}

        URI uri  = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"steve")     // 순차적으로 {} 에 값을 주입한다
                .toUri();
        System.out.println(uri);

        // http body -> object -> object mapper -> json -> rest template -> http body json
        UserRequest req = new UserRequest();
        req.setName("steve");
        req.setAge(10);

        RestTemplate restTemplate = new RestTemplate();

        // Server로 request할 URI, 전송할 requestBody, response받은 내용을 mapping할 클래스 지정정
       ResponseEntity<UserResponse> response = restTemplate.postForEntity(uri,req,UserResponse.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

        return response.getBody();

    }



    public UserResponse exchange(){
        URI uri  = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"steve")     // 순차적으로 {} 에 값을 주입한다
                .toUri();
        System.out.println(uri);

        // http body -> object -> object mapper -> json -> rest template -> http body json
        UserRequest req = new UserRequest();
        req.setName("steve");
        req.setAge(10);


        RequestEntity<UserRequest> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abce")
                .header("custom-header","ffff")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();
        // server로 보낼내용과 response시 매핑할 클래스를 지정
        ResponseEntity<UserResponse> response = restTemplate.exchange(requestEntity, UserResponse.class);

        return response.getBody();
    }



    public Req<UserResponse> genericExchange(){

        URI uri  = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user2/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"steve")     // 순차적으로 {} 에 값을 주입한다
                .toUri();
        System.out.println(uri);

        // http body -> object -> object mapper -> json -> rest template -> http body json


        UserRequest userRequest = new UserRequest();
        userRequest.setName("steve");
        userRequest.setAge(10);

        Req<UserRequest> req = new Req<UserRequest>();
        req.setHeader(new Req.Header());
        req.setResBody(userRequest);

        RequestEntity<Req<UserRequest>> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abce")
                .header("custom-header","ffff")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();

        // generic은은 위의 excange 메소드 처럼  Req<UserResponse>.class 사용하지 못한다

        ResponseEntity<Req<UserResponse>> response =
                restTemplate.exchange(requestEntity, new ParameterizedTypeReference<Req<UserResponse>>(){});

        // response에서 바디를 얻어온 후 json에서 body를 불러온다
        return response.getBody();

    }
}
