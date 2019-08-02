import java.util.Arrays;

class INum1 {
	private int num;

	public INum1(int num) {
		this.num = num;
	}
	

	
}

public class F4_ArrayObjEquals {

	public static void main(String[] args) {
		INum1[] ar1 = new INum1[3];
		INum1[] ar2 = new INum1[3];
		
		ar1[0] = new INum1(1);
		ar2[0] = new INum1(1);

		ar1[1] = new INum1(2);
		ar2[1] = new INum1(2);
		
		ar1[2] = new INum1(3);
		ar2[2] = new INum1(3);
		
		System.out.println(Arrays.equals(ar1, ar2));
	}
	

}

//두 배열에 동일한 값으로 초기화된 서로 다른 인스턴스를 각각 저장하였다.
//그러나 배열의 비교 결과는 false이다.

// 인스턴스의 내용이 아닌 참조값이 비교된다.
