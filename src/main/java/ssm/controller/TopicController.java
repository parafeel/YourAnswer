package ssm.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import ssm.pojo.Essay;
import ssm.pojo.Topic;
import ssm.pojo.User;
import ssm.service.JsonService;
import ssm.service.TopicService;
import ssm.util.StatusCode;
import ssm.util.UserRelation;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class TopicController {
	
	private TopicService topicService;
	private JsonService jsonService;

	@Autowired
	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}
	@Autowired
	public void setJsonService(JsonService jsonService) {
		this.jsonService = jsonService;
	}

	@RequestMapping("/topics")
	public ModelAndView showTopic(HttpSession session) {
		User currentUser = (User) session.getAttribute("currentUser");
		ModelAndView mav = new ModelAndView();
		if(currentUser != null) {
			mav.setViewName("AboutTopic/topics");
		} else {
			mav.setViewName("redirect:/topicCenter");
		}
	    return mav;
	}
	@RequestMapping("/topicCenter")
	public ModelAndView goToTopicenter() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("AboutTopic/topicCenter");
		return mav;
	}

	@RequestMapping("/topic/{tId}")
	public ModelAndView showTopicRealtionThing(@PathVariable("tId") int tId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("tId", tId);
		mav.setViewName("AboutTopic/showTopic");
		return mav;
	}


//相关API
	//查询topicAPI,用于为问题随笔等添加话题标签
	@RequestMapping(value = "api/Topic", method = RequestMethod.GET, produces = "application/json;" +
			"charset=UTF-8")
	public @ResponseBody List<String> getTopicByKeyWords(@RequestParam("keywords")  String keywords) {
 		if("".equals(keywords)) {
			return null;
		}
		List<Topic> topics = topicService.getTopic(keywords);
		List<String> list = new LinkedList<>();
		for (int i = 0; i < topics.size(); i++) {
			list.add(topics.get(i).gettName());
		}
		return list;
	}

	//获取全部topic的API
	@RequestMapping(value = "api/topicCenter", method = RequestMethod.GET, produces = "application/json;" +
			"charset=UTF-8")
	public @ResponseBody String listTopics() {
		List<Topic> topics = topicService.listTopic();
		String rs;
		if(topics == null) {
			rs = jsonService.toJsonString(null,StatusCode.CODE_FAILURE,StatusCode.REASON_FAILURE);
		} else {
			rs = jsonService.toJsonString(topics,StatusCode.CODE_SUCCESS,
					StatusCode.REASON_SUCCESS);
		}
		return rs;
	}


	//根据tId获取某topic信息的API
	@RequestMapping(value = "api/Topic/{tId}", method = RequestMethod.GET, produces = "application/json;" +
			"charset=UTF-8")
	public @ResponseBody String getTopicById(@PathVariable("tId")  int tId) {
		Topic topic = topicService.getById(tId);
		String rs;
		if(topic == null) {
			rs = jsonService.toJsonString(null,StatusCode.CODE_FAILURE,StatusCode.REASON_FAILURE);
		} else {
			rs = jsonService.toJsonString(topic,StatusCode.CODE_SUCCESS,
					StatusCode.REASON_SUCCESS);
		}
		return rs;
	}

	//用户关注话题功能，前端使用Ajax发出Post请求，进入此方法。方法最后返回一个关系值给前台。
	@RequestMapping(value = "UserTopicRelation/{tId}",method = RequestMethod.POST, produces = "application/json;" +
			"charset=UTF-8")
	public @ResponseBody String setUserTopicRelation(@PathVariable("tId")int tId, HttpSession session) {
		User currentUser = (User) session.getAttribute("currentUser");
		String rs;
		if(currentUser == null) {
			rs = jsonService.toJsonString(UserRelation.RELATION_UNLOAD,StatusCode.CODE_SUCCESS,StatusCode.REASON_SUCCESS);	//值-5
		} else {
			int uId = currentUser.getuId();
			byte userTopicRelationType = topicService.getUserTopicRelationType(uId,tId);
			boolean flag;
			if(userTopicRelationType == UserRelation.RELATION_NONE) {	//-10
				flag = topicService.follow(uId, tId);
				if(flag) {
					rs = jsonService.toJsonString(UserRelation.RELATION_FOLLOW,StatusCode.CODE_SUCCESS,StatusCode.REASON_SUCCESS);
				}
				else {
					rs = jsonService.toJsonString(UserRelation.RELATION_NONE,StatusCode.CODE_SUCCESS,StatusCode.REASON_SUCCESS);
				}
			} else if(userTopicRelationType == UserRelation.RELATION_FOLLOW) {	//10
				flag = topicService.changeRelation(uId, tId,UserRelation.RELATION_UNFOLLOW);
				if(flag) {
					rs = jsonService.toJsonString(UserRelation.RELATION_UNFOLLOW,StatusCode.CODE_SUCCESS,StatusCode.REASON_SUCCESS);
				}
				else {
					rs = jsonService.toJsonString(UserRelation.RELATION_FOLLOW,StatusCode.CODE_SUCCESS,StatusCode.REASON_SUCCESS);
				}
			} else if(userTopicRelationType == UserRelation.RELATION_UNFOLLOW) {	//00
				flag = topicService.changeRelation(uId, tId,UserRelation.RELATION_FOLLOW);
				if(flag) {
					rs = jsonService.toJsonString(UserRelation.RELATION_FOLLOW,StatusCode.CODE_SUCCESS,StatusCode.REASON_SUCCESS);
				}
				else {
					rs = jsonService.toJsonString(UserRelation.RELATION_UNFOLLOW,StatusCode.CODE_SUCCESS,StatusCode.REASON_SUCCESS);
				}
			} else {
				rs = jsonService.toJsonString(UserRelation.RELATION_NONE,StatusCode.CODE_SUCCESS,StatusCode.REASON_SUCCESS);
			}
		}
		return rs;
	}

	//打开topic信息页面后，Ajax自动发出GET请求，进入此方法。方法最后返回topic和当前用户的关系值给前台。
	@RequestMapping(value = "UserTopicRelation/{tId}" ,method = RequestMethod.GET, produces = "application/json;" +
			"charset=UTF-8")
	public @ResponseBody String getUserTopicRelation(@PathVariable("tId")int tId, HttpSession session) {
		User currentUser = (User) session.getAttribute("currentUser");
		String rs;
		if(currentUser == null) {
			rs = jsonService.toJsonString(UserRelation.RELATION_NONE,StatusCode.CODE_SUCCESS,StatusCode.REASON_SUCCESS);
		} else {
			int uId = currentUser.getuId();
			byte userRelationType = topicService.getUserTopicRelationType(uId, tId);
			rs = jsonService.toJsonString(userRelationType,StatusCode.CODE_SUCCESS,StatusCode.REASON_SUCCESS);
		}
		return rs;
	}

	//获取话题相关的问题
	@RequestMapping(value = "TopicRelatedQuestion/{tId}" ,method = RequestMethod.GET, produces = "application/json;" +
			"charset=UTF-8")
	public @ResponseBody String getTopicRelatedQuestion(@PathVariable("tId")int tId, HttpSession session) {
		String rs = "";

		return rs;
	}


}
