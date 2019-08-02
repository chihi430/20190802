package ch20;

interface HowLong{
	int len(String s); // 매개변수 하나, 반환 형 void
}

public class E4_OneParamAndReturn {
	public static void main(String[] args) {
		HowLong hl = s->s.length();
		
		System.out.println(hl.len("I am so Happy"));
	}

}
