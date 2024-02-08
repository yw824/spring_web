package com.leew.hello.ioc;

import com.leew.hello.ioc.Base64Encoder;
import com.leew.hello.ioc.Encoder;
import com.leew.hello.ioc.IEncoder;
import com.leew.hello.ioc.UrlEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
// Main이라는 Application도 결국, SpringBootApplication이다.
// Ctrl 키 눌러서 내부 라이브러리 코드 보면,
// Configuration / SpringBootConfiguration / ComponentScan / Component 등 Annotation 설정
// 컴포넌트로 관리, Spring Bean으로 관리
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        ApplicationContext context = ApplicationContextProvider.getContext();

        String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";

        // SpringBoot의 컨테이너 객체인 Context 객체를 가져와서, 그들이 관리하는 클래스 중
        // Base64Encoder를 가져온다. (Component로 Annotation 설정했기에 가능)
        // Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);

        // 생성자로 Base64Encoder 전달 및 동작
        Encoder encoder = context.getBean(Encoder.class);
        // 이미 Qualifier로 설정해 뒀기 때문에 굳이 전달 안해도 Base64Encoder로 설정되어 있을 것
        String result = encoder.encode(url);
        System.out.println(result);

        // 이미 만들어진 Encoder에 새롭게 urlEncoder 세팅 및 동작
        UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);
        encoder.setiEncoder(urlEncoder);

        result = encoder.encode(url);
        System.out.println(result);
    }
}
