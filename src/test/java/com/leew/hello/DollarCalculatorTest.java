package com.leew.hello;

import com.leew.hello.java.Calculator;
import com.leew.hello.java.DollarCalculator;
import com.leew.hello.java.MarketApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class) // mocking을 위한 환경 설정
public class DollarCalculatorTest {

    @Mock
    public MarketApi marketApi;

    @BeforeEach // marketApi 객체에서 connect함수가 일어나기 전에 발생
    public void init() {
        Mockito.lenient().when(marketApi.connect()).thenReturn(3000);
        // connect 값이 반환될 때 기존 marketApi 값의 1100이 아니라 우리가 설정한 3000이 리턴되도록 설정
    }
    // 그리고 이 것이 잘 작동하는 지 테스트한다.
    @Test
    public void mockTest() {
        // 이전에는 새로 marketApi를 생성했지만, 여기서는 미리 생성한 marketApi를 사용해야 Mock된 marketApi의 BeforeEach를 반영 가능
        // MarketApi marketApi = new MarketApi();
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init(); // init될 때 위의 BeforeEach가 실행되어, 실제로는 3000이 반환

        Calculator calculator = new Calculator(dollarCalculator);

        // System.out.println(calculator.sum(10, 10));
        Assertions.assertEquals(60000, calculator.sum(10, 10));
        Assertions.assertEquals(0, calculator.minus(10, 10));
    }

    @Test
    public void testHello() {
        System.out.println("hello");
    }

    @Test
    public void dollarTest() {
        MarketApi marketApi = new MarketApi();
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init();

        Calculator calculator = new Calculator(dollarCalculator);

        // System.out.println(calculator.sum(10, 10));
        Assertions.assertEquals(22000, calculator.sum(10, 10));
        Assertions.assertEquals(0, calculator.minus(10, 10));
    }
}
