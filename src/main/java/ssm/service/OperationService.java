package ssm.service;

import ssm.pojo.Answer;
import ssm.pojo.Operation;
import ssm.pojo.Question;
import ssm.pojo.User;

import java.util.List;

public interface OperationService {

	boolean putOperation(Operation operation);

	//查询最近n天的
	List<Operation> getFollowingOperations(int myuId, int nDay);

}
