package dagachi.board.model.hjModel;

import java.io.File;  
import java.util.Date;

public class FileUploadDto {

	int File_No;
	int notice_Num;
    String org_File_Name;
    String stored_FileName;
    String contents_Type;
    long file_Size;
    Date RegDate;
    
    public int getFile_No() {
		return File_No;
	}
	public void setFile_No(int file_No) {
		File_No = file_No;
	}
    
	public String getContents_Type() {
		return contents_Type;
	}
	public void setContents_Type(String contents_Type) {
		this.contents_Type = contents_Type;
	}
	public void setFile_Size(long file_Size) {
		this.file_Size = file_Size;
	}
	public int getNotice_Num() {
		return notice_Num;
	}
	public void setNotice_Num(int notice_Num) {
		this.notice_Num = notice_Num;
	}
	public String getOrg_File_Name() {
		return org_File_Name;
	}
	public void setOrg_File_Name(String org_File_Name) {
		this.org_File_Name = org_File_Name;
	}
	public String getStored_FileName() {
		return stored_FileName;
	}
	public void setStored_FileName(String stored_FileName) {
		this.stored_FileName = stored_FileName;
	}
	public long getFile_Size() {
		return file_Size;
	}
	public void setFile_Size(int file_Size) {
		this.file_Size = file_Size;
	}
	public Date getRegDate() {
		return RegDate;
	}
	public void setRegDate(Date regDate) {
		RegDate = regDate;
	}
    
}

/*
 * create table fileUpload ( File_No int primary key auto_increment , Notice_Num
 * int not null , Org_File_Name varchar(200) not null , Stored_FileName
 * varchar(200) not null , Contents_Type varchar(200) not null , File_Size long
 * not null ,RegDate date not null );
 */
