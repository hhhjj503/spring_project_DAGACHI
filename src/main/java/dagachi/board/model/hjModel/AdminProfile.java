package dagachi.board.model.hjModel;

import java.util.Date;

public class AdminProfile {

	private int file_No;
    private String admin_Id;
    private String orgFileName;
    private String StoredFileName;
    private String Content_Type;
    private String file_Size;
    private Date regDate;
    
    
    
	public AdminProfile() {
		super();
	}

	public AdminProfile(int file_No, String admin_Id, String orgFileName, String storedFileName, String content_Type,
			String file_Size, Date regDate) {
		super();
		this.file_No = file_No;
		this.admin_Id = admin_Id;
		this.orgFileName = orgFileName;
		StoredFileName = storedFileName;
		Content_Type = content_Type;
		this.file_Size = file_Size;
		this.regDate = regDate;
	}

	public int getFile_No() {
		return file_No;
	}

	public void setFile_No(int file_No) {
		this.file_No = file_No;
	}

	public String getAdmin_Id() {
		return admin_Id;
	}

	public void setAdmin_Id(String admin_Id) {
		this.admin_Id = admin_Id;
	}

	public String getOrgFileName() {
		return orgFileName;
	}

	public void setOrgFileName(String orgFileName) {
		this.orgFileName = orgFileName;
	}

	public String getStoredFileName() {
		return StoredFileName;
	}

	public void setStoredFileName(String storedFileName) {
		StoredFileName = storedFileName;
	}

	public String getContent_Type() {
		return Content_Type;
	}

	public void setContent_Type(String content_Type) {
		Content_Type = content_Type;
	}

	public String getFile_Size() {
		return file_Size;
	}

	public void setFile_Size(String file_Size) {
		this.file_Size = file_Size;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "AdminProfile [file_No=" + file_No + ", admin_Id=" + admin_Id + ", orgFileName=" + orgFileName
				+ ", StoredFileName=" + StoredFileName + ", Content_Type=" + Content_Type + ", file_Size=" + file_Size
				+ ", regDate=" + regDate + "]";
	} 

}
