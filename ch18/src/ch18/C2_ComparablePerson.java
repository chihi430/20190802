package ch18;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;


class Person implements Comparable<Person>{
	private String name;
	private int age;
	
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {

		return name+" : " + age;
	}
	@Override
	public int compareTo(Person p) {

		//return this.age -p.age;
		
	return p.age - this.age;
	}
	
//	@Override
//	public int compareTo(Person p) {
//
//		return p.name.compareTo(this.name);
//	}	
	
}

public class C2_ComparablePerson {

	public static void main(String[] args) {
		Set<Person> tree=new TreeSet<>();
		tree.add(new Person("SON",37));
		tree.add(new Person("HONG",53));
		tree.add(new Person("JEON",22));
		
		for(Person p : tree)
			System.out.println(p);
	}

}

// 반복자의 인스턴스 참조 순서는 오름차순을 기준으로 한다는 특징이 있다.