package me.dong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

/*
개인취향 JPA 사용기 따라하기
http://likelink.co.kr/39693
 */
@SpringBootApplication
public class ExJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExJpaApplication.class, args);
        System.out.println("Spring boot Run!!!!!!!");
        System.out.println("args = [" + args + "]");
    }
}