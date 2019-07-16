// 마이크로소프트사 : 윈도우 10에서 사용될 프린터는 다음의 규격을 준수해야합나디.
//						Printable 을 구현해 주세요
// 아래한글, MS Word : 인쇄는 print 만 호출하면 되는군요. 알겠습니다.
// 삼성, 엡소 : 프린터는 Printable 인터페이스 규격에 맞춰서 만들겠습니다.

//폴리모피즘을 사용하는 이유 업무 분담 효율적이다! 규격에맞게 사용하기 편하다.
interface Printable{
	void print(String doc);
}

class PrnDrvSamsung implements Printable{
	public void print(String doc) {
		System.out.println(doc+ "\nForm Samsung : Black-White Ver");
	}
}	
class PrnDrvEpson implements Printable{
	public void print(String doc) {
		System.out.println(doc+ "\nForm Epson : Black-White Ver");
	}
}


public class Polymorphism2_1 {
	
	public static void main(String[] args) {
		String doc = "프린터로 출력을 합니다.";
		
		Printable prn1 = new PrnDrvSamsung();
		prn1.print(doc);
		
		Printable prn2 = new PrnDrvEpson();
		prn2.print(doc);
		
	}

}
