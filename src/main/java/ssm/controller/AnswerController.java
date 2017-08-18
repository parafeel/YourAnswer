package ssm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ssm.pojo.*;
import ssm.service.AnswerService;
import ssm.service.JsonService;
import ssm.service.OperationService;
import ssm.service.QuestionService;
import ssm.util.StatusCode;

/**
 * 回答控制器
 */
@Controller
@RequestMapping("")
public class AnswerController {

	private QuestionService questionService;

	private AnswerService answerService;

	private OperationService operationService;

	private JsonService jsonService;

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
	@Autowired
	public void setJsonService(JsonService jsonService) {
		this.jsonService = jsonService;
	}

//此处使用rest风格的URL

	//查看Answer
	@RequestMapping("Question/{qId}/Answer/{aId}")
	public ModelAndView showAnswer(@PathVariable("aId") int aId) {
		ModelAndView mav = new ModelAndView();
		Answer currentAnswer = answerService.getAnserById(aId);
		if(currentAnswer == null ) {
			mav.setViewName("redirect:wrongInfo");
		} else {
			mav.addObject("currentAnswer", currentAnswer);
			mav.setViewName("AboutAnswer/showAnswer");
		}
		return mav;
	}

	//修改Answer
	@RequestMapping("Answer/{aId}/update")
	public ModelAndView tryUpdateAnswer(@PathVariable("aId") int aId, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User currentUser = (User)session.getAttribute("currentUser");
		Answer currentAnswer = answerService.getAnserById(aId);
		if(currentUser == null || currentAnswer == null || currentUser.getuId() != currentAnswer.getaMadeByUserId()) {
			mav.setViewName("redirect:/");
		} else {
			mav.addObject("currentAnswer", currentAnswer);
			mav.setViewName("AboutAnswer/updateAnswer");
		}
		return mav;
	}



//相关API
	//增加Answer的API
	@RequestMapping(value = "api/Answer/{qId}",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String addAnswer(@PathVariable("qId") Integer qId, Answer answer, HttpSession session) {
		User currentUser = (User)session.getAttribute("currentUser");
		String rs;
		if(currentUser == null) {
			rs = jsonService.toJsonString(null,StatusCode.CODE_FAILURE,StatusCode.REASON_FAILURE);
		} else {
			Question currentQuesstion = questionService.getQuestionById(qId);
			Answer currentAnswer = answerService.putAnswer(answer,currentQuesstion,currentUser);	//添加回答
			if(currentAnswer != null) {
				Operation operation = new Operation(currentAnswer.getaMadeByUserId(), StatusCode.TYPE_ANSWER,
						currentAnswer.getaId(),  StatusCode.ANSWER_QUESTION);
				operationService.putOperation(operation);
				rs = jsonService.toJsonString(currentAnswer,StatusCode.CODE_SUCCESS,StatusCode.REASON_SUCCESS);
			} else {
				rs = jsonService.toJsonString(null,StatusCode.CODE_DUPLICATE,StatusCode.REASON_FAILURE);
			}
		}
		return rs;	//表示未登录
	}

	//修改Answer的API
	@RequestMapping(value = "api/Answer/{aId}",method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
	public @ResponseBody String updateAnswer(@PathVariable("aId") int aId, Answer answer, HttpSession session) {
		User currentUser = (User)session.getAttribute("currentUser");
		Answer currentAnswer = answerService.getAnserById(aId);
		String rs;
		if(currentUser == null || currentAnswer == null || answer.getaId() != aId ||
				currentAnswer.getaMadeByUserId() != currentUser.getuId()) {
			rs = jsonService.toJsonString(null,StatusCode.CODE_FAILURE,StatusCode.REASON_FAILURE);
		} else {
			boolean isUpdate = answerService.updateAnswerById(answer);
			if(isUpdate) {
				rs = jsonService.toJsonString(answerService.getAnserById(aId),StatusCode.CODE_SUCCESS,
						StatusCode.REASON_SUCCESS);
			} else {
				rs = jsonService.toJsonString(null,StatusCode.CODE_FAILURE,StatusCode.REASON_FAILURE);
			}
		}
		return rs;
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
