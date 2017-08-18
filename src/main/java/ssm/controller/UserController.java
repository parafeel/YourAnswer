package ssm.controller;


import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import ssm.pojo.Answer;
import ssm.pojo.Essay;
import ssm.pojo.Question;
import ssm.pojo.User;
import ssm.service.*;
import ssm.util.CaptchaUtil;
import ssm.util.StatusCode;
import ssm.util.UserRelation;

@Controller
@RequestMapping("")
public class UserController {

	private UserService userService;

	private QuestionService questionService;

	private AnswerService answerService;

	private EssayService essayService;

	private UserRelationService userRelationService;

	private JsonService jsonService;

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

	@Autowired
	public void setEssayService(EssayService essayService) {
		this.essayService = essayService;
	}

	@Autowired
	public void setUserRelationService(UserRelationService userRelationService) {
		this.userRelationService = userRelationService;
	}

	@Autowired
	public void setJsonService(JsonService jsonService) {
		this.jsonService = jsonService;
	}

//此处使用rest风格的URL
	@RequestMapping("login")
	//跳转进入登录页面
	public ModelAndView tryLogin() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("loginMessage","请输入账号密码！");
		mav.setViewName("User/login");
		return mav;
	}

	//增加登录验证码功能
	@RequestMapping(value = "/captcha", method = RequestMethod.GET)
	@ResponseBody
	public void captcha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CaptchaUtil.outputCaptcha(request, response);
		String randomString = (String) request.getSession().getAttribute("randomString");
		System.out.println("randomString : " + randomString);
	}


	@RequestMapping(value = "userSetting/{uId}")
	//校验是否有权限设置当前用户的信息
	public ModelAndView userSetting(@PathVariable("uId") int uId, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User currentUser = (User) session.getAttribute("currentUser");
		if (null != currentUser) {
			if (uId == currentUser.getuId()) {
				mav.addObject("settingUser", currentUser);
				mav.setViewName("User/userSetting");
			} else {
				mav.addObject("wrongInfoMessage", "你似乎进入未知页面...");
				mav.setViewName("wrongInfo");
			}
		} else {
			mav.setViewName("User/login");
		}
		return mav;
	}

	@RequestMapping("user/{uId}")
	//进入用户个人信息
	public ModelAndView showUser(@PathVariable("uId") int uId) {
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
			List<Essay> pointUserEssay = essayService.getEssayByUserId(uId);
			mav.addObject("pointUser", pointUser);
			mav.addObject("pointUsersQuestion", pointUsersQuestion);
			mav.addObject("pointUsersAnswer", pointUsersAnswer);
			mav.addObject("pointUserEssay", pointUserEssay);
			mav.setViewName("User/showUser");
			return mav;
		}
	}

