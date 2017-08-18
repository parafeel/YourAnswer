package ssm.mapper;


import org.apache.ibatis.annotations.Param;
import ssm.pojo.Operation;

import java.util.List;

public interface OperationMapper {

	//增加动作
	int addOperation(Operation operation);
	//查询某个动作
	Operation queryOperationById(@Param("optId") Integer optId);
	//查询某用户动作
	List<Operation> queryOperationsByuId(@Param("uId") Integer uId);
	//查询我关注的用户的动作
	List<Operation> queryFollowingOperations(@Param("myuId") int myuId,@Param("nDay") int nDay);
}
