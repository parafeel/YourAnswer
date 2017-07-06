package parafeel.mapper;

import java.util.List;

import parafeel.pojo.Topic;

public interface TopicMapper {
	
	public void addTopic(Topic topic);
	
	public void deleteTopicById(Integer tId);
	
	public int updateTopicById(Topic topic);
	
	public List<Topic> queryTopic();
	
}
