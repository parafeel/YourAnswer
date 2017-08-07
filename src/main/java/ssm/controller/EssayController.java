package ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ssm.pojo.*;
import ssm.service.EssayService;
import ssm.service.OperationService;
import ssm.service.UserService;
import ssm.util.UserOperation;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("")
public class EssayController {

	private EssayService essayService;

	private UserService userService;

	private OperationService operationService;

	@Autowired
	public void setEssayService(EssayService essayService) {
		this.essayService = essayService;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@Autowired
	public void setOperationService(OperationService operationService) {
		this.operationService = operationService;
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
		User currentUser = (User)session.getAttribute("currentUser");
		if(currentUser == null) {
			addEssayMessage = "您还未登陆，登陆后才可回答！";
			mav.addObject("addEssayMessage", addEssayMessage);
			mav.addObject("currentEssay",essay);
			mav.setViewName("addEssay");
			return mav;
		} else {
			if(essayService.getEssayByEssayTitle(essay.getEssayTitle()) != null) {
				addEssayMessage = "随笔标题重复！";
				mav.addObject("addEssayMessage", addEssayMessage);
				mav.addObject("currentEssay",essay);
				mav.setViewName("addEssay");
			} else {
				Essay currentEssay  = essayService.putEssay(essay,currentUser);
				//添加回答成功
				if(currentEssay != null) {
					operationService.putOperation(new Operation(currentEssay.getEssayMadeByUserId(), UserOperation.TYPE_ESSAY,
							currentEssay.getEssayId()) );
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

	@RequestMapping(value = "feedEssay/{essayId}", method = RequestMethod.GET)
	public @ResponseBody Essay getEssay(@PathVariable("essayId") int essayId) {
		Essay currentEssay = essayService.getEssayByEssayId(essayId);
		if(currentEssay == null) {
			return null;
		} else {
			return currentEssay;
		}
	}


	@RequestMapping("Essay/{essayId}/update")
	public ModelAndView tryUpdateEssay(@PathVariable("essayId") int essayId, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User currentUser = (User)session.getAttribute("currentUser");
		Essay currentEssay = essayService.getEssayByEssayId(essayId);
		if(currentUser == null || currentEssay == null || currentUser.getuId() != currentEssay.getEssayMadeByUserId()) {
			mav.setViewName("redirect:/");
		} else {
			mav.addObject("currentEssay", currentEssay);
			mav.setViewName("updateEssay");
		}
		return mav;
	}

	@RequestMapping("updateEssay/{essayId}")
	public ModelAndView updateEssay(Essay essay, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User currentUser = (User)session.getAttribute("currentUser");
		if(essay.getEssayMadeByUserId() != currentUser.getuId()) {
			mav.addObject("updateEssayMessage","随笔未修改成功！");
			mav.addObject("currentEssay", essay);
			mav.setViewName("updateEssay");
		} else {
			boolean isUpdate = essayService.updateEssay(essay);
			if(isUpdate) {
				mav.addObject("essayId", essay.getEssayId());
				mav.setViewName("redirect:/Essay/{essayId}");
			}
		}
		return mav;
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
