package dagachi.board.controller.hjController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerMainController {
	
	@RequestMapping("c_main")
	public String goMain() {
		return "board/c_main";
	}
	
}
