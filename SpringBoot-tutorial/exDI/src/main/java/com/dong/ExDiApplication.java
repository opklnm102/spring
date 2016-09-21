package com.dong;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
//@Import(AppConfig.class)  //JavaConfig를 읽어 들이기 위해 선언
/*
 * DI컨테이너에 등록할 Bean을 하나하나 정의하기가 번거롭다.
 * Bean을 DI컨테이너에 자동으로 등록하는 component scan기능 사용
 * 클래스의 패키지 내부에 있는 모든 클래스를 검색하여 @Component와 
 * 같은 특정 Annotation이 붙은 클래스를 찾아 DI컨테이너에 등록
 * 대상패키지를 변경하려면 basePackages 속성에 패키지를 지정
 */
@ComponentScan
/*
 * Frontend클래스가 하는일을 App클래스에서도 할 수 있게 하는 CommandLineRunner 인터페이스
 */
public class ExDiApplication implements CommandLineRunner {
	
	//CommandLineRunner 인터페이스를 구현하는 클래스는 DI을 할 수 있다.
	@Autowired
	ArgumentResolver argumentResolver;
	
	@Autowired
	Calculator calculator;
	
	//CommandLineRunner 인터페이스의 run()은 앞서 사용한 Frontend클래스의 run()과 같은 역할
	@Override
	public void run(String... strings) throws Exception {
		System.out.println("Enter 2 numbers like 'a b': ");
		Argument argument = argumentResolver.resolve(System.in);
		int result = calculator.calc(argument.getA(), argument.getB());
		System.out.println("result = " + result);
	}

	public static void main(String[] args) {

		/*
		 * SpringApplication.run()으로 스프링부트 App을 실행
		 * 첫번째 인자로 @@EnableAutoConfiguration이 붙은 클래스 지정. 반환값 DI컨테이너 본체인 ApplicationContext
		 * 여기서는 close()로 닫을 수 있는 ConfigurableApplicationContext가 반환
		 * Java7에서 도립한 try-with-resources문안에 변수를 선언하여 try안쪽 처리가 끝나면 자동으로 close()가 호출되어
		 * DI컨테이너가 소멸되고 App이 실행을 마치게 한다.
		 */
		try (ConfigurableApplicationContext context = SpringApplication.run(ExDiApplication.class, args)) {
//			Scanner scanner = new Scanner(System.in);
//			System.out.println("Enter 2 numbers like 'a b': ");
//			
//			//이런 방식으로 App클래스에서 context.getBean()을 호출할 일이 많아지면 관련 모듈이 늘어나고 코드가 지저분 
//			//-> 이문제를 해결하는 것이 DI
//			ArgumentResolver argumentResolver = context.getBean(ArgumentResolver.class);
//			Argument argument = argumentResolver.resolve(System.in);
//			
//			//getBean()을 사용해 DI 컨테이너에서 Calculator타입 인스턴스를 받는다.
//			//실제 인스턴스는 DI 컨테이너가 알아서 찾아주므로 application쪽에서는 신경쓰지 않아도 된다.
//			//ExDiApplication클래스는 DI컨테이너로부터 Calculator를 받아오기만 할뿐 어떻게 구성되었는지 알 수 없다.
//			//DI컨테이너를 사용하면 App안에 있는 모듈사이의 의존성이 느슨해지고 독립성이 커진다.
//			Calculator calculator = context.getBean(Calculator.class);
//			int result = calculator.calc(argument.getA(), argument.getB());
//			System.out.println("result = " + result);
			
			Frontend frontend = context.getBean(Frontend.class);
			frontend.run();
		}
	}
}
