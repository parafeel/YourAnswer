package ssm.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ssm.pojo.Answer;
import ssm.pojo.Question;
import ssm.pojo.User;
import ssm.service.AnswerService;
import ssm.service.QuestionService;
import ssm.service.UserService;

@Controller
@RequestMapping("")
public class UserController {

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

	@RequestMapping("login")
	public ModelAndView tryLogin() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
	@RequestMapping("userLogin")
	public ModelAndView userLogin(@RequestParam("ulEmail") String uEmail,
			@RequestParam("ulPassword") String uPassword, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User user = userService.isUser(uEmail,uPassword);
		String loginMessage;
		if( user != null) {
			mav.setViewName("index");
			session.setAttribute("currentUser", user);
			session.setAttribute("isLogin", true);
		} else {
			loginMessage = "账号密码错误，请重新登录！";
			mav.addObject("loginMessage", loginMessage);
			mav.setViewName("login");
		}
		return mav;
	}
	
	@RequestMapping("userLogout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if(session.getAttribute("currentUser") != null) {
			session.removeAttribute("currentUser");
			session.setAttribute("isLogin", false);
		}
		mav.setViewName("redirect:/index");
		return mav;
	}

	@RequestMapping("userRegister")
	public ModelAndView tryRegister(User user, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User newUser = userService.registUser(user);
		String registerMessage;
		if( newUser != null) { 
			mav.setViewName("index");
			session.setAttribute("currentUser", newUser);
			session.setAttribute("isLogin", true);
		} else {
			mav.setViewName("login");
			registerMessage = "账号已经存在，请重新注册！";
			mav.addObject("registerMessage", registerMessage);
		}
		return mav;
	}
	
	@RequestMapping("showUser/{uId}")
	public ModelAndView showUser(@PathVariable("uId") int uId, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User pointUser = userService.getUserById(uId);
		if(pointUser == null) {
			mav.addObject("wrongInfoMessage", "你似乎进入未知页面...");
			mav.setViewName("wrongInfo");
			return mav;
		} else {
			List<Question> pointUsersQuestion = questionService.getQuestionsByUserId(uId);
			List<Answer> pointUsersAnswer = answerService.getAnswersByUserId(uId);
			mav.addObject("pointUser", pointUser);
			mav.addObject("pointUsersQuestion", pointUsersQuestion);
			mav.addObject("pointUsersAnswer", pointUsersAnswer);
			mav.setViewName("showUser");
			return mav;
		}
	}
	
	//userSetting
	@RequestMapping("setting/{uId}")
	public ModelAndView setting(@PathVariable("uId") int uId, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User currentUser = (User) session.getAttribute("currentUser");
		if(currentUser != null) {
			int currentuId = currentUser.getuId();
			if(uId == currentuId) {
				User settingUser = userService.getUserById(currentuId);
				mav.addObject("settingUser", settingUser);
				mav.setViewName("userSetting");
			} else {
				mav.setViewName("index");
			}
		} else {
			mav.setViewName("login");
		}
		return mav;
	}

	@RequestMapping("userSetting")
	public ModelAndView userSetting(User user, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User currentUser = (User) session.getAttribute("currentUser");
		if(currentUser != null) {
			int currentuId = currentUser.getuId();
			if(user.getuId() == currentuId) {
				//上传照片
				
				//
				User afterUpadteUser = userService.updateUserInfo(user);
				session.setAttribute("currentUser", afterUpadteUser);
				mav.addObject("currentuId", afterUpadteUser.getuId());
				mav.setViewName("redirect:/showUser/{currentuId}");
			} else {
				mav.setViewName("index");
			}
		} else {
			mav.setViewName("login");
		}
		return mav;
	}
	
	@RequestMapping("showAllUser")
	public ModelAndView listUser() {
		ModelAndView mav = new ModelAndView();
		List<User> userList = userService.listUser();
		mav.addObject("userList", userList);
		mav.setViewName("showAllUser");
		return mav;
	}
	
}
