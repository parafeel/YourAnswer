package ssm.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ssm.mapper.UserMapper;
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
	//跳转进入登录页面
	public ModelAndView tryLogin() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
	@RequestMapping("userLogin")
	//实现用户登录,用currentUser存储登录成功的用户信息，用loginMessage存储登录信息
	public ModelAndView userLogin(@RequestParam("ulEmail") String uEmail,
								   @RequestParam("ulPassword") String uPassword, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User user = userService.isRightUser(uEmail,uPassword);
		if(null != user) {
			session.setAttribute("currentUser", user);
			session.setAttribute("isLogin", true);
			mav.setViewName("index");
		} else {
			mav.addObject("loginMessage", "账号或密码错误，请重新登录！");
			mav.setViewName("login");
		}
		return mav;
	}
	
	@RequestMapping("userLogout")
	//实现用户退出登录
	public ModelAndView userLogout(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if(session.getAttribute("currentUser") != null) {
			session.removeAttribute("currentUser");
			session.setAttribute("isLogin", false);
		}
		mav.setViewName("redirect:/index");
		return mav;
	}

	@RequestMapping("userRegister")
	//实现用户注册功能
	public ModelAndView userRegister(User user, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User newUser = userService.registUser(user);
		if( null != newUser) {
			mav.setViewName("index");
			session.setAttribute("currentUser", newUser);
			session.setAttribute("isLogin", true);
		} else {
			mav.addObject("registerMessage", "账号已经存在，请重新注册！");
			mav.setViewName("login");
		}
		return mav;
	}

	@RequestMapping("showUser/{uId}")
	//进入用户个人主页
	public ModelAndView showUser(@PathVariable("uId") int uId, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		//获取目标用户的信息
		User pointUser = userService.getUserById(uId);
		if(pointUser == null) {
			mav.addObject("wrongInfoMessage", "你似乎进入未知页面...");
			mav.setViewName("wrongInfo");
			return mav;
		} else {
			//此处为一次性查出所有记录，可拓展为一次查询最近多少条记录
			List<Question> pointUsersQuestion = questionService.getQuestionsByUserId(uId);
			List<Answer> pointUsersAnswer = answerService.getAnswersByUserId(uId);
			mav.addObject("pointUser", pointUser);
			mav.addObject("pointUsersQuestion", pointUsersQuestion);
			mav.addObject("pointUsersAnswer", pointUsersAnswer);
			mav.setViewName("showUser");
			return mav;
		}
	}

	@RequestMapping("userSetting/{uId}")
	//校验是否有权限设置当前用户的信息
	public ModelAndView userSetting(@PathVariable("uId") int uId, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User currentUser = (User) session.getAttribute("currentUser");
		if(null != currentUser) {
			if(uId == currentUser.getuId()) {
				mav.addObject("settingUser", currentUser);
				mav.setViewName("userSetting");
			} else {
				mav.addObject("wrongInfoMessage", "你似乎进入未知页面...");
				mav.setViewName("wrongInfo");
			}
		} else {
			mav.setViewName("login");
		}
		return mav;
	}

	@RequestMapping("updateUserSetting")
	//实现用户信息的更新，预计实现用户头像照片相关,带扩展
	public ModelAndView updateUserSetting(User user, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User currentUser = (User) session.getAttribute("currentUser");
		if(currentUser != null) {
			if(user.getuId() == currentUser.getuId()) {
				//上传照片
				
				//
				User afterUpadteUser = userService.updateUserInfo(user);
				if(null != afterUpadteUser) {
					session.setAttribute("currentUser", afterUpadteUser);
					mav.addObject("currentuId", afterUpadteUser.getuId());
					mav.setViewName("redirect:/showUser/{currentuId}");
				} else {
					mav.addObject("updateUserMessage", "未更新成功");
					mav.addObject("currentuId", afterUpadteUser.getuId());
					mav.setViewName("redirect:/userSetting/{currentuId}");
				}
			} else {
				mav.addObject("wrongInfoMessage", "你似乎进入未知页面...");
				mav.setViewName("wrongInfo");
			}
		} else {
			mav.setViewName("login");
		}
		return mav;
	}

	@RequestMapping("userSecurity/{uId}")
	//校验是否有权限更改安全信息（目前仅仅密码，之后可拓展密保信息）
	public ModelAndView userSecurity(@PathVariable("uId") int uId, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User currentUser = (User) session.getAttribute("currentUser");
		if(null != currentUser) {
			if(uId == currentUser.getuId()) {
				mav.addObject("settingUser", currentUser);
				mav.setViewName("userSecurity");
			} else {
				mav.addObject("wrongInfoMessage", "你似乎进入未知页面...");
				mav.setViewName("wrongInfo");
			}
		} else {
			mav.setViewName("login");
		}
		return mav;
	}

	@RequestMapping("updateUserSecurity")
	//实现用户信息的更新，预计实现用户头像照片相关,带扩展
	public ModelAndView updateUserSecurity(User user, @RequestParam("uNewPassword1") String uNewPassword1, HttpSession
			session) {
		ModelAndView mav = new ModelAndView();
		User currentUser = (User) session.getAttribute("currentUser");
		if( currentUser != null && user != null && user.getuId() == currentUser.getuId()
				&& user.getuEmail().equals(currentUser.getuEmail())) {
			//校验是否为当前用户
			User canRightLogin = userService.isRightUser(currentUser.getuEmail(),user.getuPassword());
			if(null != canRightLogin) {
				user.setuPassword(uNewPassword1);
				int isSuccess = userService.updateUserPassword(user);
				if(isSuccess == 1) {
					mav.addObject("loginMessage", "修改密码成功请重新登陆！");
					mav.setViewName("login");
				} else {
					mav.addObject("wrongInfoMessage", "你似乎进入未知页面...");
					mav.setViewName("wrongInfo");
				}
			} else {
				mav.addObject("uId",currentUser.getuId());
				mav.addObject("updateUserSecurityMessage","修改密码未成功！");
				mav.setViewName("redirect:/userSecurity/{uId}");
			}
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
