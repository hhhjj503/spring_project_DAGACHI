package dagachi.board.service.hjService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import dagachi.board.model.hjModel.FileUploadDto;

@Service
public class FileUploadService {

	@Autowired
	FileUploadDao dao;
	
	public void setDao(FileUploadDao dao) {
		this.dao = dao;
	}

	public List<FileUploadDto> fileGet(int notice_Num) {
		return dao.fileGet(notice_Num);
	}
	
	public FileUploadDto downLoad(int notice_Num, int file_No) {
		return dao.downLoad(notice_Num, file_No);
	}
	
	public int fileChk(int notice_Num) {
		return dao.fileChk(notice_Num);
	}
	
	public void fileDel(int notice_Num) {
		dao.fileDel(notice_Num);
	}
	
	public void fileDelOne(int notice_Num, int file_No) {
		dao.fileDelOne(notice_Num, file_No);
	}
	
	public void fileUpload(FileUploadDto dto) {
		dao.fileUpload(dto);
	}
	
}
