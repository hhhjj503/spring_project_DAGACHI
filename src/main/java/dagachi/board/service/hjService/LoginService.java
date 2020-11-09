package dagachi.board.service.hjService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dagachi.board.model.hjModel.AdminMembershipDetailsDto;

@Service
public class LoginService {

	
	@Autowired
	LoginDao dao;

	public LoginDao setDao() {
		return dao;
	}
	
	public void id_ChkUp(int id_Chk,String admin_email) {
		dao.id_ChkUp(id_Chk,admin_email);
	}
	
	//id_Chk 랜덤으로 준 값 가져오기
	public String getId_Chk(String id_Chk) {
		return dao.getId_Chk(id_Chk);
	}
	
	//랜덤준값과 입력한값이 같은지 확인
	public int id_id_Chk(String id_Chk) {
		return dao.id_id_Chk(id_Chk);
	}
	
	//일치하면 아이디 정보 보여주기
	public String getId(int id_Chk,String admin_email) {
		return dao.getId(id_Chk,admin_email);
	}
	
	//로그인시 아이디 존재여부 확인
	public int loginIdChk(String admin_id) {
		return dao.loginIdChk(admin_id);
	}
	
	//아이디가 존재하면 해당 아이디로 비밀번호 가져오기
	public String loginPwdchk(String admin_id) {
		return dao.loginPwdchk(admin_id);
	}
	
	//비밀번호 변경하기
	public void changePwd(String admin_Password,String admin_id,String admin_email) {
		dao.changePwd(admin_Password, admin_id, admin_email);
	}
	
	//비밀번호 변경2단계
	public int Chk_Id_Pwd(String admin_id,String admin_email) {
		return dao.Chk_Id_Pwd(admin_id, admin_email);
	}
	
	//비밀번호 최종변경
	public int changePwdcount(String admin_id,String admin_email,String pwdCode) {
		return dao.changePwdcount(admin_id, admin_email, pwdCode);
	}
	
	public AdminMembershipDetailsDto sessionInfo(String admin_id, String admin_password) {
		return dao.sessionInfo(admin_id, admin_password);
	}
}


