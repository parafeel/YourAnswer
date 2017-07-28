package ssm.pojo;

import java.util.Date;

public class User {
	
	private int uId;
	private String uPassword;
	private String uEmail;
	private String uRealName;
	private String uTel;
	private Date uRegistDate;

	private String uName;
	private String uGender;
	private String uWord;
	private String uResidence;
	private String uProfession;
	private int uAuthority;	//普通为0，管理员100


	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getuPassword() {
		return uPassword;
	}
	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}
	public String getuEmail() {
		return uEmail;
	}
	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}
	public String getuRealName() {
		return uRealName;
	}
	public void setuRealName(String uRealName) {
		this.uRealName = uRealName;
	}
	public String getuTel() {
		return uTel;
	}
	public void setuTel(String uTel) {
		this.uTel = uTel;
	}
	public Date getuRegistDate() {
		return uRegistDate;
	}
	public void setuRegistDate(Date uRegistDate) {
		this.uRegistDate = uRegistDate;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuGender() {
		return uGender;
	}
	public void setuGender(String uGender) {
		this.uGender = uGender;
	}
	public String getuWord() {
		return uWord;
	}
	public void setuWord(String uWord) {
		this.uWord = uWord;
	}
	public String getuResidence() {
		return uResidence;
	}
	public void setuResidence(String uResidence) {
		this.uResidence = uResidence;
	}
	public String getuProfession() {
		return uProfession;
	}
	public void setuProfession(String uProfession) {
		this.uProfession = uProfession;
	}
	public int getuAuthority() {
		return uAuthority;
	}
	public void setuAuthority(int uAuthority) {
		this.uAuthority = uAuthority;
	}




}
