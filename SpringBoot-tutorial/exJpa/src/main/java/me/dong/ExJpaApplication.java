package me.dong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import me.dong.domain.Customer;
import me.dong.repository.CustomerRepository;

@EnableAutoConfiguration
@ComponentScan
public class ExJpaApplication implements CommandLineRunner {
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public void run(String... arg0) throws Exception {
		//데이터 추가
		Customer created = customerRepository.save(new Customer(
				null, "AAA", "aaa"));
		System.out.println(created + " is created!");
		
		/*
		 * 페이징 처리 - 한페이지마다 N건의 데이터를 가져오는것으로 정하여 M번째 페이지의 데이터를 가져오는 처리
		 */
		
		//Pageable인터페이스로 페이지 정보를 가져옮. 본체 클래스로 PageRequest
		//PageRequest(페이지 수, 한페이지가 포함하는 데이터수) - 페이지수는 0부터 시작
		Pageable pageable = new PageRequest(0, 3);
//		Page<Customer> page = customerRepository.findAll(pageable);  //지정한 페이지의 Customer데이터를 가져옮
		Page<Customer> page = customerRepository.findAllOrderByName(pageable);
	
		System.out.println("한 페이지당 데이터 수: " + page.getSize());
		System.out.println("현재 페이지: " + page.getNumber());
		System.out.println("전체 페이지 수: " + page.getTotalPages());
		System.out.println("전체 데이터 수: " + page.getTotalElements());
		
		page.getContent().forEach(System.out::println);  //해당 페이지의 데이터 리스트를 가져옮
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ExJpaApplication.class, args);
	}
}
