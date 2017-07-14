package ssm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ssm.pojo.Answer;
import ssm.pojo.Question;
import ssm.pojo.User;
import ssm.service.AnswerService;
import ssm.service.QuestionService;

@Controller
@RequestMapping("")
public class QuestionController {

	private QuestionService questionService;
	private AnswerService answerService;

	@Autowired
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	@Autowired
	public void setAnswerService(AnswerService answerService) {
		this.answerService = answerService;
	}

	@RequestMapping("makeQuestion")
	public ModelAndView tryQuestion() {
		ModelAndView mav = new ModelAndView("addQuestion");
		return mav;
	}
	
	@RequestMapping("addQuestion")
	public ModelAndView addQuestion(Question question, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String addQuestionMessage;
		User currentUser = (User)session.getAttribute("currentUser");
		if(currentUser == null) {
			addQuestionMessage = "您还未登陆，登陆后才可提问！";
			mav.addObject("addQuestionMessage", addQuestionMessage);
			mav.setViewName("addQuestion");
			return mav;
		} else {
			question.setqMadeByUserId(currentUser.getuId());
			questionService.putQuestion(question);
			Question currentQuestion = questionService.getQuestion(question.getqTitle());
			if(currentQuestion != null) {
				mav.addObject("qId", currentQuestion.getqId());
				mav.addObject("currentQuestion", currentQuestion);
				mav.setViewName("redirect:/Question/{qId}");
			} else {
				mav.setViewName("addQuestion");
				addQuestionMessage = "问题未提问成功！";
				mav.addObject("addQuestionMessage", addQuestionMessage);
			}
		}
		return mav;
	}
	
	
	
	@RequestMapping("listQuestion")
	public ModelAndView listQuestions() {
		ModelAndView mav = new ModelAndView();
		List<Question> questions = questionService.getQuestionByTime();
		mav.addObject("questions", questions);
		mav.setViewName("listQuestion");
		return mav;
	}
	
	@RequestMapping("Question/{qId}")
	public ModelAndView showQuestion(@PathVariable("qId") int qId) {
		ModelAndView mav = new ModelAndView();
		Question currentQuestion = questionService.getQuestionById(qId);
		List<Answer> answers = answerService.getAnswerByQuestion(currentQuestion.getqId());
		mav.addObject("currentQuestion", currentQuestion);
		mav.addObject("answers", answers);
		mav.setViewName("showQuestion");
		return mav;
	}
	
}
