class Apple{
	
	String name;
	int price;
	
	Apple(String name, int price){//안에 내용 없으면 디폴트 생성자
		
		//클래스에 있는 name참조하려면 this를 써라 
		this.name = name;
		this.price = price;
	}

	public Apple() {
		super();//부모의 기운 (클래스) 있든 없든 상관없다

	}
	
}
public class Mytest {

	public static void main(String[] args) {


	}

}
