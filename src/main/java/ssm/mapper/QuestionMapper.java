package ssm.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import ssm.pojo.Question;
import ssm.pojo.Topic;

public interface QuestionMapper {
	int addQuestion(Question question);
	
	Question hasQTitle(String qTitle);
	
	int deleteQuestionById(Integer qId);
	
	int updateQuestionById(Question Question);
	
	Question queryQuestionById(Integer qId);

	Question queryCompleteQuesionById(int qId);

	List<Topic> queryTopicOfQuestion(Question question);
	
	Question queryQuesion(Question question);
	
	List<Question> queryQuestionByTime();
	//根据uId获取此用户提出的所有问题
	List<Question> queryQuestionByUserId(@Param("qMadeByUserId") Integer qMadeByUserId);

//	public List<Question> getQuestionByTopic(Topic topic);

	List<Question> queryQuestionByKeywords(@Param("keywords") String keywords);


	//queryFeedQuestionById
	Question queryFeedQuestionById(@Param("qId") Integer qId);

}
