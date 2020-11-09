package dagachi.board.controller.hjController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Example {
	
	@RequestMapping("/Add")
	public String getData(Model model) {
		return "homepage/Add";
	}
	
	
}
