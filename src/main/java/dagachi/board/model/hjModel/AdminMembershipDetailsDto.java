package dagachi.board.model.hjModel;

import lombok.Getter;
import lombok.Setter;

public class AdminMembershipDetailsDto {
	
	int admin_Num;
	String admin_Name;
	String admin_Id;
	String admin_Password;
	String admin_Email;
	String admin_PhoneNumber;
	String dept;
	String admin_Author;
	
	public int getAdmin_Num() {
		return admin_Num;
	}
	public void setAdmin_Num(int admin_Num) {
		this.admin_Num = admin_Num;
	}
	public String getAdmin_Name() {
		return admin_Name;
	}
	public void setAdmin_Name(String admin_Name) {
		this.admin_Name = admin_Name;
	}
	public String getAdmin_Id() {
		return admin_Id;
	}
	public void setAdmin_Id(String admin_Id) {
		this.admin_Id = admin_Id;
	}
	public String getAdmin_Password() {
		return admin_Password;
	}
	public void setAdmin_Password(String admin_Password) {
		this.admin_Password = admin_Password;
	}
	public String getAdmin_Email() {
		return admin_Email;
	}
	public void setAdmin_Email(String admin_Email) {
		this.admin_Email = admin_Email;
	}
	public String getAdmin_PhoneNumber() {
		return admin_PhoneNumber;
	}
	public void setAdmin_PhoneNumber(String admin_PhoneNumber) {
		this.admin_PhoneNumber = admin_PhoneNumber;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getAdmin_Author() {
		return admin_Author;
	}
	public void setAdmin_Author(String admin_Author) {
		this.admin_Author = admin_Author;
	}
	
	
}
