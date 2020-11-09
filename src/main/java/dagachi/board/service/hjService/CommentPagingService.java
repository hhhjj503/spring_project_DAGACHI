package dagachi.board.service.hjService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;

import dagachi.board.model.hjModel.CommentDto;
import dagachi.board.model.hjModel.CommentPagingDto;
import dagachi.board.model.hjModel.PagingVO;

public class CommentPagingService {

	@Autowired
	CommentPagingDao dao;

	public void setDao(CommentPagingDao dao) {
		this.dao = dao;
	}
	

	
	public void insertcmt(CommentDto dto) {
		dao.insertcmt(dto);
	}
	
	public CommentPagingDto selectcmt(int pageNum, int per, int owner_Notice_Num) {
		int count = dao.cmtcnt(owner_Notice_Num);
		if(count == 0) {
			return new CommentPagingDto();
			}
		
		int start = (pageNum - 1) * per;
		List<CommentDto> list = new ArrayList<CommentDto>();
		list = dao.selectcmt(start, per,owner_Notice_Num);
		
		PagingVO p = new PagingVO().paging(pageNum, count, per);
		
		return new CommentPagingDto(count,list,pageNum,p.getTotalPageCount(),start,p);
	
	}
	
	public void upcmt(String cmtContents, int cmtNumber) {
		dao.upcmt(cmtContents,cmtNumber);
	}
	
	public int cmtcnt(int owner_Notice_Num) {
		int count = dao.cmtcnt(owner_Notice_Num);
		return count;
	}
	
	public void delcmt(int cmtNumber) {
		dao.delcmt(cmtNumber);	}
	
	public String getPwd(int cmtNumber) {
		String getPwd = dao.getPwd(cmtNumber);
		return getPwd;
	}
	
	public String getContents(int cmtNumber) {
		String getContents = dao.getContents(cmtNumber);
		return getContents;
	}
	
	public int getCnt(int owner_Notice_Num) {
		int getCnt = dao.getCnt(owner_Notice_Num);
		return getCnt;
	}
}

