package me.dong.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity  //JPA 엔티티 선언
@Table(name = "customers")  //엔티티에 대응하는 테이블 지정, 기본적으로 클래스이름과 동일하게 맞춘다.
@Data
@AllArgsConstructor
@NoArgsConstructor  //JPA 명세에 따라 엔티티클래스에는 디폴트생성자 필요.
public class Customer {
	@Id  //엔티티의 기본키인 필드에 선언
	@GeneratedValue  //DB가 기본키번호를 자동으로 매기도록 선언
	private Integer id;
	
	@Column(nullable = false)  //DB에 대응하는 컬럼의 이름, 제약조건 설정. 여기서는 NOT NULL 설정
	private String firstName;

	@Column(nullable = false)
	private String lastName;
}
