package ch18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class C5_ConvertColeection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<String> lst = Arrays.asList("Toy","Box","Box","Toy"); // 중복을 허용하는 list
		ArrayList<String> list = new ArrayList<>(lst);
		
		
		
		for(String s : list) {
			System.out.print(s.toString() + '\t');
		}
		System.out.println();
		
		HashSet<String> set = new HashSet<>(list);
		list = new ArrayList<>(set);
		
		for(String s : list) {
			System.out.print(s.toString() + '\t');
		}
		System.out.println();

	}

}
