package project.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@RequestMapping("homepage/main.do")
	public String goMain() {
		return "homepage/homepageMain";
	}
}