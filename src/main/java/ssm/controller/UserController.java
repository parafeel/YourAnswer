package ssm.controller;


import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;

import com.alibaba.fastjson.JSON;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ssm.pojo.*;
import ssm.pojo.utilPojo.CropAvatar;
import ssm.service.*;
import ssm.util.CaptchaUtil;
import ssm.util.OperateImage;
import ssm.util.StatusCode;
import ssm.util.UserRelation;

@Controller
@RequestMapping("")
public class UserController {

	private UserService userService;
	private QuestionService questionService;
	private AnswerService answerService;
	private EssayService essayService;
	private TopicService topicService;
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
	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
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
		mav.setViewName("login");
		return mav;
	}

	//增加登录验证码功能
	@RequestMapping(value = "/captcha", method = RequestMethod.GET)
	public @ResponseBody void captcha(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
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

	//获取用户关注的话题的API
	@RequestMapping(value = "api/User/{uId}/FollowingTopics", method = RequestMethod.GET, produces =
			"application/json;" + "charset=UTF-8")
	public @ResponseBody String getTopicByuId(@PathVariable("uId")  int uId) {
		List<Topic> topics = topicService.getRelatedTopic(uId,UserRelation.RELATION_FOLLOW);
		String rs;
		if(topics == null) {
			rs = jsonService.toJsonString(null,StatusCode.CODE_FAILURE,StatusCode.REASON_FAILURE);
		} else {
			rs = jsonService.toJsonString(topics,StatusCode.CODE_SUCCESS, StatusCode.REASON_SUCCESS);
		}
		return rs;
	}
	//获取当前用户关注的话题
	@RequestMapping(value = "api/User/FollowingTopics", method = RequestMethod.GET, produces =
			"application/json;" + "charset=UTF-8")
	public @ResponseBody String getTopicBySession(HttpSession session) {
		User currentUser = (User)session.getAttribute("currentUser");
		String rs;
		if(currentUser == null) {
			rs = jsonService.toJsonString(null,StatusCode.CODE_FAILURE,StatusCode.REASON_FAILURE);
		} else {
			int uId = currentUser.getuId();
			List<Topic> topics = topicService.getRelatedTopic(uId, UserRelation.RELATION_FOLLOW);
			rs = jsonService.toJsonString(topics, StatusCode.CODE_SUCCESS, StatusCode.REASON_SUCCESS);
		}
		return rs;
	}


//上传图片API
	@RequestMapping("/changePhoto")
	public @ResponseBody Map<String,String> uploadAvatar(@RequestParam("avatarInput") MultipartFile filePhoto,
														 @RequestParam("avatar_data") String avatarData, HttpSession session) {
		Map<String,String> map = new HashMap<>(2);
		User user = (User) session.getAttribute("currentUser");
		if( user == null || filePhoto == null) {
			map.put("flag","FAILED");
			return map;
		}
		//获取相对地址,在tomcat中指定/YourAnswer虚拟目录对应的为docBase="D:\Data\Java\IDEA\YourAnswer\target\YourAnswer
		//对应的JSP目录为${pageContext.request.contextPath}/imgs/userPho/。只有Tomcat虚拟目录，此处目录，JSP目录，三处相同，
		// 才能实现图片的上传显示完美。
		String filePath=session.getServletContext().getRealPath("/imgs/userPho/");
		//获取图片原始名称、及图片扩展名
		String originalFilename=filePhoto.getOriginalFilename();
		String types=originalFilename.substring(originalFilename.lastIndexOf(".")+1).toLowerCase();
		if(!types.equals("jpg")) {
			map.put("flag","FAILED");
			return map;
		}
		try {
			//以用户id加图片扩展名给图片命名，O原生、B大图、M中图、S小图
			String newFileName=user.getuId()+"_O"+originalFilename.substring(originalFilename.lastIndexOf("."));
			String endFileName = filePath+newFileName;
			File file=new File(endFileName);
			if(!file.exists()) {
				file.createNewFile();
			}
			//上传
			filePhoto.transferTo(file);
			//裁剪得到B,M,S头像，先获得裁剪数据，以及大中小图名称
			CropAvatar cropAvatar = JSON.parseObject(avatarData,CropAvatar.class);
			String bigAvatar=user.getuId()+"_B"+originalFilename.substring(originalFilename.lastIndexOf("."));
			String midAvatar=user.getuId()+"_M"+originalFilename.substring(originalFilename.lastIndexOf("."));
			String smAvatar=user.getuId()+"_S"+originalFilename.substring(originalFilename.lastIndexOf("."));
			OperateImage.cropImage(endFileName,filePath+bigAvatar,cropAvatar.getX(),cropAvatar.getY(),cropAvatar.getWidth(),
					cropAvatar.getHeight(),"jpg","jpg");
			//以80*80大小改变图片，此处使用thumbnailator-0.4.2.jar改变图片大小
			File file1=new File(filePath+midAvatar);
			File file2=new File(filePath+smAvatar);
			Thumbnails.of(filePath+bigAvatar).size(100, 100).keepAspectRatio(false).toFile(file1);
			Thumbnails.of(filePath+bigAvatar).size(50, 50).keepAspectRatio(false).toFile(file2);
			userService.updatePhoto(user,"userPho/" + bigAvatar,"userPho/" + midAvatar,"userPho/" + smAvatar);

			session.setAttribute("photo","photo");
			map.put("result",session.getServletContext().getContextPath()+"/imgs/userPho/"+newFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}


}
