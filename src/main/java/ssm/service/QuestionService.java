package ssm.service;

import java.util.List;

import ssm.pojo.Answer;
import ssm.pojo.Question;
import ssm.pojo.Topic;

public interface QuestionService {
	
	void putQuestion(Question question);

	Question getQuestion(String qTitle);
	
	void deleteQuestionById(int qId);
	
	void updateQuestionById(Question question);

	Question getQuestionById(int qId);
	//通过时间查询出问题列表
	List<Question> getQuestionsByTime();
	//根据uId获取此用户提出的问题列表
	List<Question> getQuestionsByUserId(int uId);

	List<Question> getQuestionsByTopic(Topic topic);
	//通过关键字查询相关问题
	List<Question> getQuestionsByKeywords(String keywords);
	
}
