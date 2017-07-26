package ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ssm.pojo.Question;
import ssm.service.QuestionService;

import java.util.List;

@Controller
@RequestMapping("")
public class IndexController {
	private QuestionService questionService;

	@Autowired
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	@RequestMapping("")
	public ModelAndView toHome() {
		ModelAndView mav = new ModelAndView("index");
	    return mav;
	}

	@RequestMapping("/index")
	public @ResponseBody List<Question> home() {
		List<Question> questions = questionService.getQuestionsByTime();
		return questions;
	}

}
