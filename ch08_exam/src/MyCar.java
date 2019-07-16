class Car {
	int fuel;
	int velocity;

	Car() {
		fuel = 10;
		velocity = 0;
	}
	void doAccel() {
		velocity = velocity +1;
		fuel = fuel -1;
	}
	void doBreak() {
		velocity = velocity -1;
	}
	void getStatus() {
		System.out.print("연료량 " + fuel);
		System.out.println(" 속도 " + velocity);
	}

}

public class MyCar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Car Benzcar = new Car();
		Benzcar.getStatus();
		
		Benzcar.doAccel();
		Benzcar.doAccel();
		Benzcar.getStatus();
		
		Benzcar.doBreak();
		Benzcar.getStatus();
		
		Car Lolslois = new Car();
		Lolslois.doAccel();
		Lolslois.doAccel();
		Lolslois.doAccel();
		Lolslois.doAccel();
		
		System.out.println("-------------------------------");
		System.out.print(" 롤스로이스 차 상태 : "); 
		Lolslois.getStatus();
		System.out.print(" 벤츠 차 상태 : ");
		Benzcar.getStatus();
		
	}

}
