package ch21;

import java.util.Arrays;
import java.util.stream.Stream;


public class B2_StringSream2 {

	public static void main(String[] args) {
		String[] names = {"YOON","LEE","PARK"};
		
		// 스트림 생성
		Stream<String> stm = Arrays.stream(names);
		
		// 최종 연산 진행
		stm.forEach(s-> System.out.println(s));

	}
}
// forEach() : 요소를 하나씩 꺼내옴
// 어렵게 짤 필요없어 람다 스트림 다나옴
// 프린트ln 3개 쓸거 이렇게 어렵게 표현한 것이다.