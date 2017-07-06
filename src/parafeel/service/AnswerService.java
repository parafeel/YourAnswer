package parafeel.service;

import java.util.List;

import parafeel.pojo.Answer;

public interface AnswerService {
	
	public boolean putAnswer(Answer answer);
	
	public Answer getAnswerByUser(int uId,int qId);

	public void deleteAnswerById(int aId);

	public void updateAnswerById(Answer answer);

	public Answer getAnserById(int aId);

	public List<Answer> getAnswerByQuestion(int aBelongToQuestionId);
	
	public List<Answer> getAnswersByUserId(int aMadeByUserId);
	
}
