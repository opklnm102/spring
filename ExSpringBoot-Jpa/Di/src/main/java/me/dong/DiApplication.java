package me.dong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * DI를 이해하기 위해 클라이언트에게 현재 시간에 대한 timestamp와 문자열을
 * JSON or XML로 출력하는 예제 작성
 */
@SpringBootApplication
public class DiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiApplication.class, args);
	}
}
