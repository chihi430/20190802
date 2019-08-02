import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class C2_ExecutorsDemo2 {

	public static void main(String[] args) {
		Runnable task1 =() ->{
			try {
				Thread.sleep(0);
			} catch (Exception e) {

			}
			String name = Thread.currentThread().getName();
			System.out.println(name+" : "+(5+7));
		};
		Runnable task2 =() ->{
			String name = Thread.currentThread().getName();
			System.out.println(name+" : "+(7-5));
		};
		
		ExecutorService exr = Executors.newFixedThreadPool(2);
		// 쓰레드 풀을 이용해서 동시에 수행될 수 있는 쓰레드의 총량을 제한하고있다.
		exr.submit(task1);
		exr.submit(task2);
		exr.submit(()->{
			String name = Thread.currentThread().getName();
			System.out.println(name+" : "+(5*7));
		});
		
		exr.shutdown();

	}

}
/*
풀 안의 쓰레드의 수를 작업의 수에 맞게 유동적으로 관리한다.
→ 초기 쓰레드와 코어 쓰레드 개수는 0개, 최대 쓰레드 수는 integer 데이터타입이 가질 수 있는 최대
값.
만약 쓰레드가 60초동안 아무일도 하지 않으면 쓰레드를 종료시키고 쓰레드풀에서 제거한다.
*/