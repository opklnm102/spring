package me.dong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/*
 * REST(REpresentational State Transfer) - C/S간 데이터를 주고받는데 필요한 SW아키텍처 스타일 중 하나
 * Server쪽에서 관리하고 있는 정보 중 Client에 제공해야할 정보를 리소스 형태로 추려냄
 * 그리고 리소스에 CRUD조작을 한 후 HTTP 메소드(GET, POST, PUT, DELETE)와 연결된 웹api(rest api)를 통해 Client에 제공
 * 
 * 공개할 웹 api
 * 모든 고객정보 얻기 - GET - /api/customers - 200 OK
 * 고객 1명 정보 얻기 - GET - /api/customers/{id} - 200 OK
 * 신규 고객정보 작성 - POST - /api/customers - 201 CREATED
 * 고객 1명 정보 업데이트 - PUT - /api/customers/{id} - 200 OK
 * 고객 1명 정보 삭제 - DELETE - /api/customers/{id} - 204 NO CONTENT
 */
@EnableAutoConfiguration
@ComponentScan
public class ExRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExRestApplication.class, args);
		System.out.println("run spring boot!!");
	}
}
