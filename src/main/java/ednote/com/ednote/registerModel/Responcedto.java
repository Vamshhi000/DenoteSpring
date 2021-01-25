package ednote.com.ednote.registerModel;

import java.util.Date;

public class Responcedto {
	
	private String email;
	private String fullName;
	private String phoneNumber;
	private String gender;
	private Date dob;
	private String msg;
	private Boolean isAuthenticated;
	private String newPassword;
	private String confirmNewPassword;
	private String password;
	
	
	
	public  Responcedto() {

	}
	
	
	
	public Responcedto(String email, String fullName, String phoneNumber, String gender, Date dob, String msg,
			Boolean isAuthenticated, String newPassword, String confirmNewPassword, String password) {
		super();
		this.email = email;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.dob = dob;
		this.msg = msg;
		this.isAuthenticated = isAuthenticated;
		this.newPassword = newPassword;
		this.confirmNewPassword = confirmNewPassword;
		this.password = password;
	}













	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Boolean getIsAuthenticated() {
		return isAuthenticated;
	}
	public void setIsAuthenticated(Boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}
	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
