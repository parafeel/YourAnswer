package ssm.service;

import java.util.List;

import ssm.pojo.Answer;

public interface AnswerService {
	
	boolean putAnswer(Answer answer);

	Answer getAnswerByUser(int uId, int qId);

	void deleteAnswerById(int aId);

	void updateAnswerById(Answer answer);

	Answer getAnserById(int aId);

	List<Answer> getAnswerByQuestion(int aBelongToQuestionId);

	//根据uId获取此用户回答的所有答案
	List<Answer> getAnswersByUserId(int aMadeByUserId);
	//根据关键字查询相关答案
	List<Answer> getAnswersByKeyWords(String keywords);
}
