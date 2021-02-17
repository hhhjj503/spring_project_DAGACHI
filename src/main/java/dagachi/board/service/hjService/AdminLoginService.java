package dagachi.board.service.hjService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dagachi.board.model.hjModel.AdminMembershipDetailsDto;
import dagachi.board.model.hjModel.OwnerNoticeDto;
import dagachi.board.model.hjModel.OwnerPagingDto;
import dagachi.board.model.hjModel.PagingVO;

@Service
public class AdminLoginService {

	
	@Autowired
	AdminLoginDao dao;
	
	public void setDao(AdminLoginDao dao) {
		this.dao = dao;
	}

	public void noticeAdd(OwnerNoticeDto dto) {
		dao.noticeAdd(dto);
	}
	public void noticeUp(OwnerNoticeDto dto) {
		dao.noticeUp(dto);
	}
	public void noticeDel(int owner_Notice_Num) {
		dao.noticeDel(owner_Notice_Num);
	}

	public OwnerNoticeDto noticeSel_1(int owner_Notice_Num) {
		OwnerNoticeDto dto =  dao.noticeSel_1(owner_Notice_Num);
		return dto;
	}
	
	public OwnerPagingDto limitList(int pageNum, int per){
		int count = dao.count();
		if(count == 0) {
			return new OwnerPagingDto();
		}
		int start = (pageNum - 1) * per;	
		List<OwnerNoticeDto> dto = dao.getList(start, per);
		
		PagingVO p = new PagingVO().paging(pageNum, count, per);
		
		return new OwnerPagingDto(count,dto,pageNum,p.getTotalPageCount(),start,p);
	}
	
	public OwnerPagingDto searchNotice_Admin_Num(int admin_Num, int pageNum, int per){
		int count = dao.searchAdminCount(admin_Num);
		if(count == 0) {
			return new OwnerPagingDto();
		}
		int start = (pageNum - 1) * per;	
		List<OwnerNoticeDto> dto = dao.searchNotice_Admin_Num(admin_Num, start, per);
		
		PagingVO p = new PagingVO().paging(pageNum, count, per);
		
		return new OwnerPagingDto(count,dto,pageNum,p.getTotalPageCount(),start,p);
	}

	public OwnerPagingDto searchNotice_title(String owner_Notice_Title, int pageNum, int per) {
		int count = dao.searchTitleCount(owner_Notice_Title);
		if(count == 0) {
			return new OwnerPagingDto();
		}
		int start = (pageNum - 1) * per;
		List<OwnerNoticeDto> dto = dao.searchNotice_title(owner_Notice_Title, start, per);
		PagingVO p = new PagingVO().paging(pageNum, count, per);
		return new OwnerPagingDto(count,dto,pageNum,p.getTotalPageCount(),start,p);
	}
	
	public String adminpwd(int admin_Num) {
		return dao.adminpwd(admin_Num);
	}
	
	public int openIds(String openIds) {
		return dao.openIds(openIds);
	}
	
	public int closeIds(String dels) {
		return dao.closeIds(dels);
	}
}


