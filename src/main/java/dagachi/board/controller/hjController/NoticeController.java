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
	
	//공지사항 목록
	@RequestMapping("a_noticeList")
	public String list(@RequestParam(value = "p", defaultValue = "1") int pageNum,
			@RequestParam(value = "per", defaultValue = "10") int per, Model m) {
		OwnerPagingDto list = ser.limitList(pageNum, per);
		m.addAttribute("list", list);
		int number = list.getCount() - (pageNum - 1) * per;
		m.addAttribute("number", number);
		return "/board/a_noticeList";
	}
	
	//검색값 search 는 0은 작성자 1은 제목 검색입니다.
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
	//목록으로
	@RequestMapping("noticeBack")
	public String noticeBack() {
		return "redirect:a_noticeList";
	}
	//검색후 목록가기
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
	 //공지사항 작성시 날짜값
	 @ModelAttribute("today")
	 public void today(Model model) {
		 Date today = new Date();
		 model.addAttribute("today",today);
	 }
	 //공지사항 작성폼
	 @RequestMapping("noticeWriteForm")
	 public String getadminWriteForm() {
			/*
			 * session = request.getSession(false); if(session == null) { return
			 * "redirect:a_adminLogin"; }
			 */
		 return "board/a_noticeWrite";
	 }
	 //공지사항 등록
	 @RequestMapping(value = "noticeWrite", method = RequestMethod.POST)
		public String getNoticeWrite(OwnerNoticeDto dto, Model m) {
		 Date today = new Date();
		 dto.setOwner_Notice_Created_Day(today);
		 ser.noticeAdd(dto);
		 return "redirect:a_noticeList";
	 } 
	 //공지사항 수정,삭제
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
	 
	 //공지사항 읽기
	 @RequestMapping("noticeRead")
	 public String noticeRead_1(@RequestParam(value="noticeNum") int noticeNum, Model model) {
		 OwnerNoticeDto choose = new OwnerNoticeDto();
		
		//해당공지번호로 파일이 있다면 파일객체를 전송함
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
	 
	//공지사항 수정 + 파일전송
	 private String filePath = "C:\\Users\\Administrator\\Desktop\\file"; //파일업로드경로
	  @RequestMapping(value = "noticeUpdate", method = RequestMethod.POST)
	  public String noticeUp(@RequestParam(value="noticeNum") int noticeNum,
			  				@RequestParam(value="admin_Num")int admin_Num,
			  			  MultipartFile[] files,  OwnerNoticeDto dto, FileUploadDto filedto, Model model) {
	  //공지수정
	  ser.noticeUp(dto);
	  System.out.println("수정종료");
	  //파일업로드
	  if(files != null && files.length > 0) {
      // windows 사용자라면 "c:\temp\년도\월\일" 형태의 문자열을 구한다.  
      String formattedDate = filePath + new SimpleDateFormat("yyyy" + File.separator + "MM" + File.separator + "dd").format(new Date());
      			File f = new File(formattedDate); //파일의 위치를 생성자에 문자열로 입력 
      			if(!f.exists()){ // 파일이 존재하지 않는다면
	                f.mkdirs(); // 해당 디렉토리를 만든다. 하위폴더까지 한꺼번에 만든다.
	            	}
	       for(MultipartFile file : files) {
	          Date date = new Date();
              String contentType = file.getContentType();
              String name = file.getName();
              String originalFilename = file.getOriginalFilename();
              long size = file.getSize();
              String extension = "." + FilenameUtils.getExtension(file.getOriginalFilename()); //파일에서 확장자만 구하는 메소드
              String baseName = FilenameUtils.getBaseName(file.getOriginalFilename()); //파일에서 이름만 구하는 메소드
              String uuid = UUID.randomUUID().toString() + file.getName(); // 중복될 일이 거의 없다. 6dfba64a-d23e-491f-980d-44d0bc1bdb5f 이런식으로 생성
              String saveFileName = formattedDate + File.separator + uuid + extension; // 실제 저장되는 파일의 절대 경로
              // 아래에서 출력되는 결과는 모두 database에 저장되야 한다.
              // pk 값은 자동으로 생성되도록 한다.
              filedto.setNotice_Num(noticeNum);
              filedto.setOrg_File_Name(originalFilename);
              filedto.setStored_FileName(saveFileName);
              filedto.setContents_Type(contentType);
              filedto.setFile_Size(size);
              filedto.setRegDate(date);
              fileser.fileUpload(filedto);
          	try(InputStream in = file.getInputStream();	
          		//()안의 경로에다가 파일을 내보내기로 설정
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
	  
	  //파일 다운로드
	  @GetMapping(value="down")
	    public void downloadReservationUserCommentImage(
	            @RequestParam(value = "notice_Num") int notice_Num,
	            @RequestParam(value="file_No")int file_No,
	            HttpServletResponse response) {
	        //id를 이용하여 파일의 정보를 읽어온다.
	        //이 부분은 원래 db에서 읽어오는 것인데 db부분은 생략했다.
			FileUploadDto dto =  fileser.downLoad(notice_Num, file_No);
	        String originalFilename = dto.getOrg_File_Name();
	        String contentType = dto.getContents_Type();
	        long fileSize = dto.getFile_Size();
	        //해당 파일이 이미 존재해야한다.
	        String saveFileName = dto.getStored_FileName();
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFilename + "\";");
	        response.setHeader("Content-Transfer-Encoding", "binary");
	        response.setHeader("Content-Type", contentType);
	        response.setHeader("Content-Length", ""+ fileSize);
	        response.setHeader("Pragma", "no-cache;");
	        response.setHeader("Expires", "-1;");
	 
	        java.io.File readFile = new java.io.File(saveFileName);
	        if(!readFile.exists()){ // 파일이 존재하지 않다면
	            throw new RuntimeException("file not found");
	        }
	        FileInputStream fis = null;
	        try{
	            fis = new FileInputStream(readFile);
	            FileCopyUtils.copy(fis, response.getOutputStream()); // 파일을 저장할때도 사용할 수 있다.
	            response.getOutputStream().flush();
	        }catch(Exception ex){
	            throw new RuntimeException(ex);
	        }finally {
	            try {
	                fis.close();
	            }catch(Exception ex){
	                // 아무것도 하지 않음 (필요한 로그를 남기는 정도의 작업만 함.)
	            }
	        }
	 }
	  
	 //검색후 공지사항 클릭
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
	  
	 //검색후 공지사항 클릭시
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
	//검색후 공지사항 수정
	  @RequestMapping(value = "searchNoticeUpdate", method = RequestMethod.POST)
	  public String searchNoticeUp(@RequestParam(value="searchstr") String searchstr,
			  @RequestParam(value="noticeNum") int noticeNum,
			  @RequestParam(value="admin_Num")int admin_Num,
			  @RequestParam(value="search")String search,
			  OwnerNoticeDto dto,MultipartFile[] files, FileUploadDto filedto, Model model) {
	  //검색후 공지사항 수정
	  ser.noticeUp(dto);
	  //검색후 파일업로드
	  if(files != null && files.length > 0) {
      // windows 사용자라면 "c:\temp\년도\월\일" 형태의 문자열을 구한다.  
      String formattedDate = filePath + new SimpleDateFormat("yyyy" + File.separator + "MM" + File.separator + "dd").format(new Date());
      			File f = new File(formattedDate);
      			if(!f.exists()){ // 파일이 존재하지 않는다면
	                f.mkdirs(); // 해당 디렉토리를 만든다. 하위폴더까지 한꺼번에 만든다.
	            	}
	       for(MultipartFile file : files ) {
	          Date date = new Date();
              String contentType = file.getContentType();
              String name = file.getName();
              String originalFilename = file.getOriginalFilename();
              long size = file.getSize();
              String extension = "." + FilenameUtils.getExtension(file.getOriginalFilename()); //파일에서 확장자만 구하는 메소드
              String baseName = FilenameUtils.getBaseName(file.getOriginalFilename()); //파일에서 이름만 구하는 메소드
              String uuid = UUID.randomUUID().toString() + file.getName(); // 중복될 일이 거의 없다. 6dfba64a-d23e-491f-980d-44d0bc1bdb5f 이런식으로 생성
              String saveFileName = formattedDate + File.separator + uuid + extension; // 실제 저장되는 파일의 절대 경로
              
              // 아래에서 출력되는 결과는 모두 database에 저장되야 한다.
              // pk 값은 자동으로 생성되도록 한다.
              filedto.setNotice_Num(noticeNum);
              filedto.setOrg_File_Name(originalFilename);
              filedto.setStored_FileName(saveFileName);
              filedto.setContents_Type(contentType);
              filedto.setFile_Size(size);
              filedto.setRegDate(date);
              fileser.fileUpload(filedto);
           try(InputStream in = file.getInputStream();	
          		//()안의 경로에다가 파일을 내보내기로 설정
                  FileOutputStream fos = new FileOutputStream(saveFileName) ) {
                  int readCount = 0;
                  byte[] buffer = new byte[512];
	                    while((readCount = in.read(buffer)) != -1){
	                        fos.write(buffer,0,readCount);
	                    }
              } catch(Exception ex) {
              	System.out.println("오류");
                ex.printStackTrace(); }
	       } System.out.println("검색후 파일도 전송함");
	 }
	  model.addAttribute("admin_Num", admin_Num);
	  model.addAttribute("searchstr", searchstr);
	  model.addAttribute("noticeNum", noticeNum);
	  model.addAttribute("search", search);
	  
	 return "redirect:searchRead";
	 }
	  //공지사항 삭제
	  @RequestMapping("noticeDel")
	  public String noticeDel( @RequestParam("owner_Notice_Num") int owner_Notice_Num,Model model) {
	  ser.noticeDel(owner_Notice_Num);
	  fileser.fileDel(owner_Notice_Num);
	  
	  return "redirect:a_noticeList";
	  }
	  
	  //검색후 공지사항 삭제
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
	  //파일삭제
	  @RequestMapping("fileDelOne")
	  public String fileDelOne(
			  @RequestParam(value="notice_Num") int notice_Num,
			  @RequestParam(value="file_No") int file_No, Model m) {
		  m.addAttribute("noticeNum", notice_Num);
		  fileser.fileDelOne(notice_Num, file_No);
		  return "redirect:noticeInfo";
	  }
	  
	  //수정,삭제화면에서 취소누르면 공지읽기로 이동
	  @RequestMapping("goNoticeRead")
	  public String goNoticeRead(@RequestParam(value="noticeNum") int noticeNum, Model m) {
		  m.addAttribute("noticeNum", noticeNum);
		  return "redirect:noticeRead";
	  }
	  
	  //검색후 수정,삭제화면에서 취소누르면 공지읽기로 이동
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
