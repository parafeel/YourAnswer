package ssm.pojo;

import java.util.Date;

/**
 * Created by wh-pc on 2017/7/25.
 */
public class Essay {
	//五个基本内容，对应DB
	private int essayId;
	private String essayTitle;
	private String essayContent;
	private int essayMadeByUserId;
	private Date essayMadeDate;

	//额外关联信息，可以查询填充
	private User essayMadeByUser;

	public int getEssayId() {
		return essayId;
	}

	public void setEssayId(int essayId) {
		this.essayId = essayId;
	}

	public String getEssayTitle() {
		return essayTitle;
	}

	public void setEssayTitle(String essayTitle) {
		this.essayTitle = essayTitle;
	}

	public String getEssayContent() {
		return essayContent;
	}

	public void setEssayContent(String essayContent) {
		this.essayContent = essayContent;
	}

	public int getEssayMadeByUserId() {
		return essayMadeByUserId;
	}

	public void setEssayMadeByUserId(int essayMadeByUserId) {
		this.essayMadeByUserId = essayMadeByUserId;
	}

	public Date getEssayMadeDate() {
		return essayMadeDate;
	}

	public void setEssayMadeDate(Date essayMadeDate) {
		this.essayMadeDate = essayMadeDate;
	}

	public User getEssayMadeByUser() {
		return essayMadeByUser;
	}

	public void setEssayMadeByUser(User essayMadeByUser) {
		this.essayMadeByUser = essayMadeByUser;
	}
}
