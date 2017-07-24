package ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ssm.service.AnswerService;
import ssm.service.QuestionService;
import ssm.service.UserService;

@Controller
@RequestMapping("")
public class IndexController {
	private UserService userService;
	private QuestionService questionService;
	private AnswerService answerService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@Autowired
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	@Autowired
	public void setAnswerService(AnswerService answerService) {
		this.answerService = answerService;
	}

	@RequestMapping("")
	public ModelAndView toHome() {
		ModelAndView mav = new ModelAndView("index");
	    return mav;
	}

	@RequestMapping("/index")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("index");
	    return mav;
	}

}
