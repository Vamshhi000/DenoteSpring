package ednote.com.ednote.registerModel;

import java.util.Date;

public class AmmountDto {
	
	private String name;
	private String tagName;
	private Long ammount;
	private String ammountDate;
	private long total;
	private long totalAmmount;
	private String message;
	public AmmountDto() {
		
	}
	public AmmountDto(String name, String tagName, Long ammount, String ammountDate, long total, long totalAmmount,
			String message) {
		super();
		this.name = name;
		this.tagName = tagName;
		this.ammount = ammount;
		this.ammountDate = ammountDate;
		this.total = total;
		this.totalAmmount = totalAmmount;
		this.message = message;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public Long getAmmount() {
		return ammount;
	}
	public void setAmmount(Long ammount) {
		this.ammount = ammount;
	}
	public String getAmmountDate() {
		return ammountDate;
	}
	public void setAmmountDate(String ammountDate) {
		this.ammountDate = ammountDate;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public long getTotalAmmount() {
		return totalAmmount;
	}
	public void setTotalAmmount(long totalAmmount) {
		this.totalAmmount = totalAmmount;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	








	
	
}
