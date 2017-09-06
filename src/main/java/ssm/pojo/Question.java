package ssm.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Question implements Serializable{

	private static final long serialVersionUID = -841603625012928460L;
	//五个基本内容，对应DB
	private int qId;
	private String qTitle;
	private String qDetail;
	private int qMadeByUserId;
	private Date qMadeDate;

	//额外关联信息，可以查询填充
	private User qMadeByUser;
	private List<Answer> qAnswers;
	private List<Topic> qTopics;


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
	public Date getqMadeDate() {
		return qMadeDate;
	}
	public void setqMadeDate(Date qMadeDate) {
		this.qMadeDate = qMadeDate;
	}
	
	public User getqMadeByUser() {
		return qMadeByUser;
	}
	public void setqMadeByUser(User qMadeByUser) {
		this.qMadeByUser = qMadeByUser;
	}
	public List<Answer> getqAnswers() {
		return qAnswers;
	}
	public void setqAnswers(List<Answer> qAnswers) {
		this.qAnswers = qAnswers;
	}

	public List<Topic> getqTopics() {
		return qTopics;
	}

	public void setqTopics(List<Topic> qTopics) {
		this.qTopics = qTopics;
	}

}
