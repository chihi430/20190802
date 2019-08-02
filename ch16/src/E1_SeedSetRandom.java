import java.util.Random;

public class E1_SeedSetRandom {

	public static void main(String[] args) {
		Random rand = new Random(System.currentTimeMillis());
		
		for(int i =0; i<7; i++) {
			System.out.print(rand.nextInt(10)+" ");
		}
	}

}
// Math 클래스에 정의된 메서드는 모두 static으로 선언 되어 있다.
// 즉 Math는 기능의 제공이 목적일 뿐,
// 인스턴스의 생성을 목적으로 정의된 클래스는 아니다.