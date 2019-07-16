package MultiDimArrayDemo;

public class MultiDimArrayDemo1 {
	public static void main(String[] args){
		
        String[][] names = {{"Mr. ","Mrs. ","Ms. "},
                             {"Smith","Jones"}};
        System.out.println(names[0][1]+ names[1][1]); // Mr. Smith
        System.out.println(names[0][2]+ names[1][0]); // Ms. Jones
   }

}
