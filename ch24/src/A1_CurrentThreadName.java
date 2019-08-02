
public class A1_CurrentThreadName {

	public static void main(String[] args) {
		Thread ct = Thread.currentThread();
		String name = ct.getName();
		System.out.println(name);
	}
}
// 프로그램 내에서 실행의 흐름을 이루는 최소의 단위
// main메소드의 실행도 하나의 쓰레드에 의해 진행이 된다.