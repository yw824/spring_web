package com.leew.hello.java;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.leew.hello.java.dto.Car;
import com.leew.hello.java.dto.User;

import java.util.Arrays;
import java.util.List;

public class ObjectMapperEx {
    public static void main(String[] args) throws JsonProcessingException {
        /*
        TODO : 자동차 사용자 파일인 sample.json 파일을 가져와서
         */

        User user = new User();
        user.setName("홍길동");
        user.setAge(20);

        Car car1 = new Car();
        car1.setName("K5");
        car1.setCarNumber("11가 1111");
        car1.setType("sedan");

        Car car2 = new Car();
        car2.setName("Q5");
        car2.setCarNumber("22나 2222");
        car2.setType("SUV");

        List<Car> carList = Arrays.asList(car1, car2);
        user.setCars(carList);

        // System.out.println(user); // 윈도우에서 하면 ms949라 인코딩 에러 발생
        // Object Mapper의 디폴트는 utf-8인데, mac의 디폴트는 utf-8이라 에러 발생 안함
        // JSON의 디폴트 인코딩이 utf-8


        // Object Mapper를 통해, 이 DTO 객체를 JSON으로 변경시켜 보자.
        // 변경이 안될 때를 대비하여 Exception Throw 설정
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);

        // System.out.println(json);
        // 위 JSON 결과를 복사하여, JSON Validator 웹페이지에 넣어 주면, 맞는 JSON 형식인지 판단 가능
        // https://jsonlint.com/

        // snakeCase -> camelCase 변경 수행(Car.java에서, json의 car_number, TYPE 키워드로)
        // 여기까지가 하나의 Object Mapper로 DTO에 접근하는 방식

        JsonNode jsonNode = objectMapper.readTree(json);
        String _name = jsonNode.get("name").asText();
        int _age = jsonNode.get("age").asInt(); // 우리가 미리 key를 알 경우에만 가능
        // 아니면, key를 돌리면서 직접 노드를 건드리며 알아내야 한다.

        System.out.println("name: " + _name); // 가능

        // 그런데,, 이제 Car를 가져오려면 ??? jsonNode에서는, List를 읽어 저장할 수 있는 함수가 없음

        // Car의 개별 원소가 하나의 새로운 노드이다. Cars는 노드를 원소로 가진 배열 노드이다.
        // 그래서 Cars를 root로 가진 새로운 Node를 만든 다음 가져와야 한다.
        JsonNode cars = jsonNode.get("cars");

        // Cars는 배열이므로, 배열의 기능을 수행할 노드인 ArrayNode로 변환하여, 각 원소를 가져오게끔 설정
        ArrayNode arrayNode = (ArrayNode)cars;

        // 맵을 우리가 객체로 바꾼다든지, 여러 가지 object를 가지고 json이 아닌, 우리가 원하는 클래스로 매핑 가능
        List<Car> _cars = objectMapper.convertValue(
                arrayNode, new TypeReference<List<Car>>() {}
        ); // 우리가 원하는 List<Car> 라는 타입의 레퍼런스로 변환시킬 것이다.
        // 중괄호 안의 내용은, 구체적으로 어떻게 변환시킬 것인가에 대한 코드, 이번 실습에서는 작성하지 않을 예정

        System.out.println(_cars);

        // json Node라는 객체 클래스에서는 특정 json의 값을 바꿀 수 없도록 해 놓았다.
        // ObjectNode 클래스는, 이 값을 바꿀 수 있다.
        ObjectNode objectNode = (ObjectNode) jsonNode;
        objectNode.put("name", "scottToSomeoneElse");
        objectNode.put("age", 21);

        System.out.println(objectNode.toPrettyString());
    }
}
