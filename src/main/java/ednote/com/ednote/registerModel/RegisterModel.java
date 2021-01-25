package ednote.com.ednote.registerModel;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "denote")


public class RegisterModel {
	@Id
	private String email;
	private String fullName;
	private String password;
	private String reenterPassword;
	private String passtoken;
	private String phoneNumber;
	private String gender;

	private Date dob;
	

	public RegisterModel() {
		
	}


	public RegisterModel(String email, String fullName, String password, String reenterPassword, String passtoken,
			String phoneNumber, String gender, Date dob) {
		super();
		this.email = email;
		this.fullName = fullName;
		this.password = password;
		this.reenterPassword = reenterPassword;
		this.passtoken = passtoken;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.dob = dob;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((passtoken == null) ? 0 : passtoken.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((reenterPassword == null) ? 0 : reenterPassword.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegisterModel other = (RegisterModel) obj;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (passtoken == null) {
			if (other.passtoken != null)
				return false;
		} else if (!passtoken.equals(other.passtoken))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (reenterPassword == null) {
			if (other.reenterPassword != null)
				return false;
		} else if (!reenterPassword.equals(other.reenterPassword))
			return false;
		return true;
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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getReenterPassword() {
		return reenterPassword;
	}


	public void setReenterPassword(String reenterPassword) {
		this.reenterPassword = reenterPassword;
	}


	public String getPasstoken() {
		return passtoken;
	}


	public void setPasstoken(String passtoken) {
		this.passtoken = passtoken;
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


	

}
