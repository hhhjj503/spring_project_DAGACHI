package dagachi.board.controller.hjController;

import java.util.ArrayList;   
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dagachi.board.model.hjModel.AdminMembershipDetailsDto;
import dagachi.board.model.hjModel.AdminPagingDto;
import dagachi.board.model.hjModel.OwnerPagingDto;
import dagachi.board.model.hjModel.PagingVO;
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
	public String adminLoginUpdateDelete(@RequestParam("admin_Id")String admin_Id) {
		return "board/a_adminLoginUpdateDelete";
	}
	
}
