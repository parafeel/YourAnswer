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

	List<Answer> getAnswersByUserId(int aMadeByUserId);
	
}
