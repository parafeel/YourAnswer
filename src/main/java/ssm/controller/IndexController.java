package ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ssm.pojo.Operation;
import ssm.pojo.Question;
import ssm.pojo.User;
import ssm.service.OperationService;
import ssm.service.QuestionService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("")
public class IndexController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private OperationService operationService;


	@RequestMapping("")
	public ModelAndView toHome() {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}
	@RequestMapping("index")
	public ModelAndView indexHome() {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}

	//首页显示的数据接口，返回用户关注的人的动态数据
	@RequestMapping(value = "/FollowingFeed", method = RequestMethod.GET)
	public @ResponseBody List<Operation> indexFeed(HttpSession session) {
		User currentUser = (User) session.getAttribute("currentUser");
		if(currentUser != null) {
			List<Operation> operations = operationService.getFollowingOperations(currentUser.getuId() , 7);
			return operations;
		} else {
			return null;
		}
	}



}
