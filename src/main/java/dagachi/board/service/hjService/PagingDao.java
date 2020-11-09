package dagachi.board.service.hjService;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import dagachi.board.model.hjModel.PagingVO;


public class PagingDao extends SqlSessionDaoSupport {

	@Autowired
	PagingVO vo;
	
	public int adminTotalCount() {
		return getSqlSession().selectOne("PagingQuery.adminTotalCount");
	}
	
	public int noticeTotalCount() {
		return getSqlSession().selectOne("PagingQuery.noticeTotalCount");
		
	}
	
}
