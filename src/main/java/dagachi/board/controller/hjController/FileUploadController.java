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
	
    private String filePath = "C:\\Users\\Administrator\\Desktop\\file"; // c:\temp ���丮�� �̸� �����д�.
	
	public void setSer(FileUploadService ser) {
		this.ser = ser;
	}

	@RequestMapping(value = "fileUpload", method = RequestMethod.POST)
	  public String file(
			  @RequestParam(value="noticeNum") int notice_Num,
			  @RequestParam(value = "file") MultipartFile[] files,
			  @RequestParam(value="admin_Num")int admin_Num, Model model) {
		  if(files != null && files.length > 0) {
	          // windows ����ڶ�� "c:\temp\�⵵\��\��" ������ ���ڿ��� ���Ѵ�.
			  //File.separator �� ����ϴ� �ü���� �°� ��ġ��θ� ǥ���ϴ� ���ڸ� ������ �������� ��� w�� ǥ��
	            String formattedDate = filePath + new SimpleDateFormat("yyyy" + File.separator + "MM" + File.separator + "dd").format(new Date());
	            File f = new File(formattedDate);
	            		if(!f.exists()){ // ������ �������� �ʴ´ٸ�
	            			f.mkdirs(); // �ش� ���丮�� �����. ������������ �Ѳ����� �����.
		            		}
	            for(MultipartFile file : files) {	
	            	Date date = new Date();
	                String contentType = file.getContentType();
	                String name = file.getName();
	                String originalFilename = file.getOriginalFilename();
	                long size = file.getSize();
	                String uuid = UUID.randomUUID().toString() + file.getName(); // �ߺ��� ���� ���� ����. 6dfba64a-d23e-491f-980d-44d0bc1bdb5f �̷������� ����
	                String saveFileName = formattedDate + File.separator + uuid ; // ���� ����Ǵ� ������ ���� ���
	                // �Ʒ����� ��µǴ� ����� ��� database�� ����Ǿ� �Ѵ�.
	                // pk ���� �ڵ����� �����ǵ��� �Ѵ�.
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
	                	System.out.println("����");
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

