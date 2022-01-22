package com.example.hello;

import com.example.hello.rest.dto.ObjectMapperTestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloApplicationTests {

    @Test
    void contextLoads() {
    }

    @DisplayName("Object Mapper 실습")
    @Test
    public void objectMapperTest() throws JsonProcessingException {
        System.out.println("------------------");

        // object mapper가 사용되는 class의 경우
        // getXXX로 시직하는 메소드가 getter/setter로 생성된 method가 아닌 경우 에러를 유발함

        var objectMapper = new ObjectMapper();

        // object -> text
        // object mapper는 get method를  활용한다.
        var objectMapperTestDto = new ObjectMapperTestDto("steve", 10,"010-1234-4567");
        var text = objectMapper.writeValueAsString(objectMapperTestDto);
        System.out.println(text);


        //text-> object
        // default 생성자를 필요로 한다.
        var objectUser = objectMapper.readValue(text, ObjectMapperTestDto.class);
        System.out.println(objectUser);

    }
}
