package dagachi.board.service.hjService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import dagachi.board.model.hjModel.AdminMembershipDetailsDto;
import dagachi.board.model.hjModel.OwnerNoticeDto;

public class AdminLoginDao extends SqlSessionDaoSupport {
	
	public List<OwnerNoticeDto> noticeSel() {
		List<OwnerNoticeDto> list = new ArrayList<OwnerNoticeDto>();
		list = getSqlSession().selectList("OwnerNotice.noticeSel");
		return list;
	}
	
	public void noticeAdd(OwnerNoticeDto dto) {
		getSqlSession().insert("OwnerNotice.noticeAdd", dto);
	}
	
	public void noticeUp(OwnerNoticeDto dto) {
		getSqlSession().update("OwnerNotice.noticeUp", dto);
	}
	
	public void noticeDel(int owner_Notice_Num) {
		getSqlSession().delete("OwnerNotice.noticeDel", owner_Notice_Num);
	}
	
	public OwnerNoticeDto noticeSel_1(int owner_Notice_Num) {
		return getSqlSession().selectOne("OwnerNotice.noticeSel_1", owner_Notice_Num);
	}
	
	public int count() {
		return getSqlSession().selectOne("AdminOwnerNotice.count");
	}
	
	public int searchAdminCount(int admin_Num) {
		return getSqlSession().selectOne("OwnerNotice.searchAdminCount", admin_Num);
	}
	
	public int searchTitleCount(String owner_Notice_Title) {
		return getSqlSession().selectOne("OwnerNotice.searchTitleCount", owner_Notice_Title);
	}
	
	
	public List<OwnerNoticeDto> getList(int start,int per){
		Map<String, Integer> m = new HashMap<String, Integer>();
		List<OwnerNoticeDto> list = new ArrayList<OwnerNoticeDto>();
		m.put("start",start);
		m.put("per",per);
		list = getSqlSession().selectList("AdminOwnerNotice.limitList",m);
		return list;
	}
	
	public List<OwnerNoticeDto> searchNotice_Admin_Num(int admin_Num,int start,int per){
		Map<String, Integer> m = new HashMap<String, Integer>();
		List<OwnerNoticeDto> list = new ArrayList<OwnerNoticeDto>();
		m.put("start",start);
		m.put("per",per);
		m.put("admin_Num", admin_Num);
		list = getSqlSession().selectList("OwnerNotice.searchNotice_AdminNum",m);
		return list;
	}
	
	public List<OwnerNoticeDto> searchNotice_title(String owner_Notice_Title, int start, int per) {
		Map<String, Object> m = new HashMap<String, Object>();
		List<OwnerNoticeDto> list = new ArrayList<OwnerNoticeDto>();
		m.put("owner_Notice_Title", owner_Notice_Title);
		m.put("start", start);
		m.put("per",per);
		list = getSqlSession().selectList("OwnerNotice.searchNotice_Title", m);
		return list;
	}
	
	public String adminpwd(int admin_Num) {
		Map<String, Integer> m = new HashMap<String, Integer>();
		m.put("admin_Num", admin_Num);
		String adminpwd;
		adminpwd =getSqlSession().selectOne("OwnerNotice.adminpwd", admin_Num);
		return adminpwd;
	}

	public int openIds(String openIds) {
		return getSqlSession().update("AdminOwnerNotice.openIds", openIds);
	}

	public int closeIds(String delIds) {
		return getSqlSession().update("AdminOwnerNotice.closeIds", delIds);
	
	}

}
