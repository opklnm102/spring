package me.dong.api;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import me.dong.domain.Customer;
import me.dong.service.CustomerService;

@RestController // Rest 웹서비스의 엔드포인트인 컨트롤러에 선언
@RequestMapping("api/customers") // Rest 웹서비스에 접속하기 위한 경로 지정
public class CustomerRestContorller {

	@Autowired
	CustomerService customerService;

	// 모든 고객 정보 얻기
	// 'api/customers' - GET으로 접속하면 메소드 호출
//	@RequestMapping(method = RequestMethod.GET)
//	List<Customer> getCustomers() {
//		List<Customer> customers = customerService.findAll();
//		return customers; // @RequestMapping이 붙은 반환값은 직렬화되어 HTTP응답 내용 안에
//							// 설정(default로 자바객체는 JSON형식으로 직렬화)
//	}
	
	/*
	 * 페이징 처리
	 * 응답 파라미터에 설정한 page, size가 Pageable객체에 매핑
	 * @PageableDefault -> 파라미터를 지정하지 않으면 기본값으로 설정 됨(page=0, size=20)
	 */
	@RequestMapping(method = RequestMethod.GET)
	Page<Customer> getCustomers(@PageableDefault Pageable pageable){
		Page<Customer> customers = customerService.findAll(pageable);
		return customers;
	}

	// 고객 1명의 정보 얻기
	// 인자이름('id')과 placeholder('{id}')의 값이 일치해야한다.
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	Customer getCustomer(@PathVariable Integer id) {
		Customer customer = customerService.findOne(id);
		return customer;
	}

	// 신규 고객정보 작성
//	@RequestMapping(method = RequestMethod.POST)
//	@ResponseStatus(HttpStatus.CREATED)  //201 CREATED반환, 아니면 200 OK반환
//	Customer postCustomers(@RequestBody Customer customer) {
//		return customerService.create(customer);
//	}
	
	/*
	 * 일반적으로 REST 웹서비스는 POST를 통해 새로 작성한 리소스에 접속하기 위한 URI를 HTTP 응답의 Location 헤더에 설정
	 * UriComponentsBuilder - 컨텍스트 상대 결로 URI를 쉽게 만들게 해준다.
	 */
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<Customer> postCustomers(@RequestBody Customer customer, UriComponentsBuilder uriBuilder){  //
		Customer created = customerService.create(customer);
		URI location = uriBuilder.path("api/customers/{id}")
				.buildAndExpand(created.getId()).toUri();  //{id}에는 buildAndExpand()에서 넘겨준 값으로 치환됨
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		return new ResponseEntity<>(created, headers, HttpStatus.CREATED);
	}

	// 고객 1명의 정보 업데이트
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	Customer putCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
		customer.setId(id);
		return customerService.update(customer);
	}

	// 고객 1명의 정보 삭제
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)  //204 NO_CONTENT반환
	void deleteCustomer(@PathVariable Integer id){
		customerService.delete(id);
	}
}
