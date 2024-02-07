package com.leew.hello.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leew.hello.dto.PutRequestDTO;

/* PUT API BODY
{
  "name": "scott",
  "age": 20,
  "car_list": [
    {
      "name": "BMW",
      "car_number": "11가 1234",
    },
    {
      "name": "SONATA",
      "car_number": "22니 5678",
    }
  ]
}
 */

@RestController
@RequestMapping("/api")
public class PutApiController {
    @PutMapping("/put")
    public PutRequestDTO put(@RequestBody PutRequestDTO putRequestDTO) {
        System.out.println(putRequestDTO);

        // response는 ??
        return putRequestDTO; // 받았던 자료 그냥 리턴시키면 스프링부트에서 자동으로 JSON 매핑됨
    }
}
