package ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import ssm.pojo.Answer;
import ssm.pojo.Question;
import ssm.pojo.User;
import ssm.service.AnswerService;
import ssm.service.QuestionService;
import ssm.service.UserService;

import java.util.List;

@Controller
@RequestMapping("")
public class SearchController {

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

	//增加简单搜索功能，搜索与关键字相关的题目或问题
	//可优化查询之后点击进入对应记录后的 查询性能。
	@RequestMapping("searchKeywords")
	public ModelAndView searchKeywords(@RequestParam("keywords") String keywords) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("keywords",keywords);
		List<Question> relatedQuestions = questionService.getQuestionsByKeywords(keywords);
		List<Answer> relatedAnswers = answerService.getAnswersByKeyWords(keywords);
		List<User> relatedUsers = userService.getUsersByKeyWords(keywords);

		mav.addObject("relatedQuestions",relatedQuestions);
		mav.addObject("relatedAnswers",relatedAnswers);
		mav.addObject("relatedUsers",relatedUsers);
		mav.setViewName("showSearchResults");
		return mav;
	}

}
