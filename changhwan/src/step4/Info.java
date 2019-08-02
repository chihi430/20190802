package step4;

public class Info {
				String name;
		String phoneNumber;
		String email;
		
		public Info(String name,String phoneNumber) {
			this.name = name;
			this.phoneNumber = phoneNumber;
		}
		public Info(String name,String phoneNumber,String email) {
			this.name = name;
			this.phoneNumber = phoneNumber;
			this.email = email;
		}
		
		public void showPhoneInfo() {
			System.out.println("Name : " + name);
			System.out.println("PhoneNumber : " + phoneNumber);
			if(email != null)
				System.out.println("Email : "+email);
			System.out.println("---------------------------");
		}
		
	}


