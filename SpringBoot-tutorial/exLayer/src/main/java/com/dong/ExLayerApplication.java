package com.dong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.dong.domain.Customer;
import com.dong.service.CustomerService;

@EnableAutoConfiguration
@ComponentScan
public class ExLayerApplication implements CommandLineRunner {
	
	@Autowired
	CustomerService customerService;
	
	@Override
	public void run(String... strings) throws Exception {
		//데이터 추가
		customerService.save(new Customer(1, "Nobita", "Nobi"));
		customerService.save(new Customer(2, "Takeshi", "Goda"));
		customerService.save(new Customer(3, "Suneo", "Honekawa"));
		
		//데이터 표시
		customerService.findAll().forEach(System.out::println);
	}

	public static void main(String[] args) {
		SpringApplication.run(ExLayerApplication.class, args);
	}
}
