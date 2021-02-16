package dagachi.board.controller.hjController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.tiles.jsp.taglib.AddListAttributeTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dagachi.board.model.hjModel.AdminMembershipDetailsDto;
import dagachi.board.model.hjModel.FileUploadDto;
import dagachi.board.model.hjModel.OwnerNoticeDto;
import dagachi.board.model.hjModel.OwnerPagingDto;
import dagachi.board.service.hjService.FileUploadService;
import dagachi.board.service.hjService.LoginService;
import dagachi.board.service.hjService.OwnerNoticeService;

@Controller
public class NoticeController {
	
	@Autowired
	OwnerNoticeService ser;
	
	@Autowired
	FileUploadService fileser;
	
	public void setFileser(FileUploadService fileser) {
		this.fileser = fileser;
	}
	public void setSer(OwnerNoticeService ser) {
		this.ser = ser;
	}
	
	//�������� ���
	@RequestMapping("a_noticeList")
	public String list(@RequestParam(value = "p", defaultValue = "1") int pageNum,
			@RequestParam(value = "per", defaultValue = "10") int per, Model m) {
		OwnerPagingDto list = ser.limitList(pageNum, per);
		m.addAttribute("list", list);
		int number = list.getCount() - (pageNum - 1) * per;
		m.addAttribute("number", number);
		return "/board/a_noticeList";
	}
	
	//�˻��� search �� 0�� �ۼ��� 1�� ���� �˻��Դϴ�.
	@RequestMapping(value = "a_noticeListSearch")
	public String searchNotice(
		@RequestParam(value="searchstr") String searchstr,
		@RequestParam(value = "p", defaultValue = "1") int pageNum,
		@RequestParam(value = "per", defaultValue = "10") int per, 
		@RequestParam(value="search")int search , Model m) {
		
		if(search == 0) {
		int admin_Num = Integer.parseInt(searchstr.trim());
		OwnerPagingDto searchAdminList = ser.searchNotice_Admin_Num(admin_Num, pageNum, per);
		m.addAttribute("searchList", searchAdminList);
		int number = searchAdminList.getCount() - (pageNum - 1) * per;
		m.addAttribute("number", number);
		m.addAttribute("searchstr", searchstr);
		m.addAttribute("search", search);
		
		return "board/a_noticeListSearch";
	} else {
		 OwnerPagingDto searchTitleList = ser.searchNotice_title(searchstr,pageNum, per);
		 m.addAttribute("searchList", searchTitleList);
		 int number2 = searchTitleList.getCount() - (pageNum - 1) * per;
		 m.addAttribute("number",number2);
		 m.addAttribute("searchstr", searchstr);
		 m.addAttribute("search", search);
		
		 return "board/a_noticeListSearch";
		}	
	}
	//�������
	@RequestMapping("noticeBack")
	public String noticeBack() {
		return "redirect:a_noticeList";
	}
	//�˻��� ��ϰ���
	@RequestMapping("searchNoticeBack")
	public String searchNoticeBack(@RequestParam(value="searchstr") String searchstr,
			@RequestParam(value="search") String search,Model model) {
		model.addAttribute("searchstr", searchstr);
		model.addAttribute("search", search);
		return "redirect:a_noticeListSearch";
	}
	@ModelAttribute("list")
	 public void noticeSel(Model model) {
		 List<OwnerNoticeDto> list = new ArrayList<OwnerNoticeDto>();
		list = ser.noticeSel();
		model.addAttribute("list", list);
	}
	 //�������� �ۼ��� ��¥��
	 @ModelAttribute("today")  
	 public void today(Model model) {
		 Date today = new Date();
		 model.addAttribute("today",today);
	 }
	 //�������� �ۼ���
	 @RequestMapping("noticeWriteForm")
	 public String getadminWriteForm() {
			/*
			 * session = request.getSession(false); if(session == null) { return
			 * "redirect:a_adminLogin"; }
			 */
		 return "board/a_noticeWrite";
	 }
	 //�������� ���
	 @RequestMapping(value = "noticeWrite", method = RequestMethod.POST)
		public String getNoticeWrite(OwnerNoticeDto dto, Model m) {
		 Date today = new Date();
		 dto.setOwner_Notice_Created_Day(today);
		 ser.noticeAdd(dto);
		 return "redirect:a_noticeList";
	 } 
	 //�������� ����,����
	 @RequestMapping(value="noticeInfo", method = RequestMethod.GET)
	 public String noticeSel_1(@RequestParam(value="noticeNum") int noticeNum,
			 Model model) {
		 OwnerNoticeDto choose = new OwnerNoticeDto();
		 
			 int count = fileser.fileChk(noticeNum); if(count > 0) { List<FileUploadDto>
			 list = fileser.fileGet(noticeNum); model.addAttribute("count", count);
			 model.addAttribute("fileDtos", list); }
			 
		choose = ser.noticeSel_1(noticeNum);
		model.addAttribute("choose", choose);
		return "board/a_noticeUpdateDelete";
	}
	 
