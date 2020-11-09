package dagachi.board.service.hjService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import dagachi.board.model.hjModel.FileUploadDto;

public class FileUploadDao extends SqlSessionDaoSupport {

	public List<FileUploadDto> fileGet(int notice_Num) {
		return getSqlSession().selectList("file.fileGet",notice_Num);
	}
	
	public FileUploadDto downLoad(int notice_Num, int file_No) {
		Map<String, Integer> m = new HashMap<String, Integer>();
		m.put("notice_Num", notice_Num);
		m.put("file_No", file_No);
		return getSqlSession().selectOne("file.downLoad", m);
	}
	
	public int fileChk(int notice_Num) {
		return getSqlSession().selectOne("file.fileChk",notice_Num);
	}
	
	public void fileDel(int notice_Num) {
		getSqlSession().selectOne("file.fileDel",notice_Num);
	}
	
	public void fileUpload(FileUploadDto dto) {
		getSqlSession().selectOne("file.fileUpload",dto);
	}
	
	public void fileDelOne(int notice_Num, int file_No) {
		Map<String, Integer> m = new HashMap<String, Integer>();
		m.put("notice_Num", notice_Num);
		m.put("file_No", file_No);
		getSqlSession().selectOne("file.fileDelOne", m);
	}
}
