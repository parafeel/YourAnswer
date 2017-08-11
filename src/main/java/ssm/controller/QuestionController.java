package ssm.controller;

import java.util.List;
import java.util.Map;

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
import ssm.service.UserService;
import ssm.util.UserOperation;

@Controller
@RequestMapping("")
public class QuestionController {

	private QuestionService questionService;

	private AnswerService answerService;

	private UserService userService;

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
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@Autowired
	public void setOperationService(OperationService operationService) {
		this.operationService = operationService;
	}

	//提问
	@RequestMapping("makeQuestion")
	public ModelAndView tryQuestion(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User currentUser = (User)session.getAttribute("currentUser");
		if(currentUser == null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		mav.setViewName("addQuestion");
		return mav;
	}
	//查看
	@RequestMapping("Question/{qId}")
	public ModelAndView showQuestion(@PathVariable("qId") int qId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("qId",qId);
		mav.setViewName("showQuestion");
		return mav;
	}
	//列表查看
	@RequestMapping("listQuestion")
	public String listQuestions() {
		return "listQuestion";
	}

//相关API
	//增加问题API
	@RequestMapping(value = "api/Question",method = RequestMethod.POST)
	public @ResponseBody int  addQuestion(Question question, HttpSession session) {
		User currentUser = (User)session.getAttribute("currentUser");
		if(currentUser == null) {
			return -1;	//表示未登录
		} else {
			Question currentQuestion = questionService.putQuestion(question,currentUser.getuId());
			if(currentQuestion != null) {
				operationService.putOperation(new Operation(currentQuestion.getqMadeByUserId(), UserOperation.TYPE_QUESTION,
						currentQuestion.getqId()));
				return currentQuestion.getqId();	//提问成功，返回问题qId
			} else {
				return 0;	//表示未提问成功
			}
		}
	}

	//删除问题API
	@RequestMapping(value = "api/Question/{qId}",method = RequestMethod.DELETE)
	public @ResponseBody boolean deleteQuestion(@PathVariable("qId") int qId, HttpSession session) {
		User currentUser = (User)session.getAttribute("currentUser");
		int currentUserAuthority = userService.getUserAuthority(currentUser);
		boolean flag;
		if(currentUserAuthority == 100 ) {
			flag = questionService.deleteQuestionById(qId);
			return  flag;
		}
		return false;
	}

	//查询问题API
	@RequestMapping(value = "api/Question/{qId}", method = RequestMethod.GET)
	public @ResponseBody Question getQuestion(@PathVariable("qId") int qId, HttpSession session) {
		Question currentQuestion = questionService.getQuestionById(qId);
		if(currentQuestion == null) {
			return null;
		}
		List<Answer> answers = answerService.getAnswerByQuestion(qId);
		User currentUser = (User)session.getAttribute("currentUser");
		if(currentUser == null) {
			currentQuestion.setqAnswers(answers);
			return currentQuestion;
		}
		//此处是为了强行实现在页面中可以增加判断，是否能修改，写的极差
		for (int i = 0; i < answers.size(); i++) {
			if (answers.get(i).getaMadeByUserId() == currentUser.getuId()) {
				answers.get(i).setCanUpdate(true);
				break;
			}
		}
		//Question currentQuestion = questionService.getCompleteQuesionById(qId);
		currentQuestion.setqAnswers(answers);
		return currentQuestion;
	}

	//列表查询问题
	@RequestMapping(value = "api/Questions",method = RequestMethod.GET)
	public @ResponseBody List<Question> Questions() {
		List<Question> questions = questionService.getQuestionsByTime();
		return questions;
	}

	//获取Question的feed流
	@RequestMapping(value = "api/onlyQuestion/{qId}", method = RequestMethod.GET)
	public @ResponseBody Question feedQuestion(@PathVariable("qId") int qId) {
		Question currentQuestion = questionService.getQuestionById(qId);
		if(currentQuestion == null) {
			return null;
		} else return currentQuestion;
	}


	
}
