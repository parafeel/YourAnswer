package ssm.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ssm.mapper.*;
import ssm.pojo.*;
import ssm.service.OperationService;

import java.sql.Date;
import java.util.List;

/**
 * Created by wh-pc on 2017/8/1.
 */
@Service
public class OperationServiceImpl implements OperationService {

	@Autowired
	private OperationMapper operationMapper;
	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private AnswerMapper answerMapper;
	@Autowired
	private EssayMapper essayMapper;
	@Autowired
	private UserMapper userMapper;

	@Override
	@Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
	public boolean putOperation(Operation operation) {
		Date insertTime= new Date(new java.util.Date().getTime());
		operation.setCreateTime(insertTime);	//插入当前时间
		int flag = operationMapper.addOperation(operation);
		if(flag == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Operation getOperationById(Integer optId) {
		Operation operation = operationMapper.queryOperationById(optId);
		return operation;
	}

	@Override
	public List<Operation> getFollowingOperations(int myuId, int nDay) {
		List<Operation> operations = operationMapper.queryFollowingOperations(myuId,nDay);
		Operation currentOperation;
		User optMadeByUser;
		Question currentQuestion;
		Answer currentAnswer;
		Essay currentEssay;
		for (int i = 0; i < operations.size(); i++) {
			currentOperation = operations.get(i);
			//增加关注人的信息
			optMadeByUser = userMapper.queryFeedUserById(currentOperation.getuId());
			currentOperation.setOptMadeByUser(optMadeByUser);
			//根据类别不同查询不同的 具体内容
			if(currentOperation.getOperationType() == 100) {
				currentQuestion = questionMapper.queryFeedQuestionById(currentOperation.getOperationId());
				currentOperation.setActionMadeByUser(currentQuestion.getqMadeByUser());
				currentOperation.setOptTitle(currentQuestion.getqTitle());
				currentOperation.setOptContent(currentQuestion.getqDetail());
				currentOperation.setOptTime(currentQuestion.getqMadeDate());
			} else if(currentOperation.getOperationType() == 101) {
				currentAnswer = answerMapper.queryFeedAnswerById(currentOperation.getOperationId());
				currentOperation.setActionMadeByUser(currentAnswer.getaMadeByUser());
				currentOperation.setOptTitle(currentAnswer.getaBelongToQuestion().getqTitle());
				currentOperation.setOptContent(currentAnswer.getaContent());
				currentOperation.setOptTime(currentAnswer.getaMadeDate());
			} else if (currentOperation.getOperationType() == 102) {
				currentEssay = essayMapper.queryFeedEssayById(currentOperation.getOperationId());
				currentOperation.setActionMadeByUser(currentEssay.getEssayMadeByUser());
				currentOperation.setOptTitle(currentEssay.getEssayTitle());
				currentOperation.setOptContent(currentEssay.getEssayContent());
				currentOperation.setOptTime(currentEssay.getEssayMadeDate());
			}
		}
		return operations;
	}
}
