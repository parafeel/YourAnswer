package ssm.pojo.utilPojo;

import java.util.Date;

/**
 * Created by wh-pc on 2017/8/30.
 */
public class TopicRelation {
	int trId;
	int tId;
	byte trType;
	int trTypeId;
	private Date trCreatetime;

	public TopicRelation() {
	}

	public TopicRelation(int tId, byte trType, int trTypeId, Date trCreatetime) {
		this.tId = tId;
		this.trType = trType;
		this.trTypeId = trTypeId;
		this.trCreatetime = trCreatetime;
	}

	public int getTrId() {
		return trId;
	}

	public void setTrId(int trId) {
		this.trId = trId;
	}

	public int gettId() {
		return tId;
	}

	public void settId(int tId) {
		this.tId = tId;
	}

	public byte getTrType() {
		return trType;
	}

	public void setTrType(byte trType) {
		this.trType = trType;
	}

	public int getTrTypeId() {
		return trTypeId;
	}

	public void setTrTypeId(int trTypeId) {
		this.trTypeId = trTypeId;
	}

	public Date getTrCreatetime() {
		return trCreatetime;
	}

	public void setTrCreatetime(Date trCreatetime) {
		this.trCreatetime = trCreatetime;
	}
}
