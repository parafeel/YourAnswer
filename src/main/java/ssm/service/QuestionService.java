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
	
	List<Question> getQuestionByTime();
	
	List<Question> getQuestionsByUserId(int uId);

	List<Question> getQuestionsByTopic(Topic topic);
	
}
