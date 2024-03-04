package com.leew.filter.component;

import org.springframework.stereotype.Component;

@Component // 사용하는 대상이 component이면, 얘도 component여야 한다.
public class MarketApi {
    public int connect() {
        return 1100;
    }
}
