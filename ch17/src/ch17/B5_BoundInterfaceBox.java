package ch17;

interface Eatable {
	public String eat();
}

class AppleB implements Eatable {
	public String toString() {
		return "I am an apple";
	}
	@Override
	public String eat() {
		return "It tastes so good!";
	}
}

class BoxB<T extends Eatable> {
	private T ob;

	public void set(T o) {
		ob = o;
	}

	public T get() {
		return ob;
	}
}

public class B5_BoundInterfaceBox {
	public static void main(String[] args) {
		BoxB<AppleB> Box = new BoxB<>();
		Box.set(new AppleB());

		AppleB ap = Box.get();
		

		
		System.out.println(ap);
	}
}
