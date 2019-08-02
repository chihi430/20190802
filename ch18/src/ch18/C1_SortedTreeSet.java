package ch18;

import java.util.Iterator;
import java.util.TreeSet;

public class C1_SortedTreeSet {

	public static void main(String[] args) {
		TreeSet<Integer> tree = new TreeSet<Integer>();
		tree.add(3);
		tree.add(1);
		tree.add(2);
		tree.add(4);
			
		System.out.println("인스턴스 수 : " + tree.size());
				
		//for-each를 이용한 전체 출력
		for(Integer n:tree)
			System.out.print(n.toString()+'\t');
		System.out.println();
		
		//Iterator 반복자에 의한 반복
		for(Iterator<Integer> itr = tree.iterator(); itr.hasNext() ; )
			System.out.print(itr.next().toString()+'\t');
		System.out.println();
	}

}

// 반복자의 인스턴스 참조 순서는 오름차순을 기준으로 한다는 특징이 있다.