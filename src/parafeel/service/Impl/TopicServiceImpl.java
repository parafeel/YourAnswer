package parafeel.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import parafeel.mapper.TopicMapper;
import parafeel.pojo.Topic;
import parafeel.service.TopicService;

@Service
public class TopicServiceImpl implements TopicService {
	
	@Autowired
	TopicMapper topicMapper;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackForClassName="Exception")
	public boolean putTopic(Topic topic) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteTopicById(int tId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTopicById(Topic topic) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Topic> getTopic() {
		// TODO Auto-generated method stub
		List<Topic> topics = topicMapper.queryTopic();
		return topics;
	}

}
