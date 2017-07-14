package ssm.service;


import ssm.pojo.Topic;

import java.util.List;

public interface TopicService {
	
	boolean putTopic(Topic topic);
	
	void deleteTopicById(int tId);
	
	void updateTopicById(Topic topic);
	
	List<Topic> getTopic();
	
}
