package ch10_8;

class SimpleBox{
	private int num;
	
	
	SimpleBox(int num){
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num+1;
	}
	
}

public class ThisUseEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleBox box = new SimpleBox(5);
		System.out.println(box.getNum()); //값을 세팅하고싶다 SimpleBox 메소드
		
		box.setNum(10); //값을 임위로 바꿀수있게하고싶다 getter setter
		System.out.println(box.getNum());

	}

}
