package dagachi.board.controller.hjController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import dagachi.board.model.hjModel.FileUploadDto;
import dagachi.board.service.hjService.FileUploadService;


public class FileUploadController {
	
	@Autowired
	FileUploadService ser;
	
    private String filePath = "C:\\Users\\Administrator\\Desktop\\file"; // c:\temp 디렉토리를 미리 만들어둔다.
	
	public void setSer(FileUploadService ser) {
		this.ser = ser;
	}

	@RequestMapping(value = "fileUpload", method = RequestMethod.POST)
	  public String file(
			  @RequestParam(value="noticeNum") int notice_Num,
			  @RequestParam(value = "file") MultipartFile[] files,
			  @RequestParam(value="admin_Num")int admin_Num, Model model) {
		  if(files != null && files.length > 0) {
	          // windows 사용자라면 "c:\temp\년도\월\일" 형태의 문자열을 구한다.
			  //File.separator 가 사용하는 운영체제에 맞게 위치경로를 표시하는 문자를 가져옴 윈도우의 경우 w로 표시
	            String formattedDate = filePath + new SimpleDateFormat("yyyy" + File.separator + "MM" + File.separator + "dd").format(new Date());
	            File f = new File(formattedDate);
	            		if(!f.exists()){ // 파일이 존재하지 않는다면
	            			f.mkdirs(); // 해당 디렉토리를 만든다. 하위폴더까지 한꺼번에 만든다.
		            		}
	            for(MultipartFile file : files) {	
	            	Date date = new Date();
	                String contentType = file.getContentType();
	                String name = file.getName();
	                String originalFilename = file.getOriginalFilename();
	                long size = file.getSize();
	                String uuid = UUID.randomUUID().toString() + file.getName(); // 중복될 일이 거의 없다. 6dfba64a-d23e-491f-980d-44d0bc1bdb5f 이런식으로 생성
	                String saveFileName = formattedDate + File.separator + uuid ; // 실제 저장되는 파일의 절대 경로
	                // 아래에서 출력되는 결과는 모두 database에 저장되야 한다.
	                // pk 값은 자동으로 생성되도록 한다.
	                FileUploadDto dto = new FileUploadDto();
	                dto.setNotice_Num(notice_Num);
	                dto.setOrg_File_Name(originalFilename);
	                dto.setStored_FileName(saveFileName);
	                dto.setContents_Type(contentType);
	                dto.setFile_Size(size);
	                dto.setRegDate(date);
	                
					ser.fileUpload(dto);
					
	            	try(InputStream in = file.getInputStream();
	                    FileOutputStream fos = new FileOutputStream(saveFileName) ) {
	                    int readCount = 0;
	                    byte[] buffer = new byte[512];
		                    while((readCount = in.read(buffer)) != -1){
		                        fos.write(buffer,0,readCount);
		                    }
	                } catch(Exception ex) {
	                	System.out.println("오류");
	                    ex.printStackTrace();
	                }
	            }//for     
	          model.addAttribute("noticeNum", notice_Num);
	  		  model.addAttribute("admin_Num", admin_Num);
	  		  model.addAttribute("file", file(0, null, 0, null));
		  	} //if
		  	return "redirect:noticeRead";
		  }//method
		  
}

