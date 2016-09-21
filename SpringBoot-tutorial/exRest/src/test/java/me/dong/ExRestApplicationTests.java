package me.dong;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import me.dong.domain.Customer;
import me.dong.repository.CustomerRepository;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ExRestApplication.class)
@WebAppConfiguration
//속성을 덮어쓴다. 실제 App에서 데이터가 지속되는 DB를 사용하고 있다면 테스트에서만 인 메모리 DB를 선택적으로 사용할 수 있다.
@IntegrationTest({ "server.port:0", "spring.datasource.url:jdbc:h2:mem:bookmark;DB_CLOSE_ON_EXIT=FALSE" })
public class ExRestApplicationTests {

	@Autowired
	CustomerRepository customerRepository;  //테스트 데이터를 주입하기 위해

	@Value("${local.server.port}")
	int port;
	String apiEndpoint;
	RestTemplate restTemplate = new TestRestTemplate();

	Customer customer1;
	Customer customer2;

	//모든 고객정보 획득 API가 반환하는 값은 Page타입이지만 이 클래스는 RestTemplate로 얻을 수 없다.
	//테스트를 위해 JSON형식의 응답을 매핑하는 자바클래스 준비(Rest Api를 이용할 때 꼭 필요한 부분은 아님)
	//나중에 테스트하는 content, numberOfElements 필드 정의
	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)  //JSON형식에 지금 존재하는 필드를 제외하고는 무시
	static class Page<T> {
		private List<T> content;
		private int numberOfElements;
	}

	//테스트 초기화
	//테스트 데이터를 모두 삭제한 후 데이터를 다시 넣는다.
	//JUnit테스트는 실행 순서가 정해져 있지 않으므로 상태를 초기화 해야함
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
		apiEndpoint = "http://127.0.0.1:" + port + "/api/customers";
	}

	//모든 고객정보 획득 API 테스트
	@Test
	public void testGetCustomers() throws Exception {
		ResponseEntity<Page<Customer>> response = restTemplate.exchange(apiEndpoint, HttpMethod.GET, null /* body, header */,
				new ParameterizedTypeReference<Page<Customer>>() {
				});  //RestTemplate의 제네릭 요청 메소드 exchange()를 사용해 테스트요청을 보냄. 반환타입이 제네릭일 때 parameterizedTypeReference를 사용하여 타입을 지정 
		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		assertThat(response.getBody().getNumberOfElements(), is(2));
		
		Customer c1 = response.getBody().getContent().get(0);
		assertThat(c1.getId(), is(customer2.getId()));
		assertThat(c1.getFirstName(), is(customer2.getFirstName()));
		assertThat(c1.getLastName(), is(customer2.getLastName()));
		
		Customer c2 = response.getBody().getContent().get(1);
		assertThat(c2.getId(), is(customer1.getId()));
		assertThat(c2.getFirstName(), is(customer1.getFirstName()));
		assertThat(c2.getLastName(), is(customer1.getLastName()));
	}
	
	//신규 고객정보 작성 API 테스트
	@Test
	public void testPostCustomers() throws Exception {
		Customer customer3 = new Customer();
		customer3.setFirstName("Nobita");
		customer3.setLastName("Nobi");
		
		ResponseEntity<Customer> response = restTemplate.exchange(apiEndpoint, HttpMethod.POST, new HttpEntity<>(customer3) /* HttpEntity를 사용해 요청내용을 만듦*/, Customer.class);
		assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
		Customer customer = response.getBody();
		assertThat(customer.getId(), is(notNullValue()));
		assertThat(customer.getFirstName(), is(customer3.getFirstName()));
		assertThat(customer.getLastName(), is(customer3.getLastName()));
	
		assertThat(
				restTemplate.exchange(apiEndpoint, 
						HttpMethod.GET,
						null,
						new ParameterizedTypeReference<Page<Customer>>() {
						}).getBody().getNumberOfElements(), is(3));
	}
	
	//고객 한명의 정보 삭제 API 테스트
	@Test
	public void testDeleteCustomers() throws Exception {
		ResponseEntity<Void> response = restTemplate.exchange(apiEndpoint + "/{id}" /* 경로안에 포함된 파라미터는 플레이스홀더로 표현*/, HttpMethod.DELETE, null /* body, header */,
				Void.class, Collections.singletonMap("id", customer1.getId()));
		assertThat(response.getStatusCode(), is(HttpStatus.NO_CONTENT));
		
		assertThat(
				restTemplate.exchange(apiEndpoint,
						HttpMethod.GET,
						null,
						new ParameterizedTypeReference<Page<Customer>>() {
						}).getBody().getNumberOfElements(), is(1));	
	}
}
