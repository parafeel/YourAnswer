package ssm.controller;

import java.util.HashMap;
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

	@Autowired
	private QuestionService questionService;
	@Autowired
	private AnswerService answerService;
	@Autowired
	private UserService userService;
	@Autowired
	private OperationService operationService;


	@RequestMapping("makeQuestion")
	public ModelAndView tryQuestion() {
		ModelAndView mav = new ModelAndView("addQuestion");
		return mav;
	}

	/**	//试着用Ajax实现提问功能，之后发现只要Ajax发来数据成功，返回都进入了success回调，还需要解析Json来实现跳转，
	 * //还不如单纯用form
	@RequestMapping("addQuestion")
	@ResponseBody	//使用Ajax必须要有
	public Map<String,Object> addQuestion(Question question, HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		String addQuestionMessage;
		User currentUser = (User)session.getAttribute("currentUser");
		if(currentUser == null) {
			addQuestionMessage = "您还未登陆，登陆后才可提问！";
			resultMap.put("addQuestionMessage", addQuestionMessage);
			return resultMap;
		} else {
			if(questionService.getQuestion(question.getqTitle()) != null) {
				addQuestionMessage = "已存在相同问题！";
				resultMap.put("addQuestionMessage", addQuestionMessage);
				return resultMap;
			} else {
				question.setqMadeByUserId(currentUser.getuId());
				questionService.putQuestion(question);
				Question currentQuestion = questionService.getQuestion(question.getqTitle());
				if(currentQuestion != null) {
					resultMap.put("qId", currentQuestion.getqId());
					addQuestionMessage = "提问成功！";
					resultMap.put("addQuestionMessage", addQuestionMessage);
				} else {
					addQuestionMessage = "似乎出现了问题！";
					resultMap.put("addQuestionMessage", addQuestionMessage);
				}
			}
		}
		return resultMap;
	}**/

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
			Question currentQuestion = questionService.putQuestion(question,currentUser.getuId());
			if(currentQuestion != null) {
				operationService.putOperation(new Operation(currentQuestion.getqMadeByUserId(), UserOperation.TYPE_QUESTION,
						currentQuestion.getqId()));
				mav.addObject("qId", currentQuestion.getqId());
				mav.addObject("currentQuestion", currentQuestion);
				mav.setViewName("redirect:/Question/{qId}");
			} else {
				addQuestionMessage = "未提问成功，似乎已有相同问题存在！";
				mav.addObject("addQuestionMessage", addQuestionMessage);
				mav.setViewName("addQuestion");
			}
		}
		return mav;
	}
	
	
	
	@RequestMapping("listQuestion")
	public ModelAndView listQuestions() {
		ModelAndView mav = new ModelAndView();
		List<Question> questions = questionService.getQuestionsByTime();
		mav.addObject("questions", questions);
		mav.setViewName("listQuestion");
		return mav;
	}
	
	@RequestMapping("Question/{qId}")
	public ModelAndView showQuestion(@PathVariable("qId") int qId) {
		ModelAndView mav = new ModelAndView();
		Question currentQuestion = questionService.getQuestionById(qId);
		if(currentQuestion == null) {
			mav.addObject("wrongInfoMessage","你似乎进入未知页面...");
			mav.setViewName("wrongInfo");
		} else {
			List<Answer> answers = answerService.getAnswerByQuestion(currentQuestion.getqId());
			mav.addObject("currentQuestion", currentQuestion);
			mav.addObject("answers", answers);
			mav.setViewName("showQuestion");
		}
		return mav;
	}

	@RequestMapping(value = "feedQuestion/{qId}", method = RequestMethod.GET)
	public @ResponseBody Question getQuestion(@PathVariable("qId") int qId) {
		Question currentQuestion = questionService.getQuestionById(qId);
		if(currentQuestion == null) {
			return null;
		} else {
			return currentQuestion;
		}
	}

	@RequestMapping(value = "Question/{qId}",method = RequestMethod.DELETE)
	public @ResponseBody boolean deleteQuestion(@PathVariable("qId") int qId, HttpSession session) {
		User currentUser = (User)session.getAttribute("currentUser");
		int currentUserAuthority = userService.getUserAuthority(currentUser);
		if(currentUserAuthority == 100 ) {
			boolean flag = questionService.deleteQuestionById(qId);
			return  flag;
		}
		return false;
	}
	
}
