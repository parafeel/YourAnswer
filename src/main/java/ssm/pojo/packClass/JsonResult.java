package ssm.pojo.packClass;

/**
 * Created by wh-pc on 2017/8/14.
 */
public class JsonResult<T> {
	//返回状态码，200为成功，400为失败
	private int resultCode;
	//状态的字符串，成功，失败
	private String reason;
	//返回的实际内容
	private T value;

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
}
