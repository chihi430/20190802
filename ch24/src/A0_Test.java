class myTest1{
	public void print() {
		int num=30;
		for(int i=0; i<num;i++) {
			System.out.println("MyTest1 : " + i);
		}
	}
}
class myTest2{
	public void print() {
		int num=30;
		for(int i=0; i<num;i++) {
			System.out.println("MyTest2 : " + i);
		}
	}
}

public class A0_Test {

	public static void main(String[] args) {
		myTest1 test1 = new myTest1();
		myTest2 test2 = new myTest2();
		test1.print();
		System.out.println("=======================");
		test2.print();

	}

}
