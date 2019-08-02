package projectHashMap;

public class PhoneInfo {
	private String name;
	private String phone;
	private String email;
	
	public PhoneInfo(String name, String phone, String email) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String toString() {
		return "이름 : "+name+"\t 전화번호 : " + phone+ "\t 이메일 : "+email;
	}

	

}
