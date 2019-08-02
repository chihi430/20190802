package ch17;


class BoxA<T extends Number>{ 
	private T ob; 
	public void set(T o) {		
		ob = o;
	}
	public T get() {
		return ob;
	}
}
public class B4_BoundedBox {
	public static void main(String[] args) {
		Box9<Integer> iBox = new Box9<>();
		iBox.set(24);

		BoxA<Double> dBox = new BoxA<>();
		dBox.set(5.97);
		
		
		System.out.println(iBox.get());
		System.out.println(dBox.get());
	}
}
