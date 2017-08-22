package ssm.mapper;

import java.util.List;

import ssm.pojo.Topic;

public interface TopicMapper {
	
	int addTopic(Topic topic);
	
	int deleteTopicById(Integer tId);
	
	int updateTopicById(Topic topic);

	Topic queryTopicById(Integer tId);
	
	List<Topic> queryTopic();
	
}
