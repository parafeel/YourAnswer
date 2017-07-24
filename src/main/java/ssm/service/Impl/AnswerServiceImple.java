package ssm.service.Impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ssm.mapper.AnswerMapper;
import ssm.pojo.Answer;
import ssm.service.AnswerService;

@Service
public class AnswerServiceImple implements AnswerService{

	private AnswerMapper answerMapper;

	@Autowired
	public void setAnswerMapper(AnswerMapper answerMapper) {
		this.answerMapper = answerMapper;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackForClassName="Exception")
	public boolean putAnswer(Answer answer) {
		// TODO Auto-generated method stub
		Answer newAnswer = answerMapper.hasAnswerd(answer);
		if(newAnswer != null) {
			return false;
		}
		Date insertTime= new Date(new java.util.Date().getTime());
		answer.setaMadeDate(insertTime);
		answerMapper.addAnswer(answer);
		return true;
	}

	@Override
	public Answer getAnswerByUser(int uId,int qId) {
		// TODO Auto-generated method stub
		return answerMapper.hasAnsweredById(uId, qId);
	}

	@Override
	public void deleteAnswerById(int aId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAnswerById(Answer answer) {
		// TODO Auto-generated method stub

	}

	@Override
	public Answer getAnserById(int aId) {
		// TODO Auto-generated method stub
		return answerMapper.queryAnswerById(aId);
	}

	@Override
	public List<Answer> getAnswerByQuestion(int aBelongToQuestionId) {
		// TODO Auto-generated method stub
		List<Answer> answers = answerMapper.queryAnswersByQuestion(aBelongToQuestionId);
		return answers;
	}

	@Override
	//根据uId获取此用户回答的所有答案
	public List<Answer> getAnswersByUserId(int aMadeByUserId) {
		// TODO Auto-generated method stub
		List<Answer> answers = answerMapper.queryAnswersByUserId(aMadeByUserId);
		return answers;
	}

	@Override
	public List<Answer> getAnswersByKeyWords(String keywords) {
		List<Answer> answers = answerMapper.queryAnswersByKeyWords(keywords);
		return answers;
	}
}
