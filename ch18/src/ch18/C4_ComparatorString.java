package ch18;

import java.util.Comparator;
import java.util.TreeSet;


//String 클래스의 정렬 기준은 사전 편찬순이다.
//이를 길이 순으로 바꾸는 문장
//

class StringComparator implements Comparator<String>{
	public int compare(String s1, String s2) {
		// 길이가 동일한 데이터는 추가되지 않는다.
		return s1.length() - s2.length();
	}
}

public class C4_ComparatorString {

	public static void main(String[] args) {
		//TreeSet<String> tree = new TreeSet<>();
		TreeSet<String> tree = new TreeSet<>(new StringComparator());
		
		tree.add("Box");
		tree.add("Rabbit");
		tree.add("Robot1");
		tree.add("Robot");
		
		
		for(String s : tree) {
			System.out.print(s.toString() + '\t');
		}
		System.out.println();

	}

}
