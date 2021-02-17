package dagachi.board.controller.hjController;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dagachi.board.model.hjModel.OwnerNoticeDto;
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
	
	//공지사항 작성폼
		 @RequestMapping("adminNoticeWriteForm")
		 public String getadminWriteForm(Model m) {
			 Date today = new Date();
			 m.addAttribute("today",today);
			 return "board/a_noticeWriteAdmin";
		 }
		 //공지사항 등록
		 @RequestMapping(value = "noticeWriteAdmin", method = RequestMethod.POST)
			public String getNoticeWrite(OwnerNoticeDto dto, Model m) {
			 Date today = new Date();
			 dto.setOwner_Notice_Created_Day(today);
			 ser.noticeAdd(dto);
			 return "redirect:a_noticeList_admin";
		 }
		 
	@PostMapping("a_noticeListUpDel_admin")
	public String noticeUpdel(String cmd,String[] openIds, String[] delIds) {
		switch(cmd) {
		case "일괄공개" :
			int[] oids = new int[openIds.length];
			for(int i = 0; i < openIds.length;i++)
				oids[i] = Integer.parseInt(openIds[i]);
			String opens = "";
			for(int i = 0; i < oids.length; i++) {
				opens += oids[i];
				if(i <= oids.length-2) 
				opens += ",";
			}
			System.out.println(opens);
			int openNum = ser.openIds(opens);
			System.out.printf("공개글 개수 : %d" ,openNum);
			break;
		case "일괄삭제" :
			int[] dids = new int[delIds.length];
			for(int i = 0; i < delIds.length;i++)
				dids[i] = Integer.parseInt(delIds[i]);
			String closes = "";
			for(int i = 0; i < delIds.length; i++) {
				closes += delIds[i];
				if(i <= delIds.length-2) 
				closes += ",";
			}
			System.out.println(closes);
			int closeNum = ser.closeIds(closes);
			System.out.printf("비공개(삭제)글 개수 : %d" ,closeNum);
			break;
		}
		
		return "redirect:a_noticeList_admin";
		
	}
}
