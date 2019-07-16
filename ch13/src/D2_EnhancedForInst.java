class Box{
	private String contents;
	private int boxNum;
	
	Box(int num, String cont){
		boxNum = num;
		contents = cont;
	}
		public int getBoxNum() {
			return boxNum;
		}
		public String toString() {
			return contents;
		}
	
}



public class D2_EnhancedForInst {
	public static void main(String[] ages) {


		Box[] ar = new Box[6];
		
		ar[0] = new Box(101,"Coffe");
		ar[1] = new Box(202,"Computer");
		ar[2] = new Box(303,"Apple");
		ar[3] = new Box(404, "Dress");
		ar[4] = new Box(505, "Fairy-tale book");
		ar[5] = new Box(606, "good day");
		
		for(Box e: ar) {
			if(e.getBoxNum() == 606) {
				System.out.println(e);
			}
		}	
	}
}
