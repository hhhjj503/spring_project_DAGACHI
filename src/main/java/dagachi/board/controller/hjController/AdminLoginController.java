package dagachi.board.controller.hjController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dagachi.board.model.hjModel.OwnerPagingDto;
import dagachi.board.service.hjService.AdminLoginService;

@Controller
public class AdminLoginController {
	
	@Autowired
	AdminLoginService ser;

	public AdminLoginService setSer() {
		return ser;
	}
	
	@RequestMapping("a_noticeList_admin")
	public String adminList(@RequestParam(value = "p", defaultValue = "1") int pageNum,
		@RequestParam(value = "per", defaultValue = "10") int per, Model m) {
		OwnerPagingDto list = ser.limitList(pageNum, per);
		m.addAttribute("list", list);
		int number = list.getCount() - (pageNum - 1) * per;
		m.addAttribute("number", number);
		return "/board/a_noticeList_admin";
	}
	
	@RequestMapping(value = "a_noticeListSearch_admin")
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
		return "/board/a_noticeListSearch_admin";
	} else {
		 OwnerPagingDto searchTitleList = ser.searchNotice_title(searchstr,pageNum, per);
		 m.addAttribute("searchList", searchTitleList);
		 int number2 = searchTitleList.getCount() - (pageNum - 1) * per;
		 m.addAttribute("number",number2);
		 m.addAttribute("searchstr", searchstr);
		 m.addAttribute("search", search);
		 return "/board/a_noticeListSearch_admin";
		}	
	}
	//목록으로
	@RequestMapping("noticeBack_admin")
	public String noticeBack() {
		return "redirect:a_noticeList_admin";
	}
	//검색후 목록가기
	@RequestMapping("searchNoticeBack_admin")
	public String searchNoticeBack(@RequestParam(value="searchstr") String searchstr,
			@RequestParam(value="search") String search,Model model) {
		model.addAttribute("searchstr", searchstr);
		model.addAttribute("search", search);
		return "redirect:a_noticeListSearch_admin";
	}
	
	
}
