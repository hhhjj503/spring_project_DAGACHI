package dagachi.board.controller.hjController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import dagachi.board.model.hjModel.AdminMembershipDetailsDto;
import dagachi.board.model.hjModel.AdminPagingDto;
import dagachi.board.model.hjModel.AdminProfile;
import dagachi.board.service.hjService.AdminMembershipDetailsService;

@Controller
public class AdminMembershipDetailsController {
	
	@Autowired
	AdminMembershipDetailsService adser;
	
	@GetMapping("a_adminAccountList")
	public String list(@RequestParam(value = "p", defaultValue = "1") int pageNum,
			@RequestParam(value = "per", defaultValue = "10") int per, Model m, HttpSession session,HttpServletRequest request) {
		session = request.getSession();
		if(session == null) {
			return "redirect:a_adminLogin";
		}
		System.out.println(session);
		AdminPagingDto adminlist = adser.limitList(pageNum, per);
		m.addAttribute("adminlist", adminlist);
		int number = adminlist.getCount() - (pageNum - 1) * per;
		m.addAttribute("number", number);
		return "board/a_adminAccountList";
	}
	
	//�˻� ������ ����Ʈ �̵�
	@GetMapping("a_adminAccountListSearch")
	public String searchList(@RequestParam(value="admin_Num") String admin_Numstr,
			@RequestParam(value = "p", defaultValue = "1") int pageNum,
			@RequestParam(value = "per", defaultValue = "10") int per, Model m) {
		// System.out.println("per::"+per);  
		int admin_Num = Integer.parseInt(admin_Numstr);
		AdminPagingDto adminSearchList = adser.searchLimitList(admin_Num,pageNum, per);
		m.addAttribute("adminSearchList", adminSearchList);
		int number = adminSearchList.getCount() - (pageNum - 1) * per;
		m.addAttribute("number", number);
		return "board/a_adminAccountListSearch";
	}
	
	//������ �űԵ��â�̵�
	@RequestMapping("adminWriteForm")
	public String getadminInsert() {
		 return "board/a_adminInsert";
	}
	
	//������ �űԵ��
	@RequestMapping(value ="/adminWrite" ,method=RequestMethod.POST)
	public String adminInsert(AdminMembershipDetailsDto dto, Model m) {
		adser.adminAdd(dto);
		 return "redirect:a_adminLogin";
	}
	
	//������ ������ȸ
	@RequestMapping("adminInfo")
	public String adminUpdate(@RequestParam("admin_Num") int admin_Num, Model model) {
		AdminMembershipDetailsDto dto = new AdminMembershipDetailsDto();
		dto = adser.adminSel_1(admin_Num);
		model.addAttribute("adminUpdate", dto);
		return "board/a_adminUpdateDelete";
	}
	
	//������ ���� ����
	@RequestMapping("adminDel")
	public String adminDel(@RequestParam("admin_Num") int admin_Num
							, Model m) { 
		adser.adminDel(admin_Num);
		System.out.println("�����Ϸ�");
		return "redirect:a_adminAccountList";
	}
	//������ ���� ����
	@RequestMapping(value ="adminUpdate" ,method=RequestMethod.POST)
	public String adminUp(AdminMembershipDetailsDto dto, Model m) { 
		adser.adminUp(dto);
		return "redirect:a_adminAccountList";
	}
	@RequestMapping(value ="goList")
	public String goList() { 
		return "redirect:a_adminAccountList";
	}
	
	//������ �����ʻ��� ���ε�
	@RequestMapping(value="adminProfile", method=RequestMethod.POST)
	public String adminProfile(@RequestParam("admin_Id")String admin_Id, MultipartFile file,HttpServletRequest request, Model model ) {
		 int profileCount = adser.adminProfileUpCount(admin_Id);
		 if(profileCount > 0) 
		adser.delAdminProfileUp(admin_Id);
		
		String filePath = request.getSession().getServletContext().getRealPath("/resources/profileImages");
		AdminProfile profile = new AdminProfile();
		String formattedDate =
				filePath + File.separator + new SimpleDateFormat("yyyy"+ File.separator+"MM" + File.separator + "dd").format(new Date());
		System.out.println("���� ���ε� ��� : "+ formattedDate);
		File f = new File(formattedDate);
		
		if(!f.exists()) f.mkdirs();
		
		String orgFileName = file.getOriginalFilename(); //���������̸���������
	    String extention = "." + FilenameUtils.getExtension(file.getOriginalFilename()); //Ȯ��������
	    String uuid = UUID.randomUUID().toString(); //�������ڿ�������
	    System.out.println("���� �̸� : " + uuid);
	    String storedFileName = formattedDate + File.separator + uuid + extention;
	    String filePaths = formattedDate + File.separator + orgFileName;
	    System.out.println("����Ǵ� �����̸� : " + storedFileName);
	    String content_Type = file.getContentType();
	    long file_Size = file.getSize();
		  
	    profile.setAdmin_Id(admin_Id);
	    profile.setOrgFileName(orgFileName);
	    profile.setStoredFileName(storedFileName);
	    profile.setFile_Size(String.valueOf(file_Size));
	    profile.setContent_Type(content_Type);
	    
	    adser.adminProfileUp(profile);
	    
	   try {
		InputStream fis = file.getInputStream();
		FileOutputStream fos = new FileOutputStream(storedFileName);
		int readCount = 0;
		byte[] buffer = new byte[1024];
			while( (readCount = fis.read(buffer)) != -1 ) {
				fos.write(buffer,0,readCount);
			}
			
		fos.close();
		fis.close();
		
		int path1 = storedFileName.indexOf("profileImages");
		String path2 = storedFileName.substring(path1, storedFileName.length());
		model.addAttribute("profile","/resources/" + path2);
		
	   } catch (IOException e) {
		e.printStackTrace();
	}
	    
	   return "board/a_adminLoginUpdateDelete";
	}
	
	
	//���̵� �ߺ�Ȯ��
	@RequestMapping(value ="duplicateChk", method = RequestMethod.POST)
	@ResponseBody
	public int duplicateChk(String admin_Id) {
		int chk = adser.duplicateChk(admin_Id);
		return chk; 
	}
	
	//�̸��� �ߺ�Ȯ��
	@RequestMapping("emailDuplicateChk")
	@ResponseBody
	public int emailDuplicateChk(String admin_Email) {
		int emailChk = adser.emailDuplicateChk(admin_Email);
		return emailChk;
	}
	
	//�α��� �� ������ ������ �̵�
	@RequestMapping("adminLoginUpdateDelete")
	public String adminLoginUpdateDelete(@RequestParam("admin_Id")String admin_Id, Model model) {
		System.out.println("admin_Id : " + admin_Id);
		 int profileCount = adser.adminProfileUpCount(admin_Id);
		 System.out.println("profileCount : " + profileCount);
		 if(profileCount > 0) {
			 AdminProfile profile = adser.getAdminProfileUp(admin_Id);
			 String storedName = profile.getStoredFileName();
			 int path1 = storedName.indexOf("profileImages");
			 String path2 = storedName.substring(path1, storedName.length());
			 model.addAttribute("profile","/resources/" + path2);
			 System.out.println("���� �ڸ��� : " + path2);
			 System.out.println(storedName);
		 } else {
			 model.addAttribute("profile","/resources/images/logo/capture.PNG");
		 }
		 System.out.println(2);
		return "board/a_adminLoginUpdateDelete";
	}
	
	
}
