package dagachi.board.model.hjModel;

import java.util.Date; 

import lombok.Getter;
import lombok.Setter;


@Getter
public class OwnerNoticeDto {

	int owner_Notice_Num;
    String owner_Notice_Content;
    String owner_Notice_Title;
    Date owner_Notice_Created_Day;
    int admin_Num;
    
	public int getOwner_Notice_Num() {
		return owner_Notice_Num;
	}
	public String getOwner_Notice_Content() {
		return owner_Notice_Content;
	}
	public String getOwner_Notice_Title() {
		return owner_Notice_Title;
	}
	public Date getOwner_Notice_Created_Day() {
		return owner_Notice_Created_Day;
	}
	public int getAdmin_Num() {
		return admin_Num;
	}
	public void setOwner_Notice_Num(int owner_Notice_Num) {
		this.owner_Notice_Num = owner_Notice_Num;
	}
	public void setOwner_Notice_Content(String owner_Notice_Content) {
		this.owner_Notice_Content = owner_Notice_Content;
	}
	public void setOwner_Notice_Title(String owner_Notice_Title) {
		this.owner_Notice_Title = owner_Notice_Title;
	}
	public void setOwner_Notice_Created_Day(Date owner_Notice_Created_Day) {
		this.owner_Notice_Created_Day = owner_Notice_Created_Day;
	}
	public void setAdmin_Num(int admin_Num) {
		this.admin_Num = admin_Num;
	}
    
    
}
