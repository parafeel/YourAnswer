package parafeel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import parafeel.pojo.Topic;
import parafeel.service.TopicService;

@Controller
@RequestMapping("")
public class TopicController {
	
	@Autowired
	TopicService topicService;
	
	@RequestMapping("/topicCenter")
	public ModelAndView showTopic() {
		ModelAndView mav = new ModelAndView();
		List<Topic> topics = topicService.getTopic();
		mav.addObject("topics", topics);
		mav.setViewName("topicCenter");
	    return mav;
	}
}
