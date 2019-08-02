import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class C3_CallableDemo {

	public static void main(String[] args)
	throws InterruptedException,ExecutionException
	{
		Callable<Integer> task =() -> {
			int sum = 0;
			for(int i = 0; i<10;i++)
				sum+=i;
				return sum;
			
		};
		ExecutorService exr = Executors.newSingleThreadExecutor();
		Future<Integer> fur = exr.submit(task);
		
		Integer r = fur.get();
		System.out.println("result : "+r);
		exr.shutdown();

	}

}
/*
풀 안의 쓰레드의 수를 작업의 수에 맞게 유동적으로 관리한다.
→ 초기 쓰레드와 코어 쓰레드 개수는 0개, 최대 쓰레드 수는 integer 데이터타입이 가질 수 있는 최대
값.
만약 쓰레드가 60초동안 아무일도 하지 않으면 쓰레드를 종료시키고 쓰레드풀에서 제거한다.
*/