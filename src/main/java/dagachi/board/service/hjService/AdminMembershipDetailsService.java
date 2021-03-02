package dagachi.board.service.hjService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dagachi.board.model.hjModel.AdminMembershipDetailsDto;
import dagachi.board.model.hjModel.AdminPagingDto;
import dagachi.board.model.hjModel.AdminProfile;
import dagachi.board.model.hjModel.OwnerNoticeDto;
import dagachi.board.model.hjModel.OwnerPagingDto;
import dagachi.board.model.hjModel.PagingVO;

@Service
public class AdminMembershipDetailsService {
	
	@Autowired
	AdminMembershipDetailsDao dao;

	
	public void setDao(AdminMembershipDetailsDao dao) {
		this.dao = dao;
	}
	
	
	public List<AdminMembershipDetailsDto> adminSel() {
		List<AdminMembershipDetailsDto> list = new ArrayList<AdminMembershipDetailsDto>();
		list.addAll(dao.adminSel());
		return list;
	}

	public AdminMembershipDetailsDto adminSel_1(int admin_Num) {
		AdminMembershipDetailsDto dto = new AdminMembershipDetailsDto();
		dto = dao.adminSel_1(admin_Num);
		return dto;
	}
	
	public void adminAdd(AdminMembershipDetailsDto dto) {
		dao.adminInsert(dto);
	}
	
	public void adminUp(AdminMembershipDetailsDto dto) {
		dao.adminUp(dto);
	}
	
	public void adminDel(int admin_Num) {
		dao.adminDel(admin_Num);
	}

	public AdminPagingDto limitList(int pageNum, int per){
		int count = dao.count();
		if(count == 0) {
			return new AdminPagingDto();
		}
		
		int start = (pageNum - 1) * per;	
		List<AdminMembershipDetailsDto> dto = dao.getList(start, per);
		
		PagingVO p = new PagingVO().paging(pageNum, count, per);
		
		return new AdminPagingDto(count,dto,pageNum,p.getTotalPageCount(),start,p);
	}
	
	//관리자 프로필사진
	public void adminProfileUp(AdminProfile adminProfile) {
		dao.adminProfileUp(adminProfile);
	}
	
	public AdminPagingDto searchLimitList(int admin_Num, int pageNum, int per){
		int count = dao.searchCount(admin_Num);
		if(count == 0) {
			return new AdminPagingDto();
		}
		
		int start = (pageNum - 1) * per;	
		List<AdminMembershipDetailsDto> adminList = dao.getSearchList(admin_Num, start, per);
		
		PagingVO p = new PagingVO().paging(pageNum, count, per);
		
		return new AdminPagingDto(count,adminList,pageNum,p.getTotalPageCount(),start,p);
	}
	
	public int duplicateChk(String admin_Id) {
		return dao.duplicateChk(admin_Id);
	}
	
	public int emailDuplicateChk(String admin_Email) {
		return dao.emailDuplicateChk(admin_Email);
	}


	public int adminProfileUpCount(String admin_Id) {
		return dao.adminProfileUpCount(admin_Id);
	}


	public AdminProfile getAdminProfileUp(String admin_Id) {
		return dao.getAdminProfileUp(admin_Id);
	}
	
}
