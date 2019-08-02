package ch18;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


class AgeComparator implements Comparator<Integer>{
	public int compare(Integer n1, Integer n2) {
		return n2.intValue() - n1.intValue();
	}
}

public class E4_ComparatorTreeMap {

	public static void main(String[] args) {
		HashMap<Integer, String> map = new HashMap<>();
		
		//key-Value 기반 데이터 저장
		map.put(45, "Brown");
		map.put(23, "James");
		map.put(37, "Martin");
		
		// key만 담고 있는 컬렉션 인스턴스 생성
		Set<Integer> ks = map.keySet();
		
		
		//전체 key 출력(for-each문 기반)
		for(Integer n : ks)
			System.out.print(n.toString()+'\t');
		System.out.println();
		
		
		//전체 value 출력(for-each문 기반)
		for(Integer n:ks)
			System.out.print(map.get(n).toString()+'\t');
		System.out.println();
		
		
		//전체 value 출력(반복자 기반)
		for(Iterator<Integer> itr = ks.iterator(); itr.hasNext(); )
			System.out.print(map.get(itr.next()) + '\t');
		System.out.println();
		
	}

}
// hashmap은 키값에 따라 , map은 출석부다 = 번호! 데이터는 중복되두 됨 동명이인 있을수 있다.
//
// hashmap은 차례대로 못돈다
