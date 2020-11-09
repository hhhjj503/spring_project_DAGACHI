package dagachi.board.model.hjModel;

import java.util.ArrayList;
import java.util.List;

import dagachi.board.model.hjModel.PagingVO;

public class OwnerPagingDto {
	
	//표시될 Dto를 관리하는 페이지
	private int count;
	private List<OwnerNoticeDto> ownerList;//현재 페이지에서 보여질 글들
	private int requestPage;//요청페이지
	private int totalPageCount;//전체 페이지 수
	private int startRow;//시작글
	private PagingVO p;
	
	public OwnerPagingDto() {
		this(0, new ArrayList<OwnerNoticeDto>(), 0, 0, 0, new PagingVO() );
	}

	public OwnerPagingDto(int count, List<OwnerNoticeDto> ownerList, int requestPage, int totalPageCount, int startRow, PagingVO p) {
		super();
		this.count = count;
		this.ownerList = ownerList;
		this.requestPage = requestPage;
		this.totalPageCount = totalPageCount;
		this.startRow = startRow;
		this.p = p;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<OwnerNoticeDto> getOwnerList() {
		return ownerList;
	}

	public void setOwnerList(List<OwnerNoticeDto> ownerList) {
		this.ownerList = ownerList;
	}

	public int getRequestPage() {
		return requestPage;
	}

	public void setRequestPage(int requestPage) {
		this.requestPage = requestPage;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public PagingVO getP() {
		return p;
	}

	public void setP(PagingVO p) {
		this.p = p;
	}
	
}
