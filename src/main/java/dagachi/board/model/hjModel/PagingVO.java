package dagachi.board.model.hjModel;

public class PagingVO {

	//표시될 페이지 번호를 저장하는 페이지
	//기본 시작페이지, 기본 마지막페이지, 전체 총페이지
	int beginPageNumber;
	int endPageNumber;
	int totalPageCount;
	
	public PagingVO() {}
	
	public PagingVO(int beginPageNumber, int endPageNumber, int totalPageCount) {
		super();
		this.beginPageNumber = beginPageNumber;
		this.endPageNumber = endPageNumber;
		this.totalPageCount = totalPageCount;
	}

	//count : 게시글 수 , per : 페이지당 출력 게시글수
	public PagingVO paging(int requestPage, int count, int per) {
		if (count == 0) {
			return new PagingVO(0,0,0);
		}
		int pageCount = count / per;
		if (count % per > 0) {
			pageCount++;
		}
		int beginNumber = (requestPage - 1) / 10 * 10 + 1;
		int endNumber = beginNumber + 9;
		if(endNumber > pageCount) {
			endNumber = pageCount;
		}
		return new PagingVO(beginNumber, endNumber, pageCount);
		
	}

	public int getBeginPageNumber() {
		return beginPageNumber;
	}

	public int getEndPageNumber() {
		return endPageNumber;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}
		
}
