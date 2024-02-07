package com.leew.hello.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DeleteApiController {

    @DeleteMapping("/delete/{userId}")
    public void delete(@PathVariable("userId") String userId, @RequestParam String account) {
        // http://localhost:9090/api/delete/100?account=user100
        System.out.println("userId: " + userId); // userId: 100
        System.out.println("account: " + account); // account: user100

        // delete 자체가 삭제 ->
        // 있어서 삭제한 것도 200, 없는데 다시 삭제 요청한 것도 200
        // 굳이 "없어요" 말할 필요는 없다. 그냥 없는 값이 된 상태가 완료된 상태로 간주
    }
}
