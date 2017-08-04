package ssm.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import ssm.util.UserRelation;

@Controller
@RequestMapping("")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private AnswerService answerService;
	@Autowired
	private EssayService essayService;
	@Autowired
	private UserRelationService userRelationService;


	@RequestMapping("login")
	//跳转进入登录页面
	public String  tryLogin() {
		return "login";
	}

	@RequestMapping("userLogin")
	//实现用户登录,用currentUser存储登录成功的用户信息，用loginMessage存储登录信息 VerificationCode
	public @ResponseBody boolean userLogin(@RequestParam("uEmail") String uEmail, @RequestParam
			("uPassword") String
			uPassword, @RequestParam("verificationCode") String verificationCode,HttpSession session) {
		/*String interRandomStr;
		interRandomStr = (String) session.getAttribute("randomString");
		if( ! verificationCode.toLowerCase().equals(interRandomStr.toLowerCase())){
			mav.addObject("loginMessage", "验证码不正确！");
			mav.setViewName("login");
			return mav;
		}*/
		User user = userService.isRightUser(uEmail,uPassword);
		boolean isLoginSuccess;
		if(null != user) {
			session.setAttribute("currentUser", user);
			session.setAttribute("isLogin", true);
			isLoginSuccess = true;
		} else {
			isLoginSuccess = false;
		}
		return isLoginSuccess;
	}

	//增加登录验证码功能
	@RequestMapping(value = "/captcha", method = RequestMethod.GET)
	@ResponseBody
	public void captcha(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		CaptchaUtil.outputCaptcha(request, response);
		String randomString = (String)request.getSession().getAttribute("randomString");
		System.out.println("randomString : " + randomString);
	}
	
	@RequestMapping("userLogout")
	//实现用户退出登录
	public ModelAndView userLogout(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if(session.getAttribute("currentUser") != null) {
			session.removeAttribute("currentUser");
			session.setAttribute("isLogin", false);
		}
		mav.setViewName("redirect:/");
		return mav;
	}

	@RequestMapping("userRegister")
	//实现用户注册功能
	public @ResponseBody boolean userRegister(User user, HttpSession session) {
		User newUser = userService.registUser(user);
		boolean isRegisterSuccess;
		if( newUser != null ) {
			session.setAttribute("isLogin", true);
			session.setAttribute("currentUser", newUser);
			isRegisterSuccess = true;
		} else {
			isRegisterSuccess = false;
		}
		return isRegisterSuccess;
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
			mav.setViewName("showUser");
			return mav;
		}
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

	@RequestMapping("userOpt/{uId}")
	//显示用户动态
	public @ResponseBody
	Map<String, ? > showUserOpt(@PathVariable("uId") int uId) {
		Map<String, List<Question>> map = new LinkedHashMap<>();
		List<Question> pointUsersQuestion = questionService.getQuestionsByUserId(uId);
		List<Answer> pointUsersAnswer = answerService.getAnswersByUserId(uId);
		List<Essay> pointUserEssay = essayService.getEssayByUserId(uId);
		map.put("pointUsersQuestion",pointUsersQuestion);
		return map;
	}



}
