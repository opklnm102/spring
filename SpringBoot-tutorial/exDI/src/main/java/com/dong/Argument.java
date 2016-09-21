package com.dong;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/*
 * @Data를 붙이면 컴파일(.class 파일 생성)시 각 필드의 setter, getter와 toString(),
 * equals(), hashCode()가 생성되므로 코드가 간결
 * final을 붙이면 setter는 생성X
 */
@Data
@RequiredArgsConstructor
public class Argument {
	private final int a;
	private final int b;
}
