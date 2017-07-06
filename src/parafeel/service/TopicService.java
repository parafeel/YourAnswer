package parafeel.service;

import java.util.List;

import parafeel.pojo.Topic;

public interface TopicService {
	
	public boolean putTopic(Topic topic);
	
	public void deleteTopicById(int tId);
	
	public void updateTopicById(Topic topic);
	
	public List<Topic> getTopic();
	
}
