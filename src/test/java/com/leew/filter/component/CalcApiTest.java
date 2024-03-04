package com.leew.filter.component;

import com.leew.filter.component.Calculator;
import com.leew.filter.component.DollarCalculator;
import com.leew.filter.component.MarketApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

@SpringBootTest // 스프링 컨테이너가 올라가면서 전체적인 테스트가 가능해진다.
// 통합 테스트 : 모든 Bean이 올라간 채로 테스트하는 것
@Import({MarketApi.class, DollarCalculator.class}) // 빈으로 등록되어 테스트 환경에서 스프링 컨테이너에 의해 받아져야 할 객체들
// 중괄호로 묶어서 받는다.
public class CalcApiTest {

    @MockBean // 모킹하면서 Component로 등록되어 빈으로 컨테이너에서 받는 객체 Annotation
    private MarketApi marketApi;

    @Autowired
    private Calculator calculator;

    @Test
    void contextLoads() { }

    // TODO: DollarController Test
    @Test
    public void dollarCalculatorTest() {
        // 똑같이 Component 디렉토리 생성하고 테스트해야 main/MarketApi가 매핑되어 모킹 가능해진다.
        Mockito.when(marketApi.connect()).thenReturn(3000);
        // calculator에서 init되지만, 그래도 mocking을 위해 설정

        int sum = calculator.sum(10, 10);
        int minus = calculator.minus(10, 10);

        Assertions.assertEquals(60000, sum);
        Assertions.assertEquals(0, minus);
    }
}
