import java.util.Arrays;

class INum2 {
	private int num;

	public INum2(int num) {
		this.num = num;
	}

	@Override
	public boolean equals(Object obj) {
		if (this.num == ((INum2) obj).num) // 두 인스턴스가 동일 인스턴스이면 true
			return true;
		else
			return false;
		
		//이렇듯 object 클래스에 정의된 equals 메소드는 참조 값 비교를 한다.
	}

	
	// 따라서 Array 클래스의 equals 메소드가 두 배열의 내용 비교를 하도록 하려면
	// 비교 대상의 equals 메소드를 내용 비교의 형태로 오버라이딩 해야한다.
	
}

public class F5_ArrayObjEquals2 {

	public static void main(String[] args) {
		INum2[] ar1 = new INum2[3];
		INum2[] ar2 = new INum2[3];
		
		ar1[0] = new INum2(1);
		ar2[0] = new INum2(1);

		ar1[1] = new INum2(2);
		ar2[1] = new INum2(2);
		
		ar1[2] = new INum2(3);
		ar2[2] = new INum2(3);
		
		System.out.println(Arrays.equals(ar1, ar2));
	}
	

}

//두 배열에 저장된 데이터의 수 , 순서 그리고 내용이 같을때 true를 반환한다.
//(배열의 길이가 다를 경우에는 false를 반환한다.)
