package me.dong;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

import me.dong.domain.Customer;
import me.dong.repository.CustomerRepository;;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ExRestApplication.class)
@WebAppConfiguration
// 속성을 덮어쓴다. 실제 App에서 데이터가 지속되는 DB를 사용하고 있다면 테스트에서만 인 메모리 DB를 선택적으로 사용할 수 있다.
@IntegrationTest({ "server.port:0", "spring.datasource.url:jdbc:h2:mem:bookmark;DB_CLOSE_ON_EXIT=FALSE" })
public class ExRestApplicationTests2 {

	@Autowired
	CustomerRepository customerRepository; // 테스트 데이터를 주입하기 위해

	@Value("${local.server.port}")
	int port;

	Customer customer1;
	Customer customer2;

	// 테스트 초기화
	// 테스트 데이터를 모두 삭제한 후 데이터를 다시 넣는다.
	// JUnit테스트는 실행 순서가 정해져 있지 않으므로 상태를 초기화 해야함
	@Before
	public void setUp() {
		customerRepository.deleteAll();
		customer1 = new Customer();
		customer1.setFirstName("Taro");
		customer1.setLastName("Yamada");
		customer2 = new Customer();
		customer2.setFirstName("Ichiro");
		customer2.setLastName("Suzuki");

		customerRepository.save(Arrays.asList(customer1, customer2));
		RestAssured.port = port;  //Rest-assured에서 사용할 포트 설정
	}

	// 모든 고객정보 획득 API 테스트
	@Test
	public void testGetCustomers() throws Exception {
		when().get("/api/customers")  //GET 메소드로 HTTP 요청
		.then()
		.statusCode(HttpStatus.OK.value())
		.body("numberOfElements", is(2))  //body()로 JSON형식의 응답 필드값 확인
		.body("content[0].id", is(customer2.getId()))
		.body("content[0].firstName", is(customer2.getFirstName()))
		.body("content[0].lastName", is(customer2.getLastName()))
		.body("content[1].id", is(customer1.getId()))
		.body("content[1].firstName", is(customer1.getFirstName()))
		.body("content[1].lastName", is(customer1.getLastName()));
	}

	// 신규 고객정보 작성 API 테스트
	@Test
	public void testPostCustomers() throws Exception {
		Customer customer3 = new Customer();
		customer3.setFirstName("Nobita");
		customer3.setLastName("Nobi");

		given().body(customer3)  //요청 내용 설정. Rest-assured를 사용하면 RestTemplate을 사용할 때 보다 테스트 코드가 간결해서 보기 쉬워진다.
		.contentType(ContentType.JSON)
		.and()
		.when().post("/api/customers")
		.then()
		.statusCode(HttpStatus.CREATED.value())
		.body("id", is(notNullValue()))
		.body("firstName", is(customer3.getFirstName()))
		.body("lastName", is(customer3.getLastName()));
		
		when().get("/api/customers")
		.then()
		.statusCode(HttpStatus.OK.value())
		.body("numberOfElements", is(3));
		
	}

	// 고객 한명의 정보 삭제 API 테스트
	@Test
	public void testDeleteCustomers() throws Exception {
		when().delete("api/customers/{id}", customer1.getId())
		.then()
		.statusCode(HttpStatus.NO_CONTENT.value());
		
		when().get("/api/customers")
		.then()
		.statusCode(HttpStatus.OK.value())
		.body("numberOfElements", is(1));
	}
}
