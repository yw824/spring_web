package com.leew.filter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leew.filter.component.Calculator;
import com.leew.filter.component.DollarCalculator;
import com.leew.filter.component.MarketApi;
import com.leew.filter.dto.Operand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.print.attribute.standard.Media;

@WebMvcTest(CalculatorApiController.class)
// SpringBootTest는 모든 Bean을 등록하여 테스트하기 때문에 로딩이 많이 걸린다.
// WebMvcTest는 웹에만 특화된 것들만 로딩시키기 때문에 자원을 줄일 수 있다.
@AutoConfigureWebMvc // 이거까지 동시에 설정
@Import({Calculator.class, DollarCalculator.class})
public class CalculatorApiControllerTest {
    // Controller 갔더니, Calculator를 주입받고 있다. -> Import로 Bean 등록시켜야 한다.
    // Calculator 갔더니, 또 iCalculator 주입받고 있다. -> DollarCalculator Bean 등록시켜야 한다.
    // mocking처리할 것이기 때문에 marketAPI는 mockBean으로 받기

    @MockBean
    private MarketApi marketApi;

    @Autowired
    private MockMvc mockMvc; // mocking을 MVC로 처리한다.

    @BeforeEach
    public void init() {
        Mockito.when(marketApi.connect()).thenReturn(3000);
    }

    // TODO: Controller-GetMapping Test
    @Test
    public void sumTest() throws Exception {
        String url = "http://localhost:9091/calc/sum";

        mockMvc.perform(
                MockMvcRequestBuilders.get(url)
                        .queryParam("x", "10")
                        .queryParam("y", "10")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.content().string("60000")
        ).andDo(
                MockMvcResultHandlers.print()
        );

        // fail : 200 Expected // Got 404 : Parameter가 안 들어가면 404 에러 발생
    }

    // TODO : Controller PostMapping Test
    @Test
    public void minusTest() throws Exception {
        Operand req = new Operand();
        req.setX(10);
        req.setY(10);

        String url = "http://localhost:9091/calc/minus";

        // throws JsonProcessingException
        String json = new ObjectMapper().writeValueAsString(req);

        mockMvc.perform( // throws Exception
                MockMvcRequestBuilders.post(url) // get/post/delete/push 모두 같은 이름의 함수가 존재
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json) // dto 바로 넣지 않고 json으로 변환하여 전달
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.result").value("0")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.response.resultCode").value("OK")
        ).andDo(
                MockMvcResultHandlers.print()
        );
    }
}
