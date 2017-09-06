package ssm.pojo;

import java.util.Date;

public class Topic {
	private int tId;
	private String tName;
	private String tDetail;
	private Date tMadeDate;

	public int gettId() {
		return tId;
	}
	public void settId(int tId) {
		this.tId = tId;
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	public String gettDetail() {
		return tDetail;
	}
	public void settDetail(String tDetail) {
		this.tDetail = tDetail;
	}
	
	public Date gettMadeDate() {
		return tMadeDate;
	}
	public void settMadeDate(Date tMadeDate) {
		this.tMadeDate = tMadeDate;
	}
	
	
	
}
