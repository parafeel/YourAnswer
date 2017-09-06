package ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ssm.pojo.*;
import ssm.service.EssayService;
import ssm.service.JsonService;
import ssm.service.OperationService;
import ssm.service.UserService;
import ssm.util.StatusCode;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("")
public class EssayController {

	private EssayService essayService;

	private UserService userService;

	private OperationService operationService;

	private JsonService jsonService;

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
	@Autowired
	public void setJsonService(JsonService jsonService) {
		this.jsonService = jsonService;
	}


//此处使用rest风格的URL
	//新建essay
	@RequestMapping("makeEssay")
	public ModelAndView makeEssay(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User currentUser = (User)session.getAttribute("currentUser");
		if(currentUser == null) {
			mav.addObject("loginMessage","登录后才能发表随笔！");
			mav.setViewName("login");
			return mav;
		}
		mav.setViewName("Essay/addEssay");
		return mav;
	}

	//查看essay
	@RequestMapping("Essay/{essayId}")
	public ModelAndView showEssay(@PathVariable("essayId") int essayId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("essayId",essayId);
		mav.setViewName("Essay/showEssay");
		return mav;
	}

	//修改essay
	@RequestMapping("Essay/{essayId}/update")
	public ModelAndView tryUpdateEssay(@PathVariable("essayId") int essayId, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User currentUser = (User)session.getAttribute("currentUser");
		Essay currentEssay = essayService.getEssayByEssayId(essayId);
		if(currentUser == null || currentEssay == null || currentUser.getuId() != currentEssay.getEssayMadeByUserId()) {
			mav.setViewName("redirect:/");
		} else {
			mav.addObject("currentEssay", currentEssay);
			mav.setViewName("Essay/updateEssay");
		}
		return mav;
	}

	//列表查看essay
	@RequestMapping("listEssay")
	public String listEssays() {
		return "Essay/listEssay";
	}

//相关API
	//增加随笔API
	@RequestMapping(value = "api/Essay",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String addEssay(Essay essay , HttpSession session) {
		User currentUser = (User)session.getAttribute("currentUser");
		String rs;
		if(currentUser == null) {
			rs = jsonService.toJsonString(null,StatusCode.CODE_FAILURE,StatusCode.REASON_FAILURE);
		} else {
			if(essayService.getEssayByEssayTitle(essay.getEssayTitle()) != null) {
				rs = jsonService.toJsonString(null,StatusCode.CODE_DUPLICATE,StatusCode.REASON_FAILURE);
			} else {
				Essay currentEssay = essayService.putEssay(essay, currentUser);
				//添加回答成功
				if (currentEssay != null) {
					Operation operation = new Operation(currentEssay.getEssayMadeByUserId(), StatusCode.TYPE_ESSAY,
							currentEssay.getEssayId() , StatusCode.PUBLISH_ESSAY);
					operationService.putOperation(operation);
					rs = jsonService.toJsonString(currentEssay, StatusCode.CODE_SUCCESS, StatusCode.REASON_SUCCESS);
				} else {
					rs = jsonService.toJsonString(null,StatusCode.CODE_FAILURE,StatusCode.REASON_FAILURE);
				}
			}
		}
		return rs;
	}

	//删除随笔API
	@RequestMapping(value = "api/Essay/{essayId}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
	public @ResponseBody String deleteEssay(@PathVariable("essayId") int essayId, HttpSession session) {
		User currentUser = (User)session.getAttribute("currentUser");
		Essay currentEssay = essayService.getEssayByEssayId(essayId);
		String rs;
		if(currentUser != null || currentEssay != null || currentEssay.getEssayMadeByUserId() == currentUser.getuId()) {
			rs = jsonService.toJsonString(currentEssay,StatusCode.CODE_SUCCESS,StatusCode.REASON_SUCCESS);
		} else {
			rs = jsonService.toJsonString(null,StatusCode.CODE_FAILURE,StatusCode.REASON_FAILURE);
		}
		return rs;
	}

	//更新随笔API
	@RequestMapping(value = "api/Essay/{essayId}",method = RequestMethod.PUT,produces = "application/json;" + "charset=UTF-8" )
	public @ResponseBody String updateEssay(@PathVariable("essayId") int essayId, Essay essay, HttpSession session) {
		User currentUser = (User)session.getAttribute("currentUser");
		Essay currentEssay = essayService.getEssayByEssayId(essayId);
		String rs;
		if(currentUser == null || currentEssay == null || essay.getEssayId() != essayId ||
				currentEssay.getEssayMadeByUserId() != currentUser.getuId()) {
			rs = jsonService.toJsonString(null,StatusCode.CODE_FAILURE,StatusCode.REASON_FAILURE);
		} else {
			boolean isUpdate = essayService.updateEssay(essay);
			if(isUpdate) {
				rs = jsonService.toJsonString(essayService.getEssayByEssayId(essayId),StatusCode.CODE_SUCCESS,
						StatusCode.REASON_SUCCESS);
			} else {
				rs = jsonService.toJsonString(null,StatusCode.CODE_FAILURE,StatusCode.REASON_FAILURE);
			}
		}
		return rs;
	}

	//获取随笔API
	@RequestMapping(value = "api/Essay/{essayId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody String getEssay(@PathVariable("essayId") int essayId) {
		String rs;
		Essay currentEssay = essayService.getEssayByEssayId(essayId);
		if(currentEssay == null) {
			rs = jsonService.toJsonString(null,StatusCode.CODE_FAILURE,StatusCode.REASON_FAILURE);;
		} else {
			rs = jsonService.toJsonString(currentEssay,StatusCode.CODE_SUCCESS,StatusCode.REASON_SUCCESS);
		}
		return rs;
	}

	//列表查看essays
	@RequestMapping(value = "api/Essays",method = RequestMethod.GET, produces = "application/json;charset=UTF-8" )
	public @ResponseBody String Essays() {
		List<Essay> essays = essayService.getEssayByTime();
		String rs = jsonService.toJsonString(essays,StatusCode.CODE_SUCCESS,StatusCode.REASON_SUCCESS);
		return  rs;
	}


	//外置获取API
	@RequestMapping(value = "api/onlyEssay/{essayId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody String getOnlyEssay(@PathVariable("essayId") int essayId) {
		String rs;
		Essay currentEssay = essayService.getEssayByEssayId(essayId);
		if(currentEssay == null) {
			rs = jsonService.toJsonString(null,StatusCode.CODE_FAILURE,StatusCode.REASON_FAILURE);;
		} else {
			rs = jsonService.toJsonString(currentEssay,StatusCode.CODE_SUCCESS,StatusCode.REASON_SUCCESS);
		}
		return rs;
	}


}
