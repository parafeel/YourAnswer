package ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import ssm.pojo.Topic;
import ssm.pojo.utilPojo.TopicRelation;

public interface TopicMapper {
	int addTopic(Topic topic);
	
	int deleteTopicById(@Param("tId") Integer tId);
	
	int updateTopicById(Topic topic);

	Topic queryTopicById(@Param("tId") Integer tId);
	
	List<Topic> queryTopic(String keywords);

	List<Topic> listTopic();

	List<Topic> queryTopicsTid(String[] qTopics);

	int addToTopicRelation(List<TopicRelation> topicRelations);
//话题关注模块
	int queryUserTopicRelationType(@Param("uId")int uId,@Param("tId") int tId);

	int follow(@Param("uId") int uId, @Param("tId") int tId, @Param("relationType") Byte relationType);

	int updateRelation(@Param("uId") int uId,@Param("tId") int tId,@Param("relationType") byte relationType);

	List<Topic> queryRelatedTopic(@Param("uId") int uId,@Param("relationType") byte relationType);
}
