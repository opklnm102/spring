package me.dong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring-Boot, JPA로 질문/답변 게시판 구현 과정 Study
 * https://slipp.net/wiki/pages/viewpage.action?pageId=25529113
 */
@SpringBootApplication
public class SpringBootJpaQnABoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJpaQnABoardApplication.class, args);
        System.out.println("Boot Run!!");
    }
}
