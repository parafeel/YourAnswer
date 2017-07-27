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

	//提供url: YourAnswer/index，来检索所有问题（JSON格式）的数据接口。
	@RequestMapping("/index")
	public @ResponseBody List<Question> home() {
		List<Question> questions = questionService.getQuestionsByTime();
		return questions;
	}

}
