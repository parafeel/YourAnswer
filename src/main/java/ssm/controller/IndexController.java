package ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ssm.pojo.Answer;
import ssm.pojo.Operation;
import ssm.pojo.User;
import ssm.service.AnswerService;
import ssm.service.JsonService;
import ssm.service.OperationService;
import ssm.util.StatusCode;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("")
public class IndexController {

	private OperationService operationService;

	private JsonService jsonService;

	private AnswerService answerService;

	@Autowired
	public void setOperationService(OperationService operationService) {
		this.operationService = operationService;
	}
	@Autowired
	public void setJsonService(JsonService jsonService) {
		this.jsonService = jsonService;
	}
	@Autowired
	public void setAnswerService(AnswerService answerService) {
		this.answerService = answerService;
	}

	//REST风格的URL
	//进入首页
	@RequestMapping("")
	public String toHome() {
		return "index";
	}
	@RequestMapping("index")
	public String indexHome() {
		return "index";
	}

	//显示进入未知页面
	@RequestMapping(value="wrongInfo")
	public ModelAndView goToWrongInfoPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("wrongInfo");
		mav.addObject("wrongInfoMessage","您似乎进入未知页面");
		return mav;
	}

//API
	//首页显示的数据接口，返回用户关注的人的动态数据
	@RequestMapping(value = "api/FollowingFeed", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody String indexFeed(HttpSession session) {
		User currentUser = (User) session.getAttribute("currentUser");
		List<Operation> operations;
		String rs;
		if(currentUser != null) {
			operations = operationService.getFollowingOperations(currentUser.getuId() , 7);
			rs = jsonService.toJsonString(operations, StatusCode.CODE_SUCCESS,StatusCode.REASON_SUCCESS);
		} else {
			rs = jsonService.toJsonString(null,StatusCode.CODE_FAILURE,StatusCode.REASON_FAILURE);
		}
		return rs;
	}
	@RequestMapping(value = "opt/{optId}")
	public ModelAndView showQuestion(@PathVariable("optId") int optId) {
		ModelAndView mav = new ModelAndView();
		Operation operation = operationService.getOperationById(optId);
		if(operation == null) {
			mav.setViewName("redirect:/wrongInfo");
		} else {
			int operationId = operation.getOperationId();
			if(operation.getOperationType() == 100) {
				mav.setViewName("redirect:/Question/" + operationId);
			} else if(operation.getOperationType() == 101) {
				Answer answer = answerService.getAnserById(operationId);
				mav.setViewName("redirect:/Question/" + answer.getaBelongToQuestionId() +
						"/Answer/"  + answer.getaId());
			} else if(operation.getOperationType() == 102) {
				mav.setViewName("redirect:/Essay/"  + operationId);
			} else {
				mav.setViewName("redirect:/wrongInfo");
			}
		}
		return mav;
	}


}
