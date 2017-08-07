package ssm.pojo;

import java.io.Serializable;
import java.util.Date;

public class Question implements Serializable{

	private static final long serialVersionUID = -841603625012928460L;

	private int qId;
	private String qTitle;
	private String qDetail;
	private int qMadeByUserId;
	
	private User qMadeByUser;
	
	private Date qMadeDate;
	
	public int getqId() {
		return qId;
	}
	public void setqId(int qId) {
		this.qId = qId;
	}
	public String getqTitle() {
		return qTitle;
	}
	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}
	public String getqDetail() {
		return qDetail;
	}
	public void setqDetail(String qDetail) {
		this.qDetail = qDetail;
	}
	public int getqMadeByUserId() {
		return qMadeByUserId;
	}
	public void setqMadeByUserId(int qMadeByUserId) {
		this.qMadeByUserId = qMadeByUserId;
	}
	
	public User getqMadeByUser() {
		return qMadeByUser;
	}
	public void setqMadeByUser(User qMadeByUser) {
		this.qMadeByUser = qMadeByUser;
	}
	
	public Date getqMadeDate() {
		return qMadeDate;
	}
	public void setqMadeDate(Date qMadeDate) {
		this.qMadeDate = qMadeDate;
	}
	
	
	
}
