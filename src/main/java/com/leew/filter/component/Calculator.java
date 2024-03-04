package com.leew.filter.component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Calculator {
    private final ICalculator iCalculator;

    // iCalculator가 될 후보군이 DollarCalculator밖에 없기 떄문에
    // 자동으로 Autowired 된다.

    public int sum(int x, int y) {
        this.iCalculator.init();
        return this.iCalculator.sum(x, y);
    }

    public int minus(int x, int y) {
        this.iCalculator.init();
        return this.iCalculator.minus(x, y);
    }
}
