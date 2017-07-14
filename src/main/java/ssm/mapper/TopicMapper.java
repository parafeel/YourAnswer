package ssm.mapper;

import java.util.List;

import ssm.pojo.Topic;

public interface TopicMapper {
	
	void addTopic(Topic topic);
	
	void deleteTopicById(Integer tId);
	
	int updateTopicById(Topic topic);
	
	List<Topic> queryTopic();
	
}
