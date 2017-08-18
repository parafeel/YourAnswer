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
	private String  realAction;
	private Date createTime;

	//产生这个feed的用户
	private User optMadeByUser;

	//具体内容的用户
	private User actionMadeByUser;
	//具体内容的Title
	private String optTitle;
	//具体内容的content
	private String optContent;
	//具体内容的时间
	private Date optTime;


	public Operation() {
	}

	public Operation(int uId, byte operationType, int operationId, String realAction) {
		this.uId = uId;
		this.operationType = operationType;
		this.operationId = operationId;
		this.realAction = realAction;
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

	public String getRealAction() {
		return realAction;
	}

	public void setRealAction(String realAction) {
		this.realAction = realAction;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getOptMadeByUser() {
		return optMadeByUser;
	}

	public void setOptMadeByUser(User optMadeByUser) {
		this.optMadeByUser = optMadeByUser;
	}

	public User getActionMadeByUser() {
		return actionMadeByUser;
	}

	public void setActionMadeByUser(User actionMadeByUser) {
		this.actionMadeByUser = actionMadeByUser;
	}

	public String getOptTitle() {
		return optTitle;
	}

	public void setOptTitle(String optTitle) {
		this.optTitle = optTitle;
	}

	public String getOptContent() {
		return optContent;
	}

	public void setOptContent(String optContent) {
		this.optContent = optContent;
	}

	public Date getOptTime() {
		return optTime;
	}

	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}
}
