package me.dong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.dong.domain.Customer;
import me.dong.repository.CustomerRepository;

/*
 * 이해를 돕기 위해 CustomerRepository 메소드를 단순 호출함
 * id가 존재하는지 검사하는 로직도 필요, 여기선 생략
 */
@Service
@Transactional
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	public List<Customer> findAll() {
		return customerRepository.findAllOrderByName();
	}
	
	//페이징 처리
	public Page<Customer> findAll(Pageable pageable) {
		return customerRepository.findAllOrderByName(pageable);
	}
	
	public Customer findOne(Integer id){
		return customerRepository.findOne(id);
	}
	
	public Customer create(Customer customer){
		return customerRepository.save(customer);
	}
	
	public Customer update(Customer customer){
		return customerRepository.save(customer);
	}
	
	public void delete(Integer id){
		customerRepository.delete(id);
	}
}