	 //�������� �б�
	 @RequestMapping("noticeRead")
	 public String noticeRead_1(@RequestParam(value="noticeNum") int noticeNum, Model model) {
		 OwnerNoticeDto choose = new OwnerNoticeDto();
		
		//�ش������ȣ�� ������ �ִٸ� ���ϰ�ü�� ������
		int count = fileser.fileChk(noticeNum);
		if(count > 0) {
			List<FileUploadDto> list = fileser.fileGet(noticeNum);
			model.addAttribute("count", count);
			model.addAttribute("fileDtos", list);
		}
		choose = ser.noticeSel_1(noticeNum);
		model.addAttribute("choose", choose);
		
		return "board/a_noticeRead";
	}
	 
	//�������� ���� + ��������
	 private String filePath = "C:\\Users\\Administrator\\Desktop\\file"; //���Ͼ��ε���
	  @RequestMapping(value = "noticeUpdate", method = RequestMethod.POST)
	  public String noticeUp(@RequestParam(value="noticeNum") int noticeNum,
			  				@RequestParam(value="admin_Num")int admin_Num,
			  			  MultipartFile[] files,  OwnerNoticeDto dto, FileUploadDto filedto, Model model) {
	  //��������
	  ser.noticeUp(dto);
	  System.out.println("��������");
	  //���Ͼ��ε�
	  if(files != null && files.length > 0) {
      // windows ����ڶ�� "c:\temp\�⵵\��\��" ������ ���ڿ��� ���Ѵ�.  
      String formattedDate = filePath + new SimpleDateFormat("yyyy" + File.separator + "MM" + File.separator + "dd").format(new Date());
      			File f = new File(formattedDate); //������ ��ġ�� �����ڿ� ���ڿ��� �Է� 
      			if(!f.exists()){ // ������ �������� �ʴ´ٸ�
	                f.mkdirs(); // �ش� ���丮�� �����. ������������ �Ѳ����� �����.
	            	}
	       for(MultipartFile file : files) {
	          Date date = new Date();
              String contentType = file.getContentType();
              String name = file.getName();
              String originalFilename = file.getOriginalFilename();
              long size = file.getSize();
              String extension = "." + FilenameUtils.getExtension(file.getOriginalFilename()); //���Ͽ��� Ȯ���ڸ� ���ϴ� �޼ҵ�
              String baseName = FilenameUtils.getBaseName(file.getOriginalFilename()); //���Ͽ��� �̸��� ���ϴ� �޼ҵ�
              String uuid = UUID.randomUUID().toString() + file.getName(); // �ߺ��� ���� ���� ����. 6dfba64a-d23e-491f-980d-44d0bc1bdb5f �̷������� ����
              String saveFileName = formattedDate + File.separator + uuid + extension; // ���� ����Ǵ� ������ ���� ���
              // �Ʒ����� ��µǴ� ����� ��� database�� ����Ǿ� �Ѵ�.
              // pk ���� �ڵ����� �����ǵ��� �Ѵ�.
              filedto.setNotice_Num(noticeNum);
              filedto.setOrg_File_Name(originalFilename);
              filedto.setStored_FileName(saveFileName);
              filedto.setContents_Type(contentType);
              filedto.setFile_Size(size);
              filedto.setRegDate(date);
              fileser.fileUpload(filedto);
          	try(InputStream in = file.getInputStream();	
          		//()���� ��ο��ٰ� ������ ��������� ����
                  FileOutputStream fos = new FileOutputStream(saveFileName) ) {
                  int readCount = 0;
                  byte[] buffer = new byte[512];
	                    while((readCount = in.read(buffer)) != -1){
	                        fos.write(buffer,0,readCount);
	                    }
              } catch(Exception ex) {
                  ex.printStackTrace();
              }
	       }
	  }
	  model.addAttribute("noticeUpdate", dto);
	  model.addAttribute("noticeNum", noticeNum);
	  model.addAttribute("admin_Num", admin_Num);
	  return "redirect:noticeRead";
	 }
	  
