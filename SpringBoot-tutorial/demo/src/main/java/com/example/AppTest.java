package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)  //JUnit 테스트에서 스프링의 기능을 사용
@SpringApplicationConfiguration(classes = DemoApplication.class)  //테스트용 ApplicationContext를 만든다.(@EnableAutoConfiguration을 붙인 클래스 지정)
@WebAppConfiguration  //Web Application 테스트. 이 어노테이션과 @SpringApplicationConfiguration을 조합하면 내장서버 실행 가능
/*
 * 통합 테스트 기능 활성화
 * value속성으로 테스트할 때 사용할 속성을 덮어쓸 수 있다.
 * server.port속성에 테스트용 서버 포트 지정
 * 0을 지정하면 현재 비어있는 포트 사용(다른 프로세스가 사용하고 있는 포트를 피할 수 있어 테스트를 실패하는 일이 안일어나게 해줌)
 */
@IntegrationTest("server.port:0")  
public class AppTest {
	
	@Value("${local.server.port}")  //포트번호 주입. @Value("${속성이름})
	int port;
	
	//실행한 내장 서버에 접속하기 위해 HTTP클라이언트 준비
	//RestTemplate에 비해 오류가 발생해도 바로 처리하는 기능
	//쉽게 Basic 인증을 설정할 수 있는 기능과 같은 테스트용 설정 기능이 추가되어 있다.
	RestTemplate restTemplate = new TestRestTemplate();  

	@Test
	public void testHome(){
		//HTTP의 GET으로 요청. (url, 직렬화에 사용할 타입)
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port, String.class);  
		
		//ResponseEntity에는 HTTP응답의 상태 코드, 헤더, 내용이 저장되어 있다.
		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		assertThat(response.getBody(), is("Hello Gradle!"));
	}
}
