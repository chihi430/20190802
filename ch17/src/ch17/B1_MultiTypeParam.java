package ch17;

class Box7<L,R>{ 
	private L left; // 왼쪽 수납 공간
	private R right; // 오른쪽 수납 공간
	
	public void set(L o, R r) {
		
		left = o;
		right = r;
	}

	@Override
	public String toString() {
		return left+" & "+right;
	}
	
}
public class B1_MultiTypeParam {
	public static void main(String[] args) {
		Box7<String, Integer> box = new Box7<String, Integer>();
		//왼쪽 정수형 오른쪽 스트링


		box.set("Apple", 25);
		System.out.println(box);
	}
}

