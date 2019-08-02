public class A3_MakeThreadDemo2 {

	public static void main(String[] args) {
		Runnable task = () ->{
			try {
				Thread.sleep(3000);
			}catch (Exception e) {
			}
			int n1 = 10;
			int n2 = 20;
			String name = Thread.currentThread().getName();

			System.out.println(name+": "+(n1+n2));
			System.out.println("스레드 종료");
			
		};
		
		Thread t = new Thread(task);
		t.start();
		
		System.out.println("End " +Thread.currentThread().getName());
	}
}
// 1단계 Thread를 상속하는 클래스의 정의와 인스턴스 생성
// 2단계 쓰레드의 start메소드 호출