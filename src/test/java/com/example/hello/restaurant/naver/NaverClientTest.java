package com.example.hello.restaurant.naver;

import com.example.hello.restaurant.naver.dto.SearchImageReq;
import com.example.hello.restaurant.naver.dto.SearchLocalReq;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NaverClientTest {

    @Autowired
    private NaverClient naverClient;

    @Test
    public void searchLocalTest(){

        var search = new SearchLocalReq();
        search.setQuery("갈비집");

        var result = naverClient.searchLocal(search);
        System.out.println(result);
        Assertions.assertNotNull(result.getItems().stream().findFirst().get().getCategory());
    }


    @Test
    public void searchImageTest(){
        var search = new SearchImageReq();
        search.setQuery("갈비집");

        var result = naverClient.searchImage(search);
        System.out.println(result);
    }

}