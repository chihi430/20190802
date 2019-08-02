package ch18;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


class Num1{
	private int num;
	
	public Num1(int n) {
		num=n;
	}

	@Override
	public String toString() {
		return String.valueOf(num);
	}
}

public class B2_HashSetEqualityOne {

	public static void main(String[] args) {
		HashSet<Num1> set = new HashSet<>();
		set.add(new Num1(7799));
		set.add(new Num1(9955));
		set.add(new Num1(7799));
		
		
		System.out.println("인스턴스 수 : " + set.size());
				
		//for-each를 이용한 전체 출력
		for(Num1 n:set)
			System.out.print(n.toString()+'\t');
		System.out.println();
	}

}

// 출력 결과를 통해 동일 인스턴스가 저장되지 않음을 알 수 있다.
// 동일 인스턴스에 대한 기준은? (ㅁㅎ르겠다는내용