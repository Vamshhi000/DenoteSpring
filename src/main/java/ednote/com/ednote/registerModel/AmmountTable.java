package ednote.com.ednote.registerModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "ammount_table")
public class AmmountTable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String tagName;
	
	private long ammount;




	public static String startDate() {
		LocalDateTime now = LocalDateTime.now();
		  DateTimeFormatter format =
				  DateTimeFormatter.ofPattern("yyyy-MM-dd");

		return now.format(format);
		
		
	}
public static String endWeeks() {
	LocalDateTime now = LocalDateTime.now();
	LocalDateTime then = now.minusWeeks(4);
	  DateTimeFormatter format =
			  DateTimeFormatter.ofPattern("yyyy-MM-dd");

	return then.format(format);
}

public static String endMonths() {
	LocalDateTime now = LocalDateTime.now();
	LocalDateTime then = now.minusMonths(6);
	  DateTimeFormatter format =
			  DateTimeFormatter.ofPattern("yyyy-MM-dd");

	return then.format(format);
}
public static String endAnnual() {
	LocalDateTime now = LocalDateTime.now();
	LocalDateTime then = now.minusYears(1);
	  DateTimeFormatter format =
			  DateTimeFormatter.ofPattern("yyyy-MM-dd");

	return then.format(format);
}
public static String endDate() {
	LocalDateTime now = LocalDateTime.now();
	LocalDateTime then = now.minusDays(7);
	  DateTimeFormatter format =
			  DateTimeFormatter.ofPattern("yyyy-MM-dd");

	return then.format(format);
}

private String ammountDate=startDate();
//	@Temporal(TemporalType.DATE)
//	private Date ammountDate= new Date(System.currentTimeMillis());

	private String name;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="email")
	private RegisterModel registerModel;
	


	public AmmountTable() {
		
	}

	
	
	public AmmountTable(String tagName, long ammount, String ammountDate, String name, RegisterModel registerModel) {
		super();
		this.tagName = tagName;
		this.ammount = ammount;
		this.ammountDate = ammountDate;
		this.name = name;
		this.registerModel = registerModel;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public long getAmmount() {
		return ammount;
	}

	public void setAmmount(long ammount) {
		this.ammount = ammount;
	}

	public String getAmmountDate() {
		return ammountDate;
	}

	public void setAmmountDate(String ammountDate) {
		this.ammountDate = ammountDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@JsonIgnore
	public RegisterModel getRegisterModel() {
		return registerModel;
	}
	@JsonIgnore
	public void setRegisterModel(RegisterModel registerModel) {
		this.registerModel = registerModel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (ammount ^ (ammount >>> 32));
		result = prime * result + ((ammountDate == null) ? 0 : ammountDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((registerModel == null) ? 0 : registerModel.hashCode());
		result = prime * result + ((tagName == null) ? 0 : tagName.hashCode());
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
		AmmountTable other = (AmmountTable) obj;
		if (ammount != other.ammount)
			return false;
		if (ammountDate == null) {
			if (other.ammountDate != null)
				return false;
		} else if (!ammountDate.equals(other.ammountDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (registerModel == null) {
			if (other.registerModel != null)
				return false;
		} else if (!registerModel.equals(other.registerModel))
			return false;
		if (tagName == null) {
			if (other.tagName != null)
				return false;
		} else if (!tagName.equals(other.tagName))
			return false;
		return true;
	}

	


	
	
	
	
}
