package ssm.pojo;

import java.io.Serializable;
import java.util.Date;

public class Answer implements Serializable{

	private static final long serialVersionUID = -1916693914525855911L;

	//五个基本内容，对应DB
	private int aId;
	private String aContent;
	private int aMadeByUserId;
	private int aBelongToQuestionId;
	private Date aMadeDate;

	//额外关联信息，可以查询填充
	private User aMadeByUser;
	private Question aBelongToQuestion;

	public int getaId() {
		return aId;
	}
	public void setaId(int aId) {
		this.aId = aId;
	}
	public String getaContent() {
		return aContent;
	}
	public void setaContent(String aContent) {
		this.aContent = aContent;
	}
	public int getaMadeByUserId() {
		return aMadeByUserId;
	}
	public void setaMadeByUserId(int aMadeByUserId) {
		this.aMadeByUserId = aMadeByUserId;
	}
	public int getaBelongToQuestionId() {
		return aBelongToQuestionId;
	}
	public void setaBelongToQuestionId(int aBelongToQuestionId) {
		this.aBelongToQuestionId = aBelongToQuestionId;
	}
	public Date getaMadeDate() {
		return aMadeDate;
	}
	public void setaMadeDate(Date aMadeDate) {
		this.aMadeDate = aMadeDate;
	}


	public User getaMadeByUser() {
		return aMadeByUser;
	}
	public void setaMadeByUser(User aMadeByUser) {
		this.aMadeByUser = aMadeByUser;
	}
	public Question getaBelongToQuestion() {
		return aBelongToQuestion;
	}
	public void setaBelongToQuestion(Question aBelongToQuestion) {
		this.aBelongToQuestion = aBelongToQuestion;
	}




}
