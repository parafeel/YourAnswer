package ssm.service;

import java.util.List;

import ssm.pojo.Answer;
import ssm.pojo.Operation;
import ssm.pojo.Question;
import ssm.pojo.User;

public interface AnswerService {

	Answer putAnswer(Answer answer, Question question, User user);

	Answer getAnswerByUser(int uId, int qId);

	void deleteAnswerById(int aId);

	boolean updateAnswerById(Answer answer);

	Answer getAnserById(int aId);

	List<Answer> getAnswersByQuestion(int aBelongToQuestionId);

	//根据uId获取此用户回答的所有答案
	List<Answer> getAnswersByUserId(int aMadeByUserId);
	//根据关键字查询相关答案
	List<Answer> getAnswersByKeyWords(String keywords);



}
