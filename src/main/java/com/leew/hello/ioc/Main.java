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

        // SpringBoot의 컨테이너 객체인 Context 객체를 가져와서, 그들이 관리하는 클래스 중
        // Base64Encoder를 가져온다. (Component로 Annotation 설정했기에 가능)
        Base64Encoder base64Encoder = context.getBean(Base64Encoder.class); // 여기서 에러 발생
        UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);
        Encoder encoder = new Encoder(urlEncoder);

        String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";
        String result = encoder.encode(url);
        System.out.println(result);
    }
}
