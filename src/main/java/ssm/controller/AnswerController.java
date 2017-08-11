package ssm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ssm.pojo.Answer;
import ssm.pojo.Operation;
import ssm.pojo.Question;
import ssm.pojo.User;
import ssm.service.AnswerService;
import ssm.service.OperationService;
import ssm.service.QuestionService;
import ssm.util.UserOperation;

import java.util.HashMap;
import java.util.Map;

/**
 * 回答控制器
 */
@Controller
@RequestMapping("")
public class AnswerController {

	private QuestionService questionService;

	private AnswerService answerService;

	private OperationService operationService;

	@Autowired
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	@Autowired
	public void setAnswerService(AnswerService answerService) {
		this.answerService = answerService;
	}
	@Autowired
	public void setOperationService(OperationService operationService) {
		this.operationService = operationService;
	}

	//此处使用rest风格的URL

	
	@RequestMapping(value = "api/Answer/{qId}",method = RequestMethod.POST)
	public @ResponseBody int addAnswer(@PathVariable("qId") Integer qId, Answer answer, HttpSession session) {
		User currentUser = (User)session.getAttribute("currentUser");
		if(currentUser == null) {
			return -1;	//表示未登录
		} else {
			Question currentQuesstion = questionService.getQuestionById(qId);
			Answer currentAnswer = answerService.putAnswer(answer,currentQuesstion,currentUser);	//添加回答
			if(currentAnswer != null) {
				operationService.putOperation(new Operation(currentAnswer.getaMadeByUserId(), UserOperation.TYPE_ANSWER,
						currentAnswer.getaId()));
				int aId =currentAnswer.getaId();
				return aId;
			} else {
				return 0;
			}
		}
	}
	
	@RequestMapping("Question/{qId}/Answer/{aId}")
	public ModelAndView showAnswer(@PathVariable("aId") int aId) {
		ModelAndView mav = new ModelAndView();
		Answer currentAnswer = answerService.getAnserById(aId);
		if(currentAnswer == null ) {
			mav.addObject("wrongInfoMessage","你似乎进入未知页面...");
			mav.setViewName("wrongInfo");
		} else {
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

	//获取Answer的feed流
	@RequestMapping(value = "api/onlyAnswer/{aId}", method = RequestMethod.GET)
	public @ResponseBody Answer getAnswer(@PathVariable("aId") int aId) {
		Answer currentAnswer = answerService.getAnserById(aId);
		if(currentAnswer == null) {
			return null;
		} else {
			return currentAnswer;
		}
	}


}
