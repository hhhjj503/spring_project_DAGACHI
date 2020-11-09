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
	
	//id_Chk �������� �� �� ��������
	public String getId_Chk(String id_Chk) {
		return dao.getId_Chk(id_Chk);
	}
	
	//�����ذ��� �Է��Ѱ��� ������ Ȯ��
	public int id_id_Chk(String id_Chk) {
		return dao.id_id_Chk(id_Chk);
	}
	
	//��ġ�ϸ� ���̵� ���� �����ֱ�
	public String getId(int id_Chk,String admin_email) {
		return dao.getId(id_Chk,admin_email);
	}
	
	//�α��ν� ���̵� ���翩�� Ȯ��
	public int loginIdChk(String admin_id) {
		return dao.loginIdChk(admin_id);
	}
	
	//���̵� �����ϸ� �ش� ���̵�� ��й�ȣ ��������
	public String loginPwdchk(String admin_id) {
		return dao.loginPwdchk(admin_id);
	}
	
	//��й�ȣ �����ϱ�
	public void changePwd(String admin_Password,String admin_id,String admin_email) {
		dao.changePwd(admin_Password, admin_id, admin_email);
	}
	
	//��й�ȣ ����2�ܰ�
	public int Chk_Id_Pwd(String admin_id,String admin_email) {
		return dao.Chk_Id_Pwd(admin_id, admin_email);
	}
	
	//��й�ȣ ��������
	public int changePwdcount(String admin_id,String admin_email,String pwdCode) {
		return dao.changePwdcount(admin_id, admin_email, pwdCode);
	}
	
	public AdminMembershipDetailsDto sessionInfo(String admin_id, String admin_password) {
		return dao.sessionInfo(admin_id, admin_password);
	}
}


