package com.dong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.dong.domain.Customer;
import com.dong.repository.CustomerRepository;

@EnableAutoConfiguration
@ComponentScan
public class ExJdbcApplication implements CommandLineRunner {

	@Autowired
	CustomerRepository customerRepository; 

	@Override
	public void run(String... arg0) throws Exception {
		
//		//SQL결과를 객체에 mapping
//		String sql = "SELECT id, first_name, last_name FROM customers WHERE id = :id";
//		SqlParameterSource param = new MapSqlParameterSource()
//				.addValue("id", 1);
//
//		//RowMapper<Customer>를 익명클래스 이용
////		Customer result = jdbcTemplate.queryForObject(sql, param, new RowMapper<Customer>() {
////
////			@Override
////			public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
////				return new Customer(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
////			}
////		});
//		
//		//'(인자) -> 반환값'형식의 람다 표현식 이용
//		Customer result = jdbcTemplate.queryForObject(sql, param, 
//				(rs, rowNum) ->  new Customer(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name")));
//		
//		System.out.println("result" + result);
		
		//데이터 추가
		Customer created = customerRepository.save(new Customer(null, "ggg", "ggg"));
		System.out.println(created + " is created!");
		
		//데이터 표시
		customerRepository.findAll().forEach(System.out::println);  //메소드 래퍼런스
	}

	public static void main(String[] args) {
		SpringApplication.run(ExJdbcApplication.class, args);
	}
}
