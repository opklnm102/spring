package com.dong.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  //모든 필자를 인자로 받는 생성자 새성
@NoArgsConstructor  //디폴트 생성자 생성
public class Customer {
	private Integer id;
	private String firstName;
	private String lastName;

}
