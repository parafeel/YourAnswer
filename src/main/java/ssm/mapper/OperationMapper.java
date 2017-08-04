package ssm.mapper;


import org.apache.ibatis.annotations.Param;
import ssm.pojo.Operation;

import java.util.List;

public interface OperationMapper {

	int addOperation(Operation operation);
	
	List<Operation> queryOperationsByuId(@Param("uId") Integer uId);

	List<Operation> queryFollowingOperations(@Param("myuId") int myuId,@Param("nDay") int nDay);
}
