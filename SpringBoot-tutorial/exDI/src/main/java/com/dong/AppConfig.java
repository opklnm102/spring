package com.dong;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * - Bean 정의 파일 작성
	인터페이스에 어떤 실제 기능(Bean)을 제공할지는 DI컨테이너가 관리하도록 구현
		1. XML 정의
		2. 자바 클래스(JavaConfig) 정의(spring 3부터 도입)
 */
@Configuration  //JavaConfig용 클래스임을 컴파일러에 알린다.
public class AppConfig {
	
	//DI컨테이너가 관리할 Bean을 생성하는 메소드에 붙이는 Annotation
	//메소드 이름이 Bean이름
	//기본값으로 싱글톤으로 생성
	@Bean  
	Calculator calculator(){
		return new AddCalculator();
	}
	
	@Bean
	ArgumentResolver argumentResolver(){
		return new ScannerArgumentResolver();
	}
	
	@Bean
	Frontend frontend(){
		return new Frontend();
		/*
		 * @autowired를 사용하지 않을 경우
		 * Frontend frontend = new Frontend();
		 * frontend.setCalculator(calculator());
		 * frontend.setArgumentResolver(argumentResolver());
		 * return frontend;
		 */
	}
}
