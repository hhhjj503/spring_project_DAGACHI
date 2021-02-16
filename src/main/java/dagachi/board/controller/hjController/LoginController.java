package dagachi.board.controller.hjController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dagachi.board.model.hjModel.AdminMembershipDetailsDto;
import dagachi.board.service.hjService.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	LoginService ser;

	@Inject
	JavaMailSender mailSender;
	
	public LoginService setSer() {
		return ser;
	}
	
	int code;
	String userEmail;
	
	//로그인 페이지
	@RequestMapping("/a_adminLogin") public String login(HttpSession session) {
		if(session == null) {
		System.out.println("세션없음"); }
		return "board/a_adminLogin";
	}
	 
	//로그인
	@RequestMapping("login")
	@ResponseBody
	public String login(@RequestParam(value="admin_id")String admin_id,
						@RequestParam(value="admin_password")String admin_password) {
		int login_Id = ser.loginIdChk(admin_id);
		if(login_Id == 1) {
			String password = ser.loginPwdchk(admin_id);
			if(password.equals(admin_password)) { 
				return "success";
			} else {
				return "pwd_false";
			}
		} else {
			return "id_false";
		}
	}
	
	//아이디 , 비밀번호 모두 일치시 기본게시판으로 이동
	@PostMapping("loginChk")
	public String loginChk(@RequestParam(value="admin_id")String admin_id,
			@RequestParam(value="admin_password")String admin_password, HttpServletRequest request, RedirectAttributes redirect,HttpSession session) {
		AdminMembershipDetailsDto login = ser.sessionInfo(admin_id, admin_password);
		boolean supervisor = login.getAdmin_Supervisor(); 
		if(supervisor == true) {
		System.out.println(login.toString());
		session =  request.getSession();
		session.setAttribute("log_In_Supervisor", login);
		return "redirect:a_noticeList_admin";
		} else {
		session =  request.getSession();
		session.setAttribute("log_In_Admin", login);
		return "redirect:a_noticeList";
		}
	}
	
	//로그아웃
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		System.out.println("세션삭제");
		return "redirect:a_adminLogin";
	}
	
	//아이디찾기 창
	@RequestMapping("find_Id_Page")
	public String find_Id_Page() {
		return "board/login/find_Id_Page";
	}
	 
	//랜덤값 생성해서 이메일로 보내기
	@RequestMapping("id_ChkUp")
	@ResponseBody
	public void id_ChkUp(@RequestParam(value="admin_email")String admin_email) throws IOException {
		int id_Chk;
		String id_ChkString = "";
		 for (int i = 1; i <= 7; i++) {
		      int n = (int) (Math.random() * 10) + 1;
		      id_ChkString += n;
		 }  
		 System.out.println(id_ChkString);
		 id_Chk = Integer.parseInt(id_ChkString);
		 code = id_Chk;
		 userEmail = admin_email;
		ser.id_ChkUp(id_Chk,admin_email);
		
		String setfrom = "kil312055@gmail.com"; //보내는사람
		String tomail = admin_email;
		String title = "DAGACHI 아이디 인증메일 입니다.";
		String content = 
				System.getProperty("line.separator") +
				System.getProperty("line.separator") +
				"아이디 인증번호는 " + id_Chk + " 입니다.";
		
		try {
			MimeMessage message  = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");
			
			helper.setFrom(setfrom);
			helper.setTo(tomail);
			helper.setSubject(title);
			helper.setText(content);
			
			mailSender.send(message);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	//랜덤값과 전송한 입력값이 같은지 확인 
	//일치하면 아이디 정보 보여주기
	 @RequestMapping("id_Chk")
	 @ResponseBody
	 public String getId(@RequestParam(value="request_id_Chk")String request_id_Chk, Model model) {
		 int coderequest = Integer.parseInt(request_id_Chk);
		 if(code == coderequest) {
			 String admin_id = ser.getId(code, userEmail); 
			 return admin_id;
		 } else {
			 return "0";
		 }
	 }
	//비밀번호 찾기 창
	 @RequestMapping("find_Pwd_Page")
	 public String find_Pwd_Page() {
		 return "board/login/find_Pwd_Page";
	 }
	 
	//아이디와 이메일로 비밀번호 변경 인증메일 보내기
	 @RequestMapping("changePwd")
	 @ResponseBody
	 public String changePwd(@RequestParam(value="request_id")String request_id,
			 				 @RequestParam(value="request_email") String email) throws IOException {
		 System.out.println("실행");
		 int id_count = ser.loginIdChk(request_id);
		 System.out.println("아이디개수" + id_count);
		 int acct_count = ser.Chk_Id_Pwd(request_id, email);
		 System.out.println("계정 개수" + acct_count);
		 if(id_count == 0) {
			 return "no_Id";
		 } else if(acct_count == 0) {
			return "no_email";
		 } else {
			 String pwd_ChkStr = "";
			 for(int i = 1 ; i <= 7; i++ ) {
				 int num = (int) (Math.random() * 10) + 1;
				 pwd_ChkStr += num;
			 }
			 
			 ser.id_ChkUp(Integer.parseInt(pwd_ChkStr),email);
			 System.out.println(pwd_ChkStr);
			 
			 String setfrom = "kil312055@gmail.com"; //보내는사람
				String tomail = email;
				String title = "DAGACHI 아이디 인증메일 입니다.";
				String content = 
						System.getProperty("line.separator") +
						System.getProperty("line.separator") +
						"아이디 인증번호는 " + pwd_ChkStr + " 입니다.";
				try {
					MimeMessage message  = mailSender.createMimeMessage();
					MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");
					
					helper.setFrom(setfrom);
					helper.setTo(tomail);
					helper.setSubject(title);
					helper.setText(content);
					
					mailSender.send(message);
				} catch (Exception e) {
					System.out.println(e);
				}
				return "success";
		 }	
	}
	 
	//비밀번호 변경 인증메일 코드 일치여부
	 @RequestMapping("changePwd_2")
	 @ResponseBody
	 public List<String> changePwd_2(@RequestParam(value="request_id")String request_id,
			 				 @RequestParam(value="request_email") String email, 
			 				 @RequestParam(value="pwdCode")String pwdCode , Model m) {
		 List<String> list = new ArrayList<String>();
		 int count = ser.changePwdcount(request_id, email, pwdCode);
		 if(count == 0) {
			 String fail = "fail";
			 list.add(fail);
			 return list;
		 } else {
			 String success = "success";
			 list.add(success);
			 list.add(request_id);
			 list.add(email);
			 return list;
		 }
	 }
	 
	 @RequestMapping("changePwd_3")
	 @ResponseBody
	 public String changePwd_3(@RequestParam(value="request_id")String request_id,
				 @RequestParam(value="request_email") String email, 
				 @RequestParam(value="pwdCode")String pwdCode) {
		 ser.changePwd(pwdCode, request_id, email);
		 return "success";
	 }
	 
	//로그인시 아이디 존재여부 확인
	@RequestMapping("loginIdChk")
	public int loginIdChk(String admin_id) {
		return ser.loginIdChk(admin_id);
	}
	
	//아이디가 존재하면 해당 아이디로 비밀번호 가져오기
	@RequestMapping("getPwd")
	public String getPwd(String admin_id) {
		return ser.loginPwdchk(admin_id);
	}
}
