package ch18;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

//
// enhanced for 문의 사용
//
public class A4_IteratorCollection {

	public static void main(String[] args) {
		List<String> list = new LinkedList<>();
		
		//인스턴스 저장
		list.add("Toy");
		list.add("Box");
		list.add("RoBoot");
		list.add("Box");

		Iterator<String> itr = list.iterator();
		
		while(itr.hasNext())
			System.out.print(itr.next()+'\t');
		System.out.println();
		
		itr = list.iterator();
		
		String str;
		while(itr.hasNext()) {
			str = itr.next();
			
			if(str.equals("Box"))
				itr.remove();
				
		}
		
		itr = list.iterator();
		
		while(itr.hasNext())
			System.out.print(itr.next()+'\t');
		System.out.println();
	}

}
