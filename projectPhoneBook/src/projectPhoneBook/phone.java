package projectPhoneBook;


class PhoneInfo{
	 
	 private String name;//이름
	 private String phoneNumber;//핸드폰번호
	 private String birthday;//생년월일
	 
	 public PhoneInfo(String name, String phoneNumber, String birthday){
	  this.name = name;
	  this.phoneNumber = phoneNumber;
	  this.birthday = birthday;
	  
	 }
	 public PhoneInfo(String name, String phoneNumber){
	  this.name = name;
	  this.phoneNumber = phoneNumber;
	  this.birthday = null;
	 }
	 public void showInfo(){
	  System.out.println(name+"\n"+phoneNumber);
	  if(birthday!=null)
	   System.out.println(birthday);
	 
	  System.out.println();
	  
	 }
	}

	public class phone {
	 public static void main(String ar[]){
	  PhoneInfo phone1 = new PhoneInfo("김지훈","010-3333-2225","900227");
	  PhoneInfo phone2 = new PhoneInfo("김원희","011-1111-2222");
	  
	  phone1.showInfo();
	  phone2.showInfo();
	  
	 }
	}
