package com.leew.filter.controller;

import com.leew.filter.component.Calculator;
import com.leew.filter.dto.Operand;
import com.leew.filter.dto.Res;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.leew.filter.component.ICalculator;

@RestController
@RequestMapping("/calc")
@RequiredArgsConstructor
public class CalculatorApiController {

    private final Calculator calculator;

    @GetMapping("/sum")
    public int sum(@RequestParam("x") int x, @RequestParam("y") int y) {
        return calculator.sum(x, y);
    }

    @GetMapping("/minus")
    public int minus(@RequestParam("x") int x, @RequestParam("y") int y) {
        return calculator.minus(x, y);
    }

    @PostMapping("/minus")
    public Res minus(@RequestBody Operand req) {

        int result = calculator.minus(req.getX(), req.getY());
        Res res = new Res();
        res.setResult(result);
        res.setResponse(new Res.Body());

        return res;
    }
}
