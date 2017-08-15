package ssm.pojo.packClass;

import ssm.pojo.Answer;
import ssm.pojo.Essay;
import ssm.pojo.Question;

import java.util.List;

/**
 * Created by wh-pc on 2017/8/15.
 */
public class PackAQE {
	//返回状态码，200为成功，400为失败
	private int resultCode;
	//状态的字符串，成功，失败
	private String reason;
	//返回的实际内容
	private List<Question> questions;
	private List<Answer> answers;
	private List<Essay> essays;

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

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public List<Essay> getEssays() {
		return essays;
	}

	public void setEssays(List<Essay> essays) {
		this.essays = essays;
	}
}
