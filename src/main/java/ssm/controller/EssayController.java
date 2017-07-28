package ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ssm.pojo.Answer;
import ssm.pojo.Essay;
import ssm.pojo.Question;
import ssm.pojo.User;
import ssm.service.EssayService;
import ssm.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("")
public class EssayController {

	private EssayService essayService;
	private UserService userService;

	@Autowired
	public void setEssayService(EssayService essayService) {
		this.essayService = essayService;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	//此处使用rest风格的URL
	@RequestMapping("makeEssay")
	public ModelAndView makeEssay() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("addEssay");
		return mav;
	}
	
	@RequestMapping("addEssay")
	public ModelAndView addEssay(Essay essay, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String addEssayMessage;
		boolean flag;
		User currentUser = (User)session.getAttribute("currentUser");
		if(currentUser == null) {
			addEssayMessage = "您还未登陆，登陆后才可回答！";
			mav.addObject("addEssayMessage", addEssayMessage);
			mav.addObject("essay",essay);
			mav.setViewName("addEssay");
			return mav;
		} else {
			if(essayService.getEssayByEssayTitle(essay.getEssayTitle()) != null) {
				addEssayMessage = "随笔标题重复！";
				mav.addObject("addEssayMessage", addEssayMessage);
				mav.addObject("essay",essay);
				mav.setViewName("addEssay");
			} else {
				essay.setEssayMadeByUserId(currentUser.getuId());
				flag = essayService.putEssay(essay);
				//添加回答成功
				if(flag) {
					Essay currentEssay = essayService.getEssayByEssayTitle(essay.getEssayTitle());
					mav.addObject("essayId",currentEssay.getEssayId());
					mav.setViewName("redirect:/Essay/{essayId}");
				} else {
					addEssayMessage = "似乎出现了什么问题！";
					mav.addObject("addEssayMessage", addEssayMessage);
					mav.setViewName("addEssay");
				}
			}
		}
		return mav;
	}
	
	@RequestMapping("Essay/{essayId}")
	public ModelAndView showEssay(@PathVariable("essayId") int essayId) {
		ModelAndView mav = new ModelAndView();
		Essay currentEssay = essayService.getEssayByEssayId(essayId);
		if(currentEssay != null) {
			currentEssay.setEssayMadeByUser(userService.getUserById(currentEssay.getEssayMadeByUserId()));
			mav.addObject("currentEssay",currentEssay);
			mav.setViewName("showEssay");
		} else {
			mav.addObject("wrongInfoMessage","你似乎进入未知页面...");
			mav.setViewName("wrongInfo");
		}
		return mav;
	}


	@RequestMapping("Essay/{essayId}/update")
	public @ResponseBody Essay updateEssay(@PathVariable("essayId") int essayId) {
		return essayService.getEssayByEssayId(essayId);
	}

	@RequestMapping("listEssay")
	public ModelAndView listEssay() {
		ModelAndView mav = new ModelAndView();
		List<Essay> essays = essayService.getEssayByTime();
		mav.addObject("essays",essays);
		mav.setViewName("listEssay");
		return  mav;
	}
}
