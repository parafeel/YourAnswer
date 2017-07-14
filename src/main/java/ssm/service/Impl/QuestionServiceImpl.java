package ssm.service.Impl;


import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ssm.mapper.QuestionMapper;
import ssm.pojo.Question;
import ssm.pojo.Topic;
import ssm.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{

	QuestionMapper questionMapper;

	@Autowired
	public void setQuestionMapper(QuestionMapper questionMapper) {
		this.questionMapper = questionMapper;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackForClassName="Exception")
	public void putQuestion(Question question) {
		Question newQuestion = questionMapper.hasQTitle(question.getqTitle());
		// TODO Auto-generated method stub
		if(newQuestion != null) {
			return;
		}
		//因为实体question中的时间为util.Date，而数据库存时需要sql.Date格式，故转化
		//读取的时候则不必再转化，因为读出来的相当于sql.Date，是Util的子类，可以直接存入对象
		Date insertTime= new Date(new java.util.Date().getTime());
		question.setqMadeDate(insertTime);
		questionMapper.addQuestion(question);
	}
	
	@Override
	public Question getQuestion(String qTitle) {
		// TODO Auto-generated method stub
		Question currentQuestion = questionMapper.hasQTitle(qTitle);
		return currentQuestion;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackForClassName="Exception")
	public void deleteQuestionById(int qId) {
		// TODO Auto-generated method stub
		questionMapper.deleteQuestionById(qId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackForClassName="Exception")
	public void updateQuestionById( Question question) {
		// TODO Auto-generated method stub
		questionMapper.updateQuestionById(question);
	}
	
	@Override
	public Question getQuestionById(int qId) {
		// TODO Auto-generated method stub
		return questionMapper.queryQuestionById(qId);
	}

	@Override
	public List<Question> getQuestionByTime() {
		// TODO Auto-generated method stub
		return questionMapper.queryQuestionByTime();
	}

	@Override
	public List<Question> getQuestionsByUserId(int uId) {
		// TODO Auto-generated method stub
		return questionMapper.queryQuestionByUserId(uId);
	}

	@Override
	public List<Question> getQuestionsByTopic(Topic topic) {
		// TODO Auto-generated method stub
		return null;
	}



}
