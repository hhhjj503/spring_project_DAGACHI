package dagachi.board.model.hjModel;

public class AdminMembershipDetailsDto {
	
	private int admin_Num;
	private String admin_Name;
	private String admin_Id;
	private String admin_Password;
	private String admin_Email;
	private String admin_PhoneNumber;
	private String dept;
	private String admin_Author;
	private boolean admin_Supervisor;
	
	@Override
	public String toString() {
		return "AdminMembershipDetailsDto [admin_Num=" + admin_Num + ", admin_Name=" + admin_Name + ", admin_Id="
				+ admin_Id + ", admin_Password=" + admin_Password + ", admin_Email=" + admin_Email
				+ ", admin_PhoneNumber=" + admin_PhoneNumber + ", dept=" + dept + ", admin_Author=" + admin_Author
				+ ", admin_Supervisor=" + admin_Supervisor + "]";
	}
	public boolean getAdmin_Supervisor() {
		return admin_Supervisor;
	}
	public void setAdmin_Supervisor(boolean admin_Supervisor) {
		this.admin_Supervisor = admin_Supervisor;
	}
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
