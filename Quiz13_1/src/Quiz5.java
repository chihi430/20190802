import java.util.Scanner;

public class Quiz5 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		
		int num = 0;
		String[] str = new String[10];
		str[num] = sc.nextLine();
		
//		int nLen = str.length();
//		String[] strArr1 = new String[nLen];
//		
//		for(int i = 0; i<nLen;i++) {
//			strArr1[i] = str.subString(i,i+1);
//		}
//		
//		String[] strArr2 = str.split("");
//		char[] strArr3 = str.toCharArray;
		
		
		System.out.println("단어를 입력하시오");
		
		for (int j = str[num].length(); j<str[num].length(); j++){  
        	char filt = str[num].charAt(j);
        	System.out.println(filt);

        }
		
        for (int i = str[num].length()-1; i>=0; i--){
            	char reverse2 = str[num].charAt(i); 	
            	System.out.println(reverse2);
        }
        
        
//    	if(reverse1.equals(str[num])) {
//    		System.out.println(reverse3);
//    		System.out.println("화문입니다.");
//    	}
//    	else {
//    		System.out.println(reverse2);
//    		System.out.println("화문이 아닙니다.");
//    	}
	}
}
