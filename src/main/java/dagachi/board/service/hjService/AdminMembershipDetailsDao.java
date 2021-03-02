package dagachi.board.service.hjService;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import dagachi.board.model.hjModel.AdminMembershipDetailsDto;
import dagachi.board.model.hjModel.AdminProfile;
import dagachi.board.model.hjModel.OwnerNoticeDto;

public class AdminMembershipDetailsDao extends SqlSessionDaoSupport{
	
	public List<AdminMembershipDetailsDto> adminSel() {
		List<AdminMembershipDetailsDto> list = new ArrayList<AdminMembershipDetailsDto>();
		list = getSqlSession().selectList("AdminQuery.adminSel");
		return list;
	}
	
	public AdminMembershipDetailsDto adminSel_1(int admin_Num) {
		AdminMembershipDetailsDto dto = new AdminMembershipDetailsDto();
		dto = getSqlSession().selectOne("AdminQuery.adminSel_1", admin_Num);
		return dto;
	}
	
	public void adminInsert(AdminMembershipDetailsDto dto) {
		getSqlSession().insert("AdminQuery.adminAdd", dto);
	}
	
	public void adminUp(AdminMembershipDetailsDto dto) {
		getSqlSession().update("AdminQuery.adminUp", dto);
	}
	
	public void adminDel(int admin_Num) {
		getSqlSession().delete("AdminQuery.adminDel", admin_Num);
	}
	
	public int count() {
		return getSqlSession().selectOne("AdminQuery.count");
		
	}
	
	public int searchCount(int admin_Num) {
		return getSqlSession().selectOne("AdminQuery.searchAdmin",admin_Num);
	}
	
	public List<AdminMembershipDetailsDto> getList(int start,int per){
		Map<String, Integer> m = new HashMap<String, Integer>();
		List<AdminMembershipDetailsDto> list = new ArrayList<AdminMembershipDetailsDto>();
		m.put("start",start);
		m.put("per",per);
		list = getSqlSession().selectList("AdminQuery.limitList",m);
		return list;
	}
	
	public List<AdminMembershipDetailsDto> getSearchList(int admin_Num,int start,int per){
		Map<String, Integer> m = new HashMap<String, Integer>();
		List<AdminMembershipDetailsDto> list = new ArrayList<AdminMembershipDetailsDto>();
		m.put("admin_Num", admin_Num);
		m.put("start",start);
		m.put("per",per);
		list = getSqlSession().selectList("AdminQuery.searchLimitList",m);
		return list;
	}
	
	public int duplicateChk(String admin_Id) {
		return getSqlSession().selectOne("AdminQuery.duplicateChk", admin_Id);
	}
	
	public int emailDuplicateChk(String admin_Email) {
		return getSqlSession().selectOne("AdminQuery.emailDuplicateChk", admin_Email);
	}

	public void adminProfileUp(AdminProfile adminProfile) {
		getSqlSession().insert("AdminProfile.adminProfileUp", adminProfile);
	}

	public int adminProfileUpCount(String admin_Id) {
		return getSqlSession().selectOne("AdminProfile.adminProfileUpCount",admin_Id);
	}

	public AdminProfile getAdminProfileUp(String admin_Id) {
		return getSqlSession().selectOne("AdminProfile.getAdminProfileUp",admin_Id);
	}
	
	public void delAdminProfileUp(String admin_Id) {
		getSqlSession().delete("AdminProfile.delAdminProfileUp",admin_Id);
	}
	
	
	
}
