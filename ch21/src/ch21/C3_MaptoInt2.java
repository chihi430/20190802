package ch21;

import java.util.Arrays;
import java.util.List;

public class C3_MaptoInt2 {

	public static void main(String[] args) {
		List<String> ls = Arrays.asList("Box","Robot","Simple");
		
		ls.stream().mapToInt(s -> s.length())
				   .forEach(n -> System.out.print(n+"\t"));
		
		System.out.println();

	}
}
