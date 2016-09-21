package me.dong.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import me.dong.domain.Customer;

/*
 * 스프링 데이터
 * 데이터 저장소 조작을 위한 범용 기능을 제공하는 하위 프로젝트
 * RDBMS, MongoDB, Redis, Neo4J같은 데이터 저장소 조작 가능
 * 범용 리포지토리 클래스도 제공
 * 
 * JPA로 RDBMS를 조작하는 스프링데이터 JPA이용
 * JpaRepository에는 CRUD(create, Read, Update, Delete) 조작용 기본 메소드가 정의
 * 		1. findOne
 * 		2. save
 * 		3. findAll
 * 		4. delete
 * 상속한 인터페이스를 만드는 것만으로 아주쉽게 리포지토리 클래스를 만들 수 있다.
 * -> 인터페이스만 있으면 실행시 '실행 클래스' 생성
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	/*
	 * JpaRepository에 정의되지 않은 쿼리를 하려면 상속한 인터페이스에 메소드 추가
	 * JPQL로 쿼리를 작성할 수 있다.
	 * - JPQL(Java Persistence Query Language)
	 * 		JPA로 엔티티를 조작할 때 사용하는 쿼리 언어로 SQL과 닮았다.
	 * 		실행시 SQL로 변형되며 RDBMS 기능에 따라 제각각인 SQL언어들을 흡수
	 * 		특정 벤더에 의존하지 않는 쿼리 작성 가능
	 * 
	 * JPA는 SQL도 사용할 수 있다. nativeQuery = true 지정
	 * @Query("SELECT x FROM Customer x ORDER BY x.firstName, x.lastName", nativeQuery = true)
	 * JPA의 장점 활용X -> 벤더에 의존하는 구문을 꼭사용해야 하는 복잡한 쿼리 기술시에만 SQL을 사용
	 */
	//페이징 처리
	@Query("SELECT x FROM Customer x ORDER BY x.firstName, x.lastName")  //JPQL 기술시 @Query 붙임
	Page<Customer> findAllOrderByName(Pageable pageable); 
	
	@Query("SELECT x FROM Customer x ORDER BY x.firstName, x.lastName")  //JPQL 기술시 @Query 붙임
	List<Customer> findAllOrderByName();
}