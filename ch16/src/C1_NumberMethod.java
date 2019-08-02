
public class C1_NumberMethod {

	public static void main(String[] args) {
		// number클래스의 추상 메서드 호출의 예
		Integer num1 = new Integer(29);	
		System.out.println(num1.intValue());
		System.out.println(num1.doubleValue());
		
		Double num2 = new Double(3.14);
		System.out.println(num2.intValue());
		System.out.println(num2.doubleValue());
	}

}
// java.lang.Number 모든 래퍼 클래스가 상속하는 클래스
// java.lang.Number에 정의된 추상 메소드들
// intValue() lonValue() doubleValue()
// -> 즉 래퍼 인스턴스에 저장된 값을 원하는 형의 기본 자료형 값으로 반환 할 수 있다.