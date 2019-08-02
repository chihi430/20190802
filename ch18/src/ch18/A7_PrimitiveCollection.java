package ch18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

// 배열 기반 리스트를 연결 기반 리스트로...

public class A7_PrimitiveCollection {

	public static void main(String[] args) {
		
		LinkedList<Integer> list = new LinkedList<>();
		
		//저장과정에서 오토박싱
		list.add(10);
		list.add(20);
		list.add(30);
		
		int n ;
		for(Iterator<Integer> itr=list.iterator(); itr.hasNext(); ) {
			n=itr.next();  // 오토언박싱
			System.out.print(n+"\t");
		}
		System.out.println();
		
	}
}
