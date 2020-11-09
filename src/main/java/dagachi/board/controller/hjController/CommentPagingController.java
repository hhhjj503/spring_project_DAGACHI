package dagachi.board.controller.hjController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dagachi.board.model.hjModel.CommentDto;
import dagachi.board.model.hjModel.CommentPagingDto;
import dagachi.board.service.hjService.CommentPagingService;

@Controller
public class CommentPagingController {

	@Autowired
	CommentPagingService ser;

	public void setSer(CommentPagingService ser) {
		this.ser = ser;
	}

	int owner_Notice_Num;

	public void setOwner_Notice_Num(int owner_Notice_Num) {
		this.owner_Notice_Num = owner_Notice_Num;
	}
	//������ȣ�� �´� ��۸� �ҷ��� ������ȣ �ʱ�ȭ
	@RequestMapping(value = "setNumber", method = RequestMethod.GET)
	@ResponseBody
	public void owner_Notice_Num(@RequestParam(value = "owner_Notice_Num") String owner_Notice_Num) {
		int num = Integer.parseInt(owner_Notice_Num);
		this.owner_Notice_Num = num;
		System.out.println("������ȣ ���ۿϷ� :" +num );
	}
	SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//������ȣ�� �´� ��۰��� ǥ��
	@RequestMapping("getCnt")
	@ResponseBody
	public int getCnt() {
		int getCnt = ser.getCnt(this.owner_Notice_Num);
		return getCnt; 
	}
	//��� ���
	@RequestMapping(value = "commentAdd", method = RequestMethod.POST)
	@ResponseBody
	public void insertcmt(CommentDto dto) {
		Date today = new Date();
		String date = transFormat.format(today);
		dto.setCmtDate(date);
		dto.setOwner_Notice_Num(owner_Notice_Num);
		ser.insertcmt(dto);
		System.out.println("��� ��ϵ�");
	}
	//��� 10���� ��������
	@RequestMapping(value = "getComment", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<HashMap<String, Object>> getComment(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "per", defaultValue = "10") int per) {
		ArrayList<HashMap<String, Object>> clist = new ArrayList<HashMap<String, Object>>();
		int notice_Num = this.owner_Notice_Num;
		CommentPagingDto list = ser.selectcmt(pageNum, per, notice_Num);
		Integer count = ser.cmtcnt(notice_Num);
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("list", list);
		hm.put("count", count);
		clist.add(hm);
		System.out.println("��۸�� ��¿Ϸ� : pageNum " + pageNum);
		return clist;
	}
	//��ۻ���, ���� ��й�ȣ ��������
	@RequestMapping(value = "getPwd", method = RequestMethod.POST)
	@ResponseBody
	public String getPwd(@RequestParam(value="cmtNumberS") String cmtNumberS) {
		int cmtNumber = Integer.parseInt(cmtNumberS); 
		 String pwd = ser.getPwd(cmtNumber);
			return pwd;
	}
	//��ۻ���(��α���)
	@RequestMapping(value = "cmtDel", method = RequestMethod.GET)
	@ResponseBody
	public void cmtDel(@RequestParam(value="cmtNumberS") String cmtNumberS) {
		int cmtNumber = Integer.parseInt(cmtNumberS); 
		ser.delcmt(cmtNumber);
		System.out.println("��ۻ����Ϸ�!");
	}
	//��ۻ���(�α���)
	@RequestMapping(value = "loginCmtDel", method = RequestMethod.GET)
	@ResponseBody
	public void loginCmtDel(@RequestParam(value="cmtNumberS") String cmtNumberS) {
		int cmtNumber = Integer.parseInt(cmtNumberS); 
		ser.delcmt(cmtNumber);
		System.out.println("�α��� ��ۻ����Ϸ�!");
	}
	//��۳��밡������
	@RequestMapping(value = "getContents", method = RequestMethod.GET)
	@ResponseBody
	public String getContents(
			@RequestParam(value="cmtNumberS") String cmtNumberS) { 
		int cmtNumber = Integer.parseInt(cmtNumberS); 
		String getContents = ser.getContents(cmtNumber);
		return getContents;
	}
	//��ۻ���
	@RequestMapping(value = "upComment", method = RequestMethod.GET)
	@ResponseBody
	public void cmtDel(@RequestParam(value="cmtNumberS") String cmtNumberS,
						@RequestParam(value="cmtContents") String cmtContents) {
		int cmtNumber = Integer.parseInt(cmtNumberS); 
		ser.upcmt(cmtContents, cmtNumber);
		System.out.println("��ۼ����Ϸ�");
	}
	
}
