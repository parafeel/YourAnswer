package parafeel.service.Impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import parafeel.mapper.AnswerMapper;
import parafeel.pojo.Answer;
import parafeel.service.AnswerService;

@Service
public class AnswerServiceImple implements AnswerService{

	@Autowired
	AnswerMapper answerMapper;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackForClassName="Exception")
	public boolean putAnswer(Answer answer) {
		// TODO Auto-generated method stub
		Answer newAnswer = answerMapper.hasAnswerd(answer);
		if(newAnswer != null) {
			return false;
		}
		Date insertTime= new java.sql.Date(new java.util.Date().getTime());
		answer.setaMadeDate(insertTime);
		answerMapper.addAnswer(answer);
		return true;
	}

	@Override
	public Answer getAnswerByUser(int uId,int qId) {
		// TODO Auto-generated method stub
		return answerMapper.hasAnswerdById(uId, qId);
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
	public List<Answer> getAnswersByUserId(int aMadeByUserId) {
		// TODO Auto-generated method stub
		List<Answer> answers = answerMapper.queryAnswersByUserId(aMadeByUserId);
		return answers;
	}
	
	

}
