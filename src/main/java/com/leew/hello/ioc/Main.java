package com.leew.hello.ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
// Main이라는 Application도 결국, SpringBootApplication이다.
// Ctrl 키 눌러서 내부 라이브러리 코드 보면,
// Configuration / SpringBootConfiguration / ComponentScan / Component 등 Annotation 설정
// 컴포넌트로 관리, Spring Bean으로 관리
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        ApplicationContext context = ApplicationContextProvider.getContext();

        Encoder encoder = null;
        String result = "";
        String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";

        // SpringBoot의 컨테이너 객체인 Context 객체를 가져와서, 그들이 관리하는 클래스 중
        // Base64Encoder를 가져온다. (Component로 Annotation 설정했기에 가능)
        // Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);

        /*
        // 생성자로 Base64Encoder 전달 및 동작
        encoder = context.getBean(Encoder.class);
        // 이미 Qualifier로 설정해 뒀기 때문에 굳이 전달 안해도 Base64Encoder로 설정되어 있을 것
        String result = encoder.encode(url);
        System.out.println(result);

        // 이미 만들어진 Encoder에 새롭게 urlEncoder 세팅 및 동작
        UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);
        encoder.setiEncoder(urlEncoder);

        result = encoder.encode(url);
        System.out.println(result);

        */

        encoder = context.getBean("urlEncode", Encoder.class);
        result = encoder.encode(url);
        System.out.println(result);
    }
}

// Encoder 자체도 2개 이상을 사용하고 싶은데 어떻게 해야 하지???
// Encoder를 컴포넌트로 두지 않고 bean으로 직접 등록하자.
@Configuration // "한 개의 클래스에서 여러 개의 bean을 등록할 거야" - 의 의미
class AppConfig {
    // 여러 개의 bean을 생성
    // 메소드 오버로딩 : 문제 발생할 수 있다.

    // Bean이 2개이다. 어떻게 구분 ??
    @Bean(name="base64Encode") // 이름을 붙여주면 된다.
    public Encoder encoder(Base64Encoder base64Encoder) {
        return new Encoder(base64Encoder);
    }

    @Bean(name="urlEncode")
    // Base64Encoder 클래스의 컴포넌트 키워드명과 겹치면 안된다. 그 이름으로 bean에 등록되기 때문
    // 교재에서는, 함수명 같아도 됐었는데, 여기서는 안된다. 함수명 다르게 하니까 작동한다.
    public Encoder encoder1(UrlEncoder urlEncoder) {
        return new Encoder(urlEncoder);
    }
}
