package com.example.hello.junitSpring;



import com.example.hello.junitSpring.component.DollarCalculator;
import com.example.hello.junitSpring.component.MarketApi;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DollarCalculatorTest {

    @MockBean
    public MarketApi marketApi;

    @Autowired
    private DollarCalculator dollarCalculator;

   @Test
   public void dollarCalculatorTest(){
       //Given
       Mockito.when(marketApi.connect()).thenReturn(3000);
       dollarCalculator.init();


       //When
       int sum = dollarCalculator.sum(10, 10);
       int minus = dollarCalculator.minus(10,10);

       //Then
       assertEquals(60000,sum);
       assertEquals(0,minus);
   }
}