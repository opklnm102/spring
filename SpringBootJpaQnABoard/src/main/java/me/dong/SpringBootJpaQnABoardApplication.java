package me.dong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Spring-Boot, JPA로 질문/답변 게시판 구현 과정 Study
 * https://slipp.net/wiki/pages/viewpage.action?pageId=25529113
 */
@SpringBootApplication
@EnableJpaAuditing  // CreatedDate, LastModifiedDate를 쓸 수 있게 해줌
public class SpringBootJpaQnABoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJpaQnABoardApplication.class, args);
        System.out.println("Boot Run!!");
    }
}
