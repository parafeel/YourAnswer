package parafeel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import parafeel.pojo.Answer;
import parafeel.pojo.Question;
import parafeel.pojo.Topic;

public interface QuestionMapper {
	public void addQuestion(Question question);
	
	public Question hasQTitle(String qTitle);
	
	public void deleteQuestionById(Integer qId);
	
	public int updateQuestionById(Question Question);
	
	public Question queryQuestionById(Integer qId);
	
	public Question queryQuesion(Question question);
	
	public List<Question> queryQuestionByTime();
	
	public List<Question> queryQuestionByUserId(@Param("qMadeByUserId")Integer qMadeByUserId);
//	
//	public List<Question> getQuestionByTopic(Topic topic);
//	
//	public List<Answer> getAnswerByQuestion(Integer aBelongToQuestionId);

}
