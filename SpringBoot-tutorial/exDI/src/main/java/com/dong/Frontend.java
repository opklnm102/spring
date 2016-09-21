package com.dong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * DI컨테이너가 Bean을 injection하도록 구현
 * App클래스에 구현한 처리를 정의할 클래스
 */
@Component
public class Frontend {

	/*
	 * DI컨테이너는 @Autowired를 붙인 필드를 포함한 클래스를 관리 자동으로 찾아내어 주입 이와 같은 구조를
	 * auto-wiring라고 부른다.
	 */
	@Autowired // DI컨테이너가 주입해야 할 필드를 의미
	ArgumentResolver argumentResolver;

	@Autowired
	Calculator calculator;

	public void run() {
		System.out.println("Enter 2 numbers like 'a b': ");
		Argument argument = argumentResolver.resolve(System.in);
		int result = calculator.calc(argument.getA(), argument.getB());
		System.out.println("result = " + result);
	}

}
