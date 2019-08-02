import java.util.Arrays;

public class F2_CopyOfSystem {

	public static void main(String[] args) {
		double[] Org = {1.1, 2.2, 3.3, 4.4, 5.5};
		double[] Cpy = new double[3];
		
		
		// 미리 만들어져 있는 배열에 복사한다.
		System.arraycopy(Org, 1, Cpy, 0, 3);
		
		for(double d: Cpy)
			System.out.print(d+"\t");
		System.out.println();


			
		
	}

}

//앞의 예제와 달리 배열을 새로 생성하지 않고 배열에 복사를 하려는
// 경우에는 이와같이 메서드를 호출하면 안된다.
