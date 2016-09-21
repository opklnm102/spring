package com.dong.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dong.domain.Customer;

@Repository
/*
 * @Transactional을 클래스 수준에 설정
 * DI 컨테이너에서 가져오고 해당 클래스에 속한 메소드를 호출하면 DB트랜잭션이 자동으로 이루어짐
 * 1. 메소드가 제대로 실행되면 DB트랜잭션이 커밋
 * 2. 실행도중 오류가 발생하면 DB 트랜잭션이 롤백-> checked exception일 경우 롤백되지 않는다.
 * DI 컨테이너는 각 메소드 앞뒤에 처리를 추가한 클래스를 동적으로 생성
 */
@Transactional 
public class CustomerRepository {

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	//NamedParameterJdbcTemplate의 query()를 사용하여 SQL실행 결과를 객체형태로 가져온다.
	private static final RowMapper<Customer> customerRowMapper = (rs, i) -> {
		Integer id = rs.getInt("id");
		String firstName = rs.getString("first_name");
		String lastName = rs.getString("last_name");
		return new Customer(id, firstName, lastName);
	};

	public List<Customer> findAll() {
		List<Customer> customers = jdbcTemplate.query("SELECT id, first_name, last_name FROM customers ORDER BY id",
				customerRowMapper);

		return customers;
	}

	public Customer findOne(Integer customerId) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", customerId);
		return jdbcTemplate.queryForObject("SELECT id, first_name, last_name FROM customers WHERE id=:id", param,
				customerRowMapper);
	}

	public Customer save(Customer customer) {
		//업데이트용 SqlParameterSource를 만든다. BeanPropertySqlParameterSource를 사용하면 객체에 포함된 필드 이름과 값을 매핑한 SqlParameterSource가 자동으로 작성
		SqlParameterSource param = new BeanPropertySqlParameterSource(customer);
		
		//업데이트 계열의 SQL을 실행하기 위해 update() 호출
		if (customer.getId() == null) {  //id가 null이면 insert문
			jdbcTemplate.update("INSERT INTO customers(first_name, last_name) " + "VALUES(:firstName, :lastName)",
					param);
		} else {  //아니면 update문
			jdbcTemplate.update("UPDATE customers SET first_name=:firstName, " + "last_name=:lastName WHERE id=:id",
					param);
		}
		return customer;
	}

	public void delete(Integer customerId) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", customerId);
		//delete문을 실행할 때도 update() 호출
		jdbcTemplate.update("DELETE FROM customers WHERE id=:id", param);
	}
}
