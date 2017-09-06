package ssm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import ssm.pojo.*;
import ssm.service.*;
import ssm.util.StatusCode;

@Controller
@RequestMapping("")
public class QuestionController {

	private QuestionService questionService;

	private AnswerService answerService;

	private UserService userService;

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
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@Autowired
	public void setOperationService(OperationService operationService) {
		this.operationService = operationService;
	}
	@Autowired
	public void setJsonService(JsonService jsonService) {
		this.jsonService = jsonService;
	}

	//提问
	@RequestMapping("makeQuestion")
	public ModelAndView tryQuestion(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User currentUser = (User)session.getAttribute("currentUser");
		if(currentUser == null) {
			mav.addObject("loginMessage","登录后才能提问！");
			mav.setViewName("login");
			return mav;
		}
		mav.setViewName("Question/addQuestion");
		return mav;
	}

	//查看
	@RequestMapping("Question/{qId}")
	public ModelAndView showQuestion(@PathVariable("qId") int qId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("qId",qId);
		mav.setViewName("Question/showQuestion");
		return mav;
	}

	//列表查看
	@RequestMapping("listQuestion")
	public String listQuestions() {
		return "Question/listQuestion";
	}

//相关API
	//增加问题API
	@RequestMapping(value = "api/Question",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String  addQuestion(Question question,  HttpSession session,
			 @RequestParam(value = "qTopics[]", required = false,defaultValue = "") String[] qTopics) {
		User currentUser = (User)session.getAttribute("currentUser");
		System.out.println(qTopics);
		String rs;
		if(currentUser != null && question.getqTitle()!= null && !question.getqTitle().trim().equals("")) {
			Question currentQuestion = questionService.putQuestion(question, currentUser.getuId(),qTopics);
			if (currentQuestion != null) {
				Operation operation = new Operation(currentQuestion.getqMadeByUserId(), StatusCode.TYPE_QUESTION,
						currentQuestion.getqId() , StatusCode.FOCUS_QUESTION);
				operationService.putOperation(operation);
				rs = jsonService.toJsonString(currentQuestion, StatusCode.CODE_SUCCESS, StatusCode.REASON_SUCCESS);
			} else {
				rs = jsonService.toJsonString(null, StatusCode.CODE_FAILURE, StatusCode.REASON_FAILURE);
			}
		} else {
			rs = jsonService.toJsonString(null, StatusCode.CODE_FAILURE, StatusCode.REASON_FAILURE);
		}
		return rs;
	}

	//删除问题API
	@RequestMapping(value = "api/Question/{qId}",method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
	public @ResponseBody String deleteQuestion(@PathVariable("qId") int qId, HttpSession session) {
		User currentUser = (User)session.getAttribute("currentUser");
		int currentUserAuthority = userService.getUserAuthority(currentUser);
		String rs;
		if(currentUserAuthority == 100 ) {
			rs = jsonService.toJsonString(null,StatusCode.CODE_SUCCESS,StatusCode.REASON_SUCCESS);
		} else {
			rs = jsonService.toJsonString(null,StatusCode.CODE_FAILURE,StatusCode.REASON_FAILURE);
		}
		return rs;
	}

	//查询问题API
	@RequestMapping(value = "api/Question/{qId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody String getQuestion(@PathVariable("qId") int qId, HttpSession session) {
		Question currentQuestion = questionService.getQuestionById(qId);
		String rs;
		if(currentQuestion == null) {
			rs = jsonService.toJsonString(null,StatusCode.CODE_FAILURE,StatusCode.REASON_FAILURE);
		} else {
			rs = jsonService.toJsonString(currentQuestion,StatusCode.CODE_SUCCESS,StatusCode.REASON_SUCCESS);
		}
		return rs;
	}

	//列表查询问题
	@RequestMapping(value = "api/Questions",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody String Questions() {
		List<Question> questions = questionService.getQuestionsByTime();
		String rs;
		rs = jsonService.toJsonString(questions,StatusCode.CODE_SUCCESS,StatusCode.REASON_SUCCESS);
		return rs;
	}

	//获取Question的feed流
	@RequestMapping(value = "api/onlyQuestion/{qId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody String feedQuestion(@PathVariable("qId") int qId) {
		Question currentQuestion = questionService.getQuestionById(qId);
		String rs;
		if(currentQuestion == null) {
			rs = jsonService.toJsonString(null,StatusCode.CODE_FAILURE,StatusCode.REASON_FAILURE);;
		} else {
			rs = jsonService.toJsonString(currentQuestion,StatusCode.CODE_SUCCESS,StatusCode.REASON_SUCCESS);
		}
		return rs;
	}


	
}