	  //���� �ٿ�ε�
	  @GetMapping(value="down")
	    public void downloadReservationUserCommentImage(
	            @RequestParam(value = "notice_Num") int notice_Num,
	            @RequestParam(value="file_No")int file_No,
	            HttpServletResponse response) {
	        //id�� �̿��Ͽ� ������ ������ �о�´�.
	        //�� �κ��� ���� db���� �о���� ���ε� db�κ��� �����ߴ�.
			FileUploadDto dto =  fileser.downLoad(notice_Num, file_No);
	        String originalFilename = dto.getOrg_File_Name();
	        String contentType = dto.getContents_Type();
	        long fileSize = dto.getFile_Size();
	        //�ش� ������ �̹� �����ؾ��Ѵ�.
	        String saveFileName = dto.getStored_FileName();
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFilename + "\";");
	        response.setHeader("Content-Transfer-Encoding", "binary");
	        response.setHeader("Content-Type", contentType);
	        response.setHeader("Content-Length", ""+ fileSize);
	        response.setHeader("Pragma", "no-cache;");
	        response.setHeader("Expires", "-1;");
	 
	        java.io.File readFile = new java.io.File(saveFileName);
	        if(!readFile.exists()){ // ������ �������� �ʴٸ�
	            throw new RuntimeException("file not found");
	        }
	        FileInputStream fis = null;
	        try{
	            fis = new FileInputStream(readFile);
	            FileCopyUtils.copy(fis, response.getOutputStream()); // ������ �����Ҷ��� ����� �� �ִ�.
	            response.getOutputStream().flush();
	        }catch(Exception ex){
	            throw new RuntimeException(ex);
	        }finally {
	            try {
	                fis.close();
	            }catch(Exception ex){
	                // �ƹ��͵� ���� ���� (�ʿ��� �α׸� ����� ������ �۾��� ��.)
	            }
	        }
	 }
	  
	 //�˻��� �������� Ŭ��
	 @RequestMapping("searchRead")
	 public String searchRead(@RequestParam(value="searchstr") String searchstr,
			  @RequestParam(value="noticeNum") int noticeNum,
			  @RequestParam(value="search")String search,
			  Model model) {
		 	OwnerNoticeDto choose = new OwnerNoticeDto();
			choose = ser.noticeSel_1(noticeNum);
			int count = fileser.fileChk(noticeNum);
			if(count > 0) {
				List<FileUploadDto> list = fileser.fileGet(noticeNum);
				model.addAttribute("count", count);
				model.addAttribute("fileDtos", list);
			}
			model.addAttribute("choose", choose);
			model.addAttribute("searchstr", searchstr);
			model.addAttribute("search", search);
			return "board/a_noticeSearchRead";
	 }
	  
	 //�˻��� �������� Ŭ����
	  @RequestMapping(value = "searchNoticeInfo", method = RequestMethod.GET)
	  public String searchNoticeSel_1(@RequestParam(value="searchstr") String searchstr,
			  @RequestParam(value="noticeNum") int noticeNum,
			  @RequestParam(value="adminpwd")String adminpwd,
			  @RequestParam(value="search")String search,MultipartFile[] files,
			  FileUploadDto filedto,
			  Model model) {
			OwnerNoticeDto choose = new OwnerNoticeDto();
			int count = fileser.fileChk(noticeNum);
			if(count > 0) {
				List<FileUploadDto> list = fileser.fileGet(noticeNum);
				model.addAttribute("count", count);
				model.addAttribute("fileDtos", list);
			}
			choose = ser.noticeSel_1(noticeNum);
			model.addAttribute("choose", choose);
			model.addAttribute("adminpwd", adminpwd);
			model.addAttribute("searchstr", searchstr);
			model.addAttribute("search", search);
			return "board/a_SearchNoticeUpdateDelete";
	  }
	//�˻��� �������� ����
	  @RequestMapping(value = "searchNoticeUpdate", method = RequestMethod.POST)
	  public String searchNoticeUp(@RequestParam(value="searchstr") String searchstr,
			  @RequestParam(value="noticeNum") int noticeNum,
			  @RequestParam(value="admin_Num")int admin_Num,
			  @RequestParam(value="search")String search,
			  OwnerNoticeDto dto,MultipartFile[] files, FileUploadDto filedto, Model model) {
	  //�˻��� �������� ����
	  ser.noticeUp(dto);
	  //�˻��� ���Ͼ��ε�
	  if(files != null && files.length > 0) {
      // windows ����ڶ�� "c:\temp\�⵵\��\��" ������ ���ڿ��� ���Ѵ�.  
      String formattedDate = filePath + new SimpleDateFormat("yyyy" + File.separator + "MM" + File.separator + "dd").format(new Date());
      			File f = new File(formattedDate);
      			if(!f.exists()){ // ������ �������� �ʴ´ٸ�
	                f.mkdirs(); // �ش� ���丮�� �����. ������������ �Ѳ����� �����.
	            	}
	       for(MultipartFile file : files ) {
	          Date date = new Date();
              String contentType = file.getContentType();
              String name = file.getName();
              String originalFilename = file.getOriginalFilename();
              long size = file.getSize();
              String extension = "." + FilenameUtils.getExtension(file.getOriginalFilename()); //���Ͽ��� Ȯ���ڸ� ���ϴ� �޼ҵ�
              String baseName = FilenameUtils.getBaseName(file.getOriginalFilename()); //���Ͽ��� �̸��� ���ϴ� �޼ҵ�
              String uuid = UUID.randomUUID().toString() + file.getName(); // �ߺ��� ���� ���� ����. 6dfba64a-d23e-491f-980d-44d0bc1bdb5f �̷������� ����
              String saveFileName = formattedDate + File.separator + uuid + extension; // ���� ����Ǵ� ������ ���� ���
              
              // �Ʒ����� ��µǴ� ����� ��� database�� ����Ǿ� �Ѵ�.
              // pk ���� �ڵ����� �����ǵ��� �Ѵ�.
              filedto.setNotice_Num(noticeNum);
              filedto.setOrg_File_Name(originalFilename);
              filedto.setStored_FileName(saveFileName);
              filedto.setContents_Type(contentType);
              filedto.setFile_Size(size);
              filedto.setRegDate(date);
              fileser.fileUpload(filedto);
           try(InputStream in = file.getInputStream();	
          		//()���� ��ο��ٰ� ������ ��������� ����
                  FileOutputStream fos = new FileOutputStream(saveFileName) ) {
                  int readCount = 0;
                  byte[] buffer = new byte[512];
	                    while((readCount = in.read(buffer)) != -1){
	                        fos.write(buffer,0,readCount);
	                    }
              } catch(Exception ex) {
              	System.out.println("����");
                ex.printStackTrace(); }
	       } System.out.println("�˻��� ���ϵ� ������");
	 }
	  model.addAttribute("admin_Num", admin_Num);
	  model.addAttribute("searchstr", searchstr);
	  model.addAttribute("noticeNum", noticeNum);
	  model.addAttribute("search", search);
	  
	 return "redirect:searchRead";
	 }
	  //�������� ����
	  @RequestMapping("noticeDel")
	  public String noticeDel( @RequestParam("owner_Notice_Num") int owner_Notice_Num,Model model) {
	  ser.noticeDel(owner_Notice_Num);
	  fileser.fileDel(owner_Notice_Num);
	  
	  return "redirect:a_noticeList";
	  }
	  
	  //�˻��� �������� ����
	  @RequestMapping("searchNoticeDel")
	  public String searchNoticeDel(@RequestParam("owner_Notice_Num") int owner_Notice_Num,
	  @RequestParam(value="searchstr") String searchstr,
	  @RequestParam(value="search") String search,
	  Model m) {
		  ser.noticeDel(owner_Notice_Num);
		  fileser.fileDel(owner_Notice_Num);
		  m.addAttribute("searchstr", searchstr);
		  m.addAttribute("search", search);
		  
		  return "redirect:a_noticeListSearch";
	  }
	  //���ϻ���
	  @RequestMapping("fileDelOne")
	  public String fileDelOne(
			  @RequestParam(value="notice_Num") int notice_Num,
			  @RequestParam(value="file_No") int file_No, Model m) {
		  m.addAttribute("noticeNum", notice_Num);
		  fileser.fileDelOne(notice_Num, file_No);
		  return "redirect:noticeInfo";
	  }
	  
	  //����,����ȭ�鿡�� ��Ҵ����� �����б�� �̵�
	  @RequestMapping("goNoticeRead")
	  public String goNoticeRead(@RequestParam(value="noticeNum") int noticeNum, Model m) {
		  m.addAttribute("noticeNum", noticeNum);
		  return "redirect:noticeRead";
	  }
	  
	  //�˻��� ����,����ȭ�鿡�� ��Ҵ����� �����б�� �̵�
	  @RequestMapping("goSearchNoticeRead")
	  public String goSearchNoticeRead(@RequestParam(value="searchstr") String searchstr,
			  @RequestParam(value="noticeNum") int noticeNum,
			  @RequestParam(value="search")String search, Model m) {
		  m.addAttribute("searchstr", searchstr);
		  m.addAttribute("noticeNum", noticeNum);
		  m.addAttribute("search", search);
		  return "redirect:searchRead";
	  }
	  
}
