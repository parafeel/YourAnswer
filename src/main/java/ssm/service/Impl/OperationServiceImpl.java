package ssm.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ssm.mapper.OperationMapper;
import ssm.pojo.Operation;
import ssm.service.OperationService;

import java.sql.Date;

/**
 * Created by wh-pc on 2017/8/1.
 */
@Service
public class OperationServiceImpl implements OperationService {

	@Autowired
	private OperationMapper operationMapper;

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
}
