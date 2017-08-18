package ssm.service;

import ssm.pojo.Operation;

import java.util.List;

public interface OperationService {

	boolean putOperation(Operation operation);

	Operation getOperationById(Integer optId);

	//查询最近n天的
	List<Operation> getFollowingOperations(int myuId, int nDay);

}
