package ssm.service.Impl;


import java.sql.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ssm.mapper.OperationMapper;
import ssm.mapper.QuestionMapper;
import ssm.pojo.Operation;
import ssm.pojo.Question;
import ssm.pojo.Topic;
import ssm.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{
	@Autowired
	QuestionMapper questionMapper;

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackForClassName="Exception")
	public Question putQuestion(Question question,int CurrentuId) {
		//因为实体question中的时间为util.Date，而数据库存时需要sql.Date格式，故转化
		//读取的时候则不必再转化，因为读出来的相当于sql.Date，是Util的子类，可以直接存入对象
		question.setqMadeByUserId(CurrentuId);
		Date insertTime= new Date(new java.util.Date().getTime());
		question.setqMadeDate(insertTime);
		//修改查询语句，即可将插入后的记录的ID传回！
		int flag = questionMapper.addQuestion(question);
		if(flag == 1) {
			return question;
		} else {
			return  null;
		}
	}
	
	@Override
	public Question getQuestion(String qTitle) {
		// TODO Auto-generated method stub
		Question currentQuestion = questionMapper.hasQTitle(qTitle);
		return currentQuestion;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackForClassName="Exception")
	public boolean deleteQuestionById(int qId) {
		// TODO Auto-generated method stub
		int flag = questionMapper.deleteQuestionById(qId);
		if(flag == 1) {
			return true;
		} else {
			return  false;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackForClassName="Exception")
	public boolean updateQuestionById( Question question) {
		// TODO Auto-generated method stub
		Date insertTime= new Date(new java.util.Date().getTime());
		question.setqMadeDate(insertTime);
		int flag = questionMapper.updateQuestionById(question);
		if(flag == 1) {
			return true;
		} else {
			return  false;
		}
	}
	
	@Override
	public Question getQuestionById(int qId) {
		// TODO Auto-generated method stub
		Question question = questionMapper.queryQuestionById(qId);
		//String jsonStrQuestion = JSON.toJSONString(question);
		//System.out.println(jsonStrQuestion);
		return question;
	}

	@Override
	public List<Question> getQuestionsByTime() {
		// TODO Auto-generated method stub
		return questionMapper.queryQuestionByTime();
	}

	@Override
	//根据uId获取此用户提出的所有问题
	//之后可以设置匿名问题不能被查询出来，待拓展
	public List<Question> getQuestionsByUserId(int uId) {
		// TODO Auto-generated method stub
		return questionMapper.queryQuestionByUserId(uId);
	}

	@Override
	public List<Question> getQuestionsByTopic(Topic topic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> getQuestionsByKeywords(String keywords) {
		return questionMapper.queryQuestionByKeywords(keywords);
	}

}
