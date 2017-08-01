package ssm.service.Impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ssm.mapper.AnswerMapper;
import ssm.mapper.OperationMapper;
import ssm.pojo.Answer;
import ssm.pojo.Question;
import ssm.pojo.User;
import ssm.service.AnswerService;
import ssm.util.UserOperation;

@Service
public class AnswerServiceImple implements AnswerService{

	@Autowired
	private AnswerMapper answerMapper;


	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackForClassName="Exception")
	public Answer putAnswer(Answer answer, Question question, User user) {
		// TODO Auto-generated method stub
		answer.setaMadeByUserId(user.getuId());
		answer.setaBelongToQuestionId(question.getqId());
		answer.setaBelongToQuestionTitle(question.getqTitle());
		Date insertTime= new Date(new java.util.Date().getTime());
		answer.setaMadeDate(insertTime);	//插入当前时间
		int flag = answerMapper.addAnswer(answer);
		if(flag == 1) {
			return answer;
		} else {
			return null;
		}
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
	public boolean updateAnswerById(Answer answer) {
		// TODO Auto-generated method stub
		Date insertTime= new Date(new java.util.Date().getTime());
		answer.setaMadeDate(insertTime);
		int flag = answerMapper.updateAnswerById(answer);
		if(flag == 1) {
			return true;
		} else {
			return false;
		}
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

	@Override
	public boolean putAnswerOperation(int uId, int operationType, int operationId) {
		operationType = UserOperation.TYPE_ANSWER;

		return false;
	}




}
