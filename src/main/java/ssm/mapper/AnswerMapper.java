package ssm.mapper;


import 	org.apache.ibatis.annotations.Param;

import 	ssm.pojo.Answer;
import ssm.pojo.Question;

import  java.util.List;

public interface AnswerMapper {

	int addAnswer(Answer answer);
	
	//用@Param("aMadeByUserId")，来标识传入到xml的parameter的名称
	Answer hasAnsweredById(@Param("aMadeByUserId") Integer aMadeByUserId, @Param("aBelongToQuestionId") Integer
            aBelongToQuestionId);
	
	Answer hasAnswerd(Answer answer);
	
	void deleteAnswerById(Integer aId);
	
	int updateAnswerById(Answer answer);
	
	Answer queryAnswerById(Integer aId);
	
	List<Answer> queryAnswersByQuestion(@Param("aBelongToQuestionId") Integer aBelongToQuestionId);
	//根据uId获取此用户回答的所有答案
	List<Answer> queryAnswersByUserId(@Param("aMadeByUserId") Integer aMadeByUserId);
	//根据关键字查询相关答案
	List<Answer> queryAnswersByKeyWords(@Param("keywords") String keywords);

	//queryFeedQuestionById
	Answer queryFeedAnswerById(Integer aId);
}