//用户相关API
	@RequestMapping(value = "api/userLogin", method = RequestMethod.POST ,produces = "application/json;charset=UTF-8" )
	//实现用户登录,用currentUser存储登录成功的用户信息，用loginMessage存储登录信息 VerificationCode
	public @ResponseBody
	String userLogin(@RequestParam("uEmail") String uEmail, @RequestParam("uPassword") String uPassword, HttpSession
			session) {
		/*	 @RequestParam("verificationCode") String verificationCode,
		String interRandomStr;
		interRandomStr = (String) session.getAttribute("randomString");
		if( ! verificationCode.toLowerCase().equals(interRandomStr.toLowerCase())){
			mav.addObject("loginMessage", "验证码不正确！");
			mav.setViewName("login");
			return mav;
		}*/
		boolean canLogin = userService.isRightUser(uEmail, uPassword);
		String rs ;
		if(canLogin) {
			User currentUser = userService.getUserByuEmail(uEmail);
			session.setAttribute("currentUser",currentUser);
			rs = jsonService.toJsonString(currentUser, StatusCode.CODE_SUCCESS,StatusCode.REASON_SUCCESS);
		} else {
			rs = jsonService.toJsonString(null,StatusCode.CODE_FAILURE,StatusCode.REASON_FAILURE);
		}
		return rs;	//返回JSON字符串，包含登录的结果信息
	}

	@RequestMapping(value = "api/userLogout", method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
	//实现用户退出登录
	public @ResponseBody
	String userLogout(HttpSession session) {
		String rs;
		if (session.getAttribute("currentUser") != null) {
			session.removeAttribute("currentUser");
			rs = jsonService.toJsonString(null,StatusCode.CODE_SUCCESS,StatusCode.REASON_SUCCESS);
		} else {
			rs = jsonService.toJsonString(null,StatusCode.CODE_FAILURE,StatusCode.REASON_FAILURE);
		}
		return rs;
	}

	@RequestMapping(value = "api/user", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	//实现用户注册功能
	public @ResponseBody
	String userRegister(User user, HttpSession session) {
		User newUser = userService.registUser(user);
		String rs;
		if (newUser != null) {
			session.setAttribute("currentUser", newUser);
			rs = jsonService.toJsonString(newUser,StatusCode.CODE_SUCCESS,StatusCode.REASON_SUCCESS);
		} else {
			rs = jsonService.toJsonString(null,StatusCode.CODE_FAILURE,StatusCode.REASON_FAILURE);
		}
		return rs;
	}

	@RequestMapping(value = "api/isLogin", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	//是否登录
	public @ResponseBody
	String isLogin(HttpSession session) {
		//获取目标用户的信息
		User user = (User) session.getAttribute("currentUser");
		String rs;
		if (user != null) {
			rs = jsonService.toJsonString(user,StatusCode.CODE_SUCCESS,StatusCode.REASON_SUCCESS);
		} else {
			rs = jsonService.toJsonString(null,StatusCode.CODE_FAILURE,StatusCode.REASON_FAILURE);
		}
		return rs;
	}


	@RequestMapping(value = "api/userSetting", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	//实现用户信息的更新，预计实现用户头像照片相关,带扩展
	public @ResponseBody String updateUserSetting(User user, HttpSession session) {
		User currentUser = (User) session.getAttribute("currentUser");
		String rs;
		if (currentUser != null && user.getuId() == currentUser.getuId()) {
			//上传照片
			//
			User afterUpadteUser = userService.updateUserInfo(user);
			if (afterUpadteUser != null) {
				session.setAttribute("currentUser", afterUpadteUser);
				rs = jsonService.toJsonString(afterUpadteUser,StatusCode.CODE_SUCCESS,StatusCode.REASON_SUCCESS);
				return rs;
			}
		}
		rs = jsonService.toJsonString(null,StatusCode.CODE_FAILURE,StatusCode.REASON_FAILURE);
		return rs;
	}

	@RequestMapping(value = "api/userPwd",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	//实现用户密码修改
	public @ResponseBody String updateUserSecurity(User user, @RequestParam("uNewPassword1") String uNewPassword1, HttpSession
			session) {
		User currentUser = (User) session.getAttribute("currentUser");
		String rs;
		if( currentUser != null && user != null && user.getuId() == currentUser.getuId()
				&& user.getuEmail().equals(currentUser.getuEmail())) {
			//校验是否为当前用户
			boolean canRightLogin = userService.isRightUser(currentUser.getuEmail(),user.getuPassword());
			if(canRightLogin) {
				user.setuPassword(uNewPassword1);
				int isSuccess = userService.updateUserPassword(user);
				if(isSuccess == 1) {
					rs = jsonService.toJsonString(null,StatusCode.CODE_SUCCESS,StatusCode.REASON_SUCCESS);
					return rs;
				}
			}
		}
		rs = jsonService.toJsonString(null,StatusCode.CODE_FAILURE,StatusCode.REASON_FAILURE);
		return rs;
	}

	@RequestMapping(value = "UserRelation/{pointUserId}",method = RequestMethod.POST)
	//用户关注功能，前端使用Ajax发出Post请求，进入此方法。方法最后返回一个关系值给前台。
	public @ResponseBody
	byte setUserRelation(@PathVariable("pointUserId")String pointUserId, HttpSession session) {
		User currentUser = (User) session.getAttribute("currentUser");
		int toUserId = Integer.parseInt(pointUserId);
		if(currentUser == null) {
			return UserRelation.RELATION_UNLOAD;	//-5
		} else if(currentUser.getuId()==toUserId) {
			return UserRelation.RELATION_ISSELF;	//-1
		} else {
			int fromUserId = currentUser.getuId();
			byte userRelationType = userRelationService.getRelationType(fromUserId,toUserId);
			boolean flag;
			if(userRelationType == UserRelation.RELATION_NONE) {	//-10
				flag = userRelationService.follow(fromUserId, toUserId);
				if(flag) return UserRelation.RELATION_FOLLOW;
				else return UserRelation.RELATION_NONE;
			} else if(userRelationType == UserRelation.RELATION_FOLLOW) {	//10
				flag = userRelationService.changeRelation(fromUserId, toUserId,UserRelation.RELATION_UNFOLLOW);
				if(flag) return UserRelation.RELATION_UNFOLLOW;
				else return UserRelation.RELATION_FOLLOW;
			} else if(userRelationType == UserRelation.RELATION_UNFOLLOW) {	//00
				flag = userRelationService.changeRelation(fromUserId, toUserId,UserRelation.RELATION_FOLLOW);
				if(flag) return UserRelation.RELATION_FOLLOW;
				else return UserRelation.RELATION_UNFOLLOW;
			}
			return UserRelation.RELATION_NONE;
		}
	}

	@RequestMapping(value = "UserRelation/{pointUserId}" ,method = RequestMethod.GET)
	//@Pathvariable传的是路径上的值，@RequestParam传的是data数据里的值
	//打开个人信息页面后，Ajax自动发出GET请求，进入此方法。方法最后返回该用户的关注数、被关注数，以及和当前用户的关系值给前台。
	public @ResponseBody
	Map<String ,Integer> getUserRelation(@PathVariable("pointUserId")String pointUserId, HttpSession session) {
		User currentUser = (User) session.getAttribute("currentUser");
		int toUserId = Integer.parseInt(pointUserId);
		int following = userRelationService.getFollowing(toUserId,UserRelation.RELATION_FOLLOW);
		int followed = userRelationService.getFollowed(toUserId,UserRelation.RELATION_FOLLOW);
		Map<String ,Integer> map = new HashMap<>();
		if(currentUser == null) {
			map.put("userRelationType",(int)UserRelation.RELATION_NONE);
			map.put("following",following);
			map.put("followed",followed);
			return map;
		} else {
			int fromUserId = currentUser.getuId();
			if(fromUserId == toUserId) {
				map.put("userRelationType",(int)UserRelation.RELATION_ISSELF);
				map.put("following",following);
				map.put("followed",followed);
				return map;
			}
			byte userRelationType = userRelationService.getRelationType(fromUserId, toUserId);
			map.put("userRelationType",(int)userRelationType);
			map.put("following",following);
			map.put("followed",followed);
			return map;
		}
	}

	@RequestMapping(value = "api/user/{pointUserId}/Following" ,method = RequestMethod.GET, produces =
			"application/json;charset=UTF-8")
	public @ResponseBody String getUserFollowing(@PathVariable("pointUserId")int pointUserId) {
		List<User> users = userRelationService.getFollowingUsers(pointUserId , UserRelation.RELATION_FOLLOW);
		String rs;
		if(users == null) {
			rs = jsonService.toJsonString(users,StatusCode.CODE_FAILURE,StatusCode.REASON_FAILURE);
		} else {
			rs = jsonService.toJsonString(users,StatusCode.CODE_SUCCESS,StatusCode.REASON_SUCCESS);
		}
		return rs;
	}

	@RequestMapping(value = "api/user/{pointUserId}/Followed" ,method = RequestMethod.GET, produces =
			"application/json;charset=UTF-8")
	public @ResponseBody String getUserFollowed(@PathVariable("pointUserId")int pointUserId) {
		List<User> users = userRelationService.getFollowedUsers(pointUserId , UserRelation.RELATION_FOLLOW);
		String rs;
		if(users == null) {
			rs = jsonService.toJsonString(users,StatusCode.CODE_FAILURE,StatusCode.REASON_FAILURE);
		} else {
			rs = jsonService.toJsonString(users,StatusCode.CODE_SUCCESS,StatusCode.REASON_SUCCESS);
		}
		return rs;
	}

	@RequestMapping("userOpt/{uId}")
	//显示用户动态
	public @ResponseBody
	Map<String, ? > showUserOpt(@PathVariable("uId") int uId) {
		Map<String, List<Question>> map = new LinkedHashMap<>();
		List<Question> pointUsersQuestion = questionService.getQuestionsByUserId(uId);
		map.put("pointUsersQuestion",pointUsersQuestion);
		return map;
	}



}
