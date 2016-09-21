package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//request를 받아들이는 컨트롤러 클래스임을 나타냄
@RestController  
//스프링부트에서 대단히 중요한 요소
//다양한 설정이 자동으로 수행, 기존 스프링에 필요했던 설정파일이 필요없어짐
@EnableAutoConfiguration  
public class App {

    @RequestMapping("/")  //이 메소드가 HTTP요청을 받아들인다.
    String home() {
        return "Hello World!!!";  //HTTP 응답이 되어 출력
    }

    public static void main(String[] args) {  //스프링부트 App을 실행하는데 필요한 처리를 작성
        SpringApplication.run(App.class, args);  //@EnableAutoConfiguration이 붙은 클래스를 첫번째 인자로
    }
}
