package ch21;

import java.util.Arrays;
import java.util.List;

public class B4_ListStream {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("Toy","Robot","Box");
		
		list.stream().forEach(s-> System.out.println(s+"\t"));
		
		System.out.println();
	}
}

// 컬렉션 인스턴스를 대상으로 스트림 생성하기