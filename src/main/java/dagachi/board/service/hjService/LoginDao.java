package dagachi.board.service.hjService;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import dagachi.board.model.hjModel.AdminMembershipDetailsDto;

public class LoginDao extends SqlSessionDaoSupport {
	
	//id_Chk에 랜덤값주기
	public void id_ChkUp(int id_Chk,String admin_email) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("id_Chk", id_Chk);
		m.put("admin_email", admin_email);
		getSqlSession().update("loginMapper.id_ChkUp", m);
	}
	
	//id_Chk 랜덤으로 준 값 가져오기
	public String getId_Chk(String id_Chk) {
		return getSqlSession().selectOne("loginMapper.getId_Chk", id_Chk);
	}
	
	//랜덤준값과 입력한값이 같은지 확인
	public int id_id_Chk(String id_Chk) {
		return getSqlSession().selectOne("loginMapper.id_id_Chk", id_Chk);
	}
	
	//일치하면 아이디 정보 보여주기
	public String getId(int id_Chk,String admin_email) {
		Map<String,Object> m = new HashMap<String, Object>();
		m.put("id_Chk", id_Chk);
		m.put("admin_email", admin_email);
		return getSqlSession().selectOne("loginMapper.getId", m);
	}
	
	//로그인시 아이디 존재여부 확인
	public int loginIdChk(String admin_id) {
		return getSqlSession().selectOne("loginMapper.loginIdChk",admin_id);
	}
	
	//아이디가 존재하면 해당 아이디로 비밀번호 가져오기
	public String loginPwdchk(String admin_id) {
		return getSqlSession().selectOne("loginMapper.loginPwdchk", admin_id);
	}
	
	//비밀번호 변경하기
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
