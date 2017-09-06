package ssm.service;


import ssm.pojo.Topic;
import ssm.pojo.utilPojo.TopicRelation;

import java.util.Date;
import java.util.List;

public interface TopicService {

	Topic putTopic(Topic topic);

	boolean deleteTopicById(int tId);

	boolean updateTopicById(Topic topic);

	Topic getById(int tId);

	List<Topic> getTopic(String keyWords);

	List<Topic> listTopic();

	List<Topic> getTopicsTid(String[] qTopics);

	boolean putToTopicRelation(List<Topic>  topics,byte trType,int trTypeId,Date trCreatetime);

//	话题的关注模块
	byte getUserTopicRelationType(int uId, int tId);

	boolean follow(int uId, int tId);

	boolean changeRelation(int uId, int tId, byte relationType);

	List<Topic> getRelatedTopic(int uId , byte relationType);

}
