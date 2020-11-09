package dagachi.board.service.hjService;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import dagachi.board.model.hjModel.AdminMembershipDetailsDto;

public class LoginDao extends SqlSessionDaoSupport {
	
	//id_Chk�� �������ֱ�
	public void id_ChkUp(int id_Chk,String admin_email) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("id_Chk", id_Chk);
		m.put("admin_email", admin_email);
		getSqlSession().update("loginMapper.id_ChkUp", m);
	}
	
	//id_Chk �������� �� �� ��������
	public String getId_Chk(String id_Chk) {
		return getSqlSession().selectOne("loginMapper.getId_Chk", id_Chk);
	}
	
	//�����ذ��� �Է��Ѱ��� ������ Ȯ��
	public int id_id_Chk(String id_Chk) {
		return getSqlSession().selectOne("loginMapper.id_id_Chk", id_Chk);
	}
	
	//��ġ�ϸ� ���̵� ���� �����ֱ�
	public String getId(int id_Chk,String admin_email) {
		Map<String,Object> m = new HashMap<String, Object>();
		m.put("id_Chk", id_Chk);
		m.put("admin_email", admin_email);
		return getSqlSession().selectOne("loginMapper.getId", m);
	}
	
	//�α��ν� ���̵� ���翩�� Ȯ��
	public int loginIdChk(String admin_id) {
		return getSqlSession().selectOne("loginMapper.loginIdChk",admin_id);
	}
	
	//���̵� �����ϸ� �ش� ���̵�� ��й�ȣ ��������
	public String loginPwdchk(String admin_id) {
		return getSqlSession().selectOne("loginMapper.loginPwdchk", admin_id);
	}
	
	//��й�ȣ �����ϱ�
	public void changePwd(String admin_Password,String admin_id,String admin_email) {
		Map<String, String> m = new HashedMap<String,String>();
		m.put("admin_password", admin_Password);
		m.put("admin_id", admin_id);
		m.put("admin_email", admin_email);
		getSqlSession().update("loginMapper.changePwd", m);
	}
	
	public int Chk_Id_Pwd(String admin_id,String admin_email) {
		Map<String, String> m = new HashMap<String, String>();
		m.put("admin_id", admin_id);
		m.put("admin_email", admin_email);
		return getSqlSession().selectOne("loginMapper.chk_Id_Pwd", m);
	}
	
	public int changePwdcount(String admin_id,String admin_email,String pwdCode) {
		Map<String, String> m = new HashMap<String, String>();
		m.put("admin_id", admin_id);
		m.put("admin_email", admin_email);
		m.put("id_Chk", pwdCode);
		return getSqlSession().selectOne("loginMapper.changePwdcount", m);
	}
	
	public AdminMembershipDetailsDto sessionInfo(String admin_id, String admin_password) {
		AdminMembershipDetailsDto login = new AdminMembershipDetailsDto();
		Map<String, String> m =new HashMap<String, String>();
		m.put("admin_id", admin_id);
		m.put("admin_password", admin_password);
		login =  getSqlSession().selectOne("loginMapper.sessionInfo", m);
		return login;
	}
}
