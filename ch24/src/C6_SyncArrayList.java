import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class C6_SyncArrayList {
	public static List<Integer> lst = Collections.synchronizedList(new ArrayList<Integer>());
//	public static List<Integer> lst = new ArrayList<Integer>();

	public static void main(String[] args) throws InterruptedException{
		for(int i =0;i<16;i++)
			lst.add(i);
		System.out.println(lst);
		
		Runnable task = () ->{
			synchronized (lst) {
				ListIterator<Integer> itr =lst.listIterator();
				
				while(itr.hasNext())
					itr.set(itr.next()+1);
				//System.out.println(lst);
			}
		};
		
		ExecutorService exr = Executors.newFixedThreadPool(3);
		exr.submit(task);
		exr.submit(task);
		exr.submit(task);

		
		exr.shutdown();
		exr.awaitTermination(100, TimeUnit.SECONDS);
		System.out.println(lst);
	}

}
/*
풀 안의 쓰레드의 수를 작업의 수에 맞게 유동적으로 관리한다.
→ 초기 쓰레드와 코어 쓰레드 개수는 0개, 최대 쓰레드 수는 integer 데이터타입이 가질 수 있는 최대
값.
만약 쓰레드가 60초동안 아무일도 하지 않으면 쓰레드를 종료시키고 쓰레드풀에서 제거한다.
*/