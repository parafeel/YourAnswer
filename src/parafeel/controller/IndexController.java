package parafeel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class IndexController {
	
	@RequestMapping("/index")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("index");
	    return mav;
	}
}
