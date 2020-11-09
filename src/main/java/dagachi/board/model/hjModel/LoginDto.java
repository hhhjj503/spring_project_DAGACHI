package dagachi.board.model.hjModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
	String admin_id;
	String admin_password;
	String id_Chk;
	String admin_email;
	String admin_Password;
	Boolean useCookie;
}
