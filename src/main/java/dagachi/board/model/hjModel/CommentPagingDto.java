package dagachi.board.model.hjModel;

import java.util.ArrayList;
import java.util.List;

public class CommentPagingDto {
	
	private int count;
	private List<CommentDto> commentList;
	private int requestPage;
	private int totalPageCount;
	private int startRow;
	private PagingVO p;
	
	public CommentPagingDto() {
		this(0, new ArrayList<CommentDto>(),0,0,0, new PagingVO() );
	}

	public CommentPagingDto(int count, List<CommentDto> commentList, int requestPage, int totalPageCount, int startRow,
			PagingVO p) {
		super();
		this.count = count;
		this.commentList = commentList;
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

	public List<CommentDto> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentDto> commentList) {
		this.commentList = commentList;
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
