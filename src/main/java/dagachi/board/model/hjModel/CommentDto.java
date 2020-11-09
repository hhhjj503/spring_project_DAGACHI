package dagachi.board.model.hjModel;

import java.util.Date;

public class CommentDto {
	
	String cmtWriter;
    String cmtPassword;
    String cmtContents;
    String cmtDate;
    int owner_Notice_Num;
    int cmtNumber;
    
	public int getCmtNumber() {
		return cmtNumber;
	}
	public void setCmtNumber(int cmtNumber) {
		this.cmtNumber = cmtNumber;
	}
	public int getOwner_Notice_Num() {
		return owner_Notice_Num;
	}
	public void setOwner_Notice_Num(int owner_Notice_Num) {
		this.owner_Notice_Num = owner_Notice_Num;
	}
	public String getCmtWriter() {
		return cmtWriter;
	}
	public void setCmtWriter(String cmtWriter) {
		this.cmtWriter = cmtWriter;
	}
	public String getCmtPassword() {
		return cmtPassword;
	}
	public void setCmtPassword(String cmtPassword) {
		this.cmtPassword = cmtPassword;
	}
	public String getCmtContents() {
		return cmtContents;
	}
	public void setCmtContents(String cmtContents) {
		this.cmtContents = cmtContents;
	}
	public String getCmtDate() {
		return cmtDate;
	}
	public void setCmtDate(String cmtDat) {
		this.cmtDate = cmtDat;
	}
    
    

}
