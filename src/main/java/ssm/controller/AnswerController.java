package ssm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ssm.mapper.AnswerMapper;
import ssm.pojo.Answer;
import ssm.pojo.Operation;
import ssm.pojo.Question;
import ssm.pojo.User;
import ssm.service.AnswerService;
import ssm.service.OperationService;
import ssm.service.QuestionService;
import ssm.service.UserService;
import ssm.util.UserOperation;

@Controller
@RequestMapping("")
public class AnswerController {

	@Autowired
	private QuestionService questionService;
	@Autowired
	private AnswerService answerService;
	@Autowired
	private UserService userService;
	@Autowired
	private OperationService operationService;


	//此处使用rest风格的URL
	@RequestMapping("makeAnswer/{qId}")
	public ModelAndView makeAnswer(@PathVariable ("qId") Integer qId) {
		Question currentQuestion = questionService.getQuestionById(qId);
		ModelAndView mav = new ModelAndView();
		mav.addObject("currentQuestion", currentQuestion);
		mav.addObject("qId", qId);
		mav.setViewName("addAnswer");
		return mav;
	}
	
	@RequestMapping("addAnswer/{qId}")
	public ModelAndView addAnswer(@PathVariable("qId") Integer qId, Answer answer, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String addAnswerMessage;
		boolean flag;
		User currentUser = (User)session.getAttribute("currentUser");
		Question currentQuesstion = questionService.getQuestionById(qId);
		if(currentUser == null) {
			addAnswerMessage = "未登陆，登陆后才可回答！";
			mav.addObject("currentQuestion", currentQuesstion);
			mav.addObject("addAnswerMessage", addAnswerMessage);
			mav.setViewName("addAnswer");
			return mav;
		} else {
			Answer currentAnswer = answerService.putAnswer(answer,currentQuesstion,currentUser);
			//添加回答成功
			if(currentAnswer != null) {
				operationService.putOperation(new Operation(currentAnswer.getaMadeByUserId(), UserOperation.TYPE_ANSWER,
						currentAnswer.getaId()));
				mav.addObject("aId", currentAnswer.getaId());
				mav.addObject("qId", qId);
				mav.setViewName("redirect:/Question/{qId}/showAnswer/{aId}");
			} else {
				addAnswerMessage = "未回答成功，似乎您已回答过此问题！";
				mav.addObject("currentQuestion", currentQuesstion);
				mav.addObject("addAnswerMessage", addAnswerMessage);
				mav.addObject("qId", qId);
				mav.setViewName("addAnswer");
			}
		}
		return mav;
	}
	
	@RequestMapping("Question/{qId}/showAnswer/{aId}")
	public ModelAndView showAnswer(@PathVariable("qId") int qId,@PathVariable("aId") int aId) {
		ModelAndView mav = new ModelAndView();
		Question currentQuestion = questionService.getQuestionById(qId);
		Answer currentAnswer = answerService.getAnserById(aId);
		User theAnsweredUser = userService.getUserById(currentAnswer.getaMadeByUserId());
		if(currentQuestion == null || currentAnswer == null || theAnsweredUser == null) {
			mav.addObject("wrongInfoMessage","你似乎进入未知页面...");
			mav.setViewName("wrongInfo");
		} else {
			currentAnswer.setaMadeByUser(theAnsweredUser);
			mav.addObject("currentQuestion", currentQuestion);
			mav.addObject("currentAnswer", currentAnswer);
			mav.setViewName("showAnswer");
		}
		return mav;
	}

	@RequestMapping("answer/{aId}/update")
	public ModelAndView tryUpdateAnswer(@PathVariable("aId") int aId, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User currentUser = (User)session.getAttribute("currentUser");
		Answer currentAnswer = answerService.getAnserById(aId);
		if(currentUser == null || currentAnswer == null || currentUser.getuId() != currentAnswer.getaMadeByUserId()) {
			mav.setViewName("redirect:/");
		} else {
			mav.addObject("currentAnswer", currentAnswer);
			mav.setViewName("updateAnswer");
		}
		return mav;
	}

	@RequestMapping("updateAnswer/{aId}")
	public ModelAndView updateAnswer(Answer answer) {
		ModelAndView mav = new ModelAndView();
		boolean isUpdate = answerService.updateAnswerById(answer);
		if(isUpdate) {
			Answer currentAnswer = answerService.getAnserById(answer.getaId());
			mav.addObject("qId", currentAnswer.getaBelongToQuestionId());
			mav.addObject("aId", currentAnswer.getaId());
			mav.setViewName("redirect:/Question/{qId}/showAnswer/{aId}");
		} else {
			mav.addObject("updateAnswerMessage","问题未修改成功！");
			mav.addObject("currentAnswer", answer);
			mav.setViewName("updateAnswer");
		}
		return mav;
	}


}
