package ch18;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;




public class F1_SortCollections {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("Toy", "Box","Robot","Weapon");
		
		//정렬 이전 출력
		for(Iterator<String> itr = list.iterator(); itr.hasNext(); ) 
			System.out.print(itr.next()+'\t');
		System.out.println();
		
		//정렬
		Collections.sort(list);
		
		//정렬 이후 출력
		for(Iterator<String> itr = list.iterator(); itr.hasNext();) {
			System.out.print(itr.next()+'\t');
		}
		System.out.println();
	}

}
