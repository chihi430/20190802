package ch18;

import java.util.HashMap;

public class E1_HashMapCollection {

	public static void main(String[] args) {
		HashMap<Integer, String> map = new HashMap<>();
		
		//key-Value 기반 데이터 저장
		map.put(45, "Brown");
		map.put(23, "James");
		map.put(37, "Martin");
		
		
		//데이터 탐색
		System.out.println("23 번 " + map.get(23));
		System.out.println("37 번 " + map.get(37));
		System.out.println("45 번 " + map.get(45));
		System.out.println();
		
		//데이터 삭제
		map.remove(37);
		
		//데이터 삭제확인
		System.out.println("37번:" + map.get(37));
	}

}
// hashmap은 키값에 따라
//
// hashmap은 차례대로 못돈다
