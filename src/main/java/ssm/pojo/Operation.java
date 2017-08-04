package ssm.pojo;

import java.util.Date;

/**
 * Created by wh-pc on 2017/7/31.
 */
public class Operation {
	private int optId;
	private int uId;
	private byte operationType;
	private int operationId;
	private byte  parentType;
	private int parentId;
	private Date createTime;

	public Operation() {
	}

	public Operation(int uId, byte operationType, int operationId) {
		this.uId = uId;
		this.operationType = operationType;
		this.operationId = operationId;
	}

	public int getOptId() {
		return optId;
	}

	public void setOptId(int optId) {
		this.optId = optId;
	}

	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	public byte getOperationType() {
		return operationType;
	}

	public void setOperationType(byte operationType) {
		this.operationType = operationType;
	}

	public int getOperationId() {
		return operationId;
	}

	public void setOperationId(int operationId) {
		this.operationId = operationId;
	}

	public byte getParentType() {
		return parentType;
	}

	public void setParentType(byte parentType) {
		this.parentType = parentType;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
