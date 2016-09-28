package me.dong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 클라이언트 요청/응답에 대한 로그를 기록하는 AOP 예제
 */
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)  //aop 실행을 위한 설정
public class AopApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopApplication.class, args);
	}
}
