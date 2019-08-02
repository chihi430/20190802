import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class Counter4{
	int count = 0;
	ReentrantLock criticObj = new ReentrantLock();
	public void increment() {
		criticObj.lock();
		
		try {
			count++;
		}finally {
			criticObj.unlock();
		}
	}
	
	public void decrement() {
		criticObj.lock();
		
		try {
			count--;			
		}finally {
			criticObj.unlock();
		}
		
	}
	public int getCount() {return count;}
}

public class C4_MutualAccessReentrantLock {
	public static Counter4 cnt = new Counter4();

	public static void main(String[] args) throws InterruptedException{
		Runnable task1 = () ->{
			for(int i = 0; i<1000;i++)
				cnt.increment();
		};
		
		Runnable task2 = () ->{
			for(int i = 0; i<1000;i++)
				cnt.decrement();
		};
		
		ExecutorService exr = Executors.newFixedThreadPool(2);
		exr.submit(task1);
		exr.submit(task2);
		exr.shutdown();
		exr.awaitTermination(100, TimeUnit.SECONDS);
		System.out.println(cnt.getCount());
	}

}
/*
풀 안의 쓰레드의 수를 작업의 수에 맞게 유동적으로 관리한다.
→ 초기 쓰레드와 코어 쓰레드 개수는 0개, 최대 쓰레드 수는 integer 데이터타입이 가질 수 있는 최대
값.
만약 쓰레드가 60초동안 아무일도 하지 않으면 쓰레드를 종료시키고 쓰레드풀에서 제거한다.
*/