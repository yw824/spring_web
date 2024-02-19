package com.leew.hello.dto;

import lombok.*;

@Data // Getter + Setter + toString + equals 모두 자동으로 생성
// @Getter // Getter 생성
// @Setter // Setter 생성
@NoArgsConstructor // 디폴트 생성자
@AllArgsConstructor // 모든 변수를 받는 생성자
public class FilterDTO {

    private String name;
    private int age;
}
