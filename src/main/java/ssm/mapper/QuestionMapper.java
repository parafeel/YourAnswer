package ssm.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import ssm.pojo.Question;

public interface QuestionMapper {
	void addQuestion(Question question);
	
	Question hasQTitle(String qTitle);
	
	void deleteQuestionById(Integer qId);
	
	int updateQuestionById(Question Question);
	
	Question queryQuestionById(Integer qId);
	
	Question queryQuesion(Question question);
	
	List<Question> queryQuestionByTime();
	//根据uId获取此用户提出的所有问题
	List<Question> queryQuestionByUserId(@Param("qMadeByUserId") Integer qMadeByUserId);
//	
//	public List<Question> getQuestionByTopic(Topic topic);
//	
//	public List<Answer> getAnswerByQuestion(Integer aBelongToQuestionId);

}
