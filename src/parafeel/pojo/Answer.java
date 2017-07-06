package parafeel.pojo;

import java.util.Date;
import java.util.HashMap;

public class Answer {
	
	private int aId;
	private String aContent;
	private int aMadeByUserId;
	
	private User aMadeByUser;
	
	private Date aMadeDate;
	private int aBelongToQuestionId;
	
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
	
	
	public User getaMadeByUser() {
		return aMadeByUser;
	}
	public void setaMadeByUser(User aMadeByUser) {
		this.aMadeByUser = aMadeByUser;
	}
	public Date getaMadeDate() {
		return aMadeDate;
	}
	public void setaMadeDate(Date aMadeDate) {
		this.aMadeDate = aMadeDate;
	}
	public int getaBelongToQuestionId() {
		return aBelongToQuestionId;
	}
	public void setaBelongToQuestionId(int aBelongToQuestionId) {
		this.aBelongToQuestionId = aBelongToQuestionId;
	}
	public Question getaBelongToQuestion() {
		return aBelongToQuestion;
	}
	public void setaBelongToQuestion(Question aBelongToQuestion) {
		this.aBelongToQuestion = aBelongToQuestion;
	}
	
	

}
