package com.leew.hello.di;

public class Main {
    public static void main(String[] args) {
        // 이런 방식의 웹 주소가 있다고 하자.
        String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";

        // 요구사항 - TODO : base 64로 인코딩 해주세요!!

        // 방법 1 : 그냥 Encoder 바로 new로 생성해서 작동한다.
        Base64Encoder encoder64 = new Base64Encoder();
        System.out.println("Encoded String: " + encoder64.encode(url));

        // 추가 요구사항 - TODO : URL도 Encoding 해주세요!!
        // 그러면, 아무 생각 없이 바로 URLEncoding 객체 생성, 그러나 추상화를 더해 보자.
        IEncoder urlEncoder = new UrlEncoder();
        System.out.println(urlEncoder.encode(url));

        // Dependency Injection : Encoder를 동작하는 클래스는 외부에서 받는다.
        // 내가 원하는 객체로 주입만 시켜주면 된다.
        Encoder encoder = new Encoder(new Base64Encoder());
        // 내가 어떤 동작을 할 것인지 설정만 해 두면, 동작을 맡은 Encoder.encode()는 바뀌지 않는다.
        System.out.println(encoder.encode(url));
    }
}
