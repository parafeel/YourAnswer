package ssm.service.Impl;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ssm.mapper.TopicMapper;
import ssm.pojo.Topic;
import ssm.pojo.utilPojo.TopicRelation;
import ssm.service.TopicService;
import ssm.util.UserRelation;

@Service
public class TopicServiceImpl implements TopicService {

	private TopicMapper topicMapper;

	@Autowired
	public void setTopicMapper(TopicMapper topicMapper) {
		this.topicMapper = topicMapper;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackForClassName="Exception")
	public Topic putTopic(Topic topic) {
		// TODO Auto-generated method stub
		Date insertTime= new Date(new java.util.Date().getTime());
		topic.settMadeDate(insertTime);
		int flag = topicMapper.addTopic(topic);
		if(flag == 1) {
			return topic;
		} else {
			return null;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackForClassName="Exception")
	public boolean deleteTopicById(int tId) {
		// TODO Auto-generated method stub
		int flag = topicMapper.deleteTopicById(tId);
		if(flag == 1) {
			return true;
		} else {
			return  false;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackForClassName="Exception")
	public boolean updateTopicById(Topic topic) {
		// TODO Auto-generated method stub
		Date insertTime= new Date(new java.util.Date().getTime());
		topic.settMadeDate(insertTime);
		int flag = topicMapper.updateTopicById(topic);
		if(flag == 1) {
			return true;
		} else {
			return  false;
		}
	}

	@Override
	public Topic getById(int tId) {
		return topicMapper.queryTopicById(tId);
	}

	@Override
	public List<Topic> getTopic(String keyWords) {
		return topicMapper.queryTopic(keyWords);
	}

	@Override
	public List<Topic> listTopic() {
		return topicMapper.listTopic();
	}

	@Override
	public List<Topic> getTopicsTid(String[] qTopics) {
		return topicMapper.queryTopicsTid(qTopics);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackForClassName="Exception")
	public boolean putToTopicRelation(List<Topic>  topics, byte trType, int trTypeId, Date trCreatetime) {
		List<TopicRelation> topicRelations = new ArrayList<>(topics.size());
		for (int i = 0; i < topics.size(); i++) {
			topicRelations.add(new TopicRelation(topics.get(i).gettId(), trType,trTypeId,trCreatetime));
		}
		int flag = topicMapper.addToTopicRelation(topicRelations);
		if(flag == topicRelations.size()) {
			return true;
		} else {
			return false;
		}
	}

//	话题的关注模块
	@Override
	public byte getUserTopicRelationType(int uId, int tId) {
		return (byte) topicMapper.queryUserTopicRelationType(uId,tId);
	}

	@Override
	public boolean follow(int uId, int tId) {
		int flag = topicMapper.follow(uId,tId, UserRelation.RELATION_FOLLOW);
		if(flag == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean changeRelation(int uId, int tId, byte relationType) {
		int flag = topicMapper.updateRelation(uId,tId, relationType);
		if(flag == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Topic> getRelatedTopic(int uId, byte relationType) {
		return topicMapper.queryRelatedTopic(uId,relationType);
	}
}
