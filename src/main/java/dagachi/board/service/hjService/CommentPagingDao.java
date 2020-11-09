package dagachi.board.service.hjService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import dagachi.board.model.hjModel.CommentDto;

public class CommentPagingDao extends SqlSessionDaoSupport {
	
	public void insertcmt(CommentDto dto) {
		getSqlSession().selectOne("Comment.insertcmt", dto);
	}
	
	public List<CommentDto> selectcmt(int start, int per, int owner_Notice_Num) {
		Map<String, Integer> m = new HashedMap<String, Integer>();
		List<CommentDto> list = new ArrayList<CommentDto>();
		m.put("start", start);
		m.put("per", per);
		m.put("owner_Notice_Num", owner_Notice_Num);
		list = getSqlSession().selectList("Comment.selectcmt", m);
		return list;
	}
	
	public void upcmt(String cmtContents, int cmtNumber) {
		Map m = new HashedMap<String, Object>();
		Integer integer = new Integer(cmtNumber);
		m.put("cmtContents", cmtContents);
		m.put("cmtNumber", integer);
		getSqlSession().selectOne("Comment.upcmt", m);
	}
	
	public int cmtcnt(int owner_Notice_Num) {
		int count = getSqlSession().selectOne("Comment.cmtcnt", owner_Notice_Num);
		return count;
	}
	
	public void delcmt(int cmtNumber) {
		getSqlSession().selectOne("Comment.delcmt", cmtNumber);
	}
	
	public String getPwd(int cmtNumber) {
		String getPwd = getSqlSession().selectOne("Comment.getPwd", cmtNumber);
		return getPwd;
	}
	
	public String getContents(int cmtNumber) {
		String getContents = getSqlSession().selectOne("Comment.getContents", cmtNumber);
		return getContents;
	}
	public int getCnt(int owner_Notice_Num) {
		int getCnt = getSqlSession().selectOne("Comment.getCnt", owner_Notice_Num);
		return getCnt;
	}
}
