package ch18;

import java.util.List;
import java.util.ArrayList;

public class A1_ArrayListCollection {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		
		//인스턴스 저장 : 순서 있슴, 중복허용
		list.add("Toy");
		list.add("Box");
		list.add("Box");
		list.add("RoBoot");
		
		// 인스턴스 참조
		for(int i =0; i<list.size(); i++){
			System.out.print(list.get(i)+'\t');
		}
		System.out.println();

		//첫번째 인스턴스 삭제
		list.remove(0);
		
		//삭제후 인스턴스 참조
		for(int i =0; i<list.size(); i++) {
			System.out.print(list.get(i) +'\t');
		}
		System.out.println();

	}

}
//ArrayList<E> 클래스
//배열 기반 자료구조이지만 공간의 확보 및 확장은
// 리스트 처럼 ArrayList 인스턴스가 스스로 처리한다.