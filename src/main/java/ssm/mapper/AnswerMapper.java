package ssm.mapper;


import 	org.apache.ibatis.annotations.Param;

import 	ssm.pojo.Answer;
import  java.util.List;

public interface AnswerMapper {
	void addAnswer(Answer answer);
	
	//用@Param("aMadeByUserId")，来标识传入到xml的parameter的名称
	Answer hasAnsweredById(@Param("aMadeByUserId") Integer aMadeByUserId, @Param("aBelongToQuestionId") Integer
            aBelongToQuestionId);
	
	Answer hasAnswerd(Answer answer);
	
	void deleteAnswerById(Integer aId);
	
	int updateAnswerById(Answer answer);
	
	Answer queryAnswerById(Integer aId);
	
	List<Answer> queryAnswersByQuestion(@Param("aBelongToQuestionId") Integer aBelongToQuestionId);

	List<Answer> queryAnswersByUserId(@Param("aMadeByUserId") Integer aMadeByUserId);
	
}
