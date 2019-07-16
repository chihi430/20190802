
class Book {
	int price;
	int num = 0;
	String title;

	Book(Book copy) 
	{
		title = copy.title;
		price = copy.price;
	}
	Book(String t, int p) 
	{
		title = t;
		price = p;
	}


	void print() 
	{
		System.out.println("제    목 : " + title);
		System.out.println("가    격 : " + price);
//		System.out.println("주문수량 : " + num);
//		System.out.println("합계금액 : " + price * num);
	}
}

public class MyBook {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Book book1 = new Book("자바 프로그래밍", 10000);
		book1.print();
		
		Book book2 = new Book(book1);
		book1.title = "자바 디자인 패턴";
		book1.print();

	}

}

//생성자는 클래스 이름과 같은 메서드명을 가진다.
// 생성자는 메서드이지만 리턴형이 없다.
// 디폴트 생성자(파라미터 없는 애)는 자동으로 생성한다.
// 파라미터가 있는 생성자를 만들면 (오버로딩 하면)
// 디폴트 생성자가 자동으로 생성되지 않는다.


