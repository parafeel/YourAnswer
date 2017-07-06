package parafeel.service;

import java.util.List;

import parafeel.pojo.Answer;
import parafeel.pojo.Question;
import parafeel.pojo.Topic;

public interface QuestionService {
	
	public void putQuestion(Question question);

	public Question getQuestion(String qTitle);
	
	public void deleteQuestionById(int qId);
	
	public void updateQuestionById(Question question);
	
	public Question getQuestionById(int qId);
	
	public List<Question> getQuestionByTime();
	
	public List<Question> getQuestionsByUserId(int uId);

	public List<Question> getQuestionsByTopic(Topic topic);
	
}
