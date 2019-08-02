
public class test {

    public static void main(String[] args){ 
    String s = "work'list'Man'ager" ; 
    String[] arr = s.split("'"); 
    StringBuilder sb=new StringBuilder(); 
    for(String st: arr) 
     sb.append(st); 
    System.out.println(sb.toString()); 
    } 
}
