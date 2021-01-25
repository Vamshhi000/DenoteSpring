package ednote.com.ednote.registerModel;

public class BarChartDto {
	
	private String weekDay;
	private long ammount;
	
	public BarChartDto() {
		
	}

	public BarChartDto(String weekDay, long ammount) {
		super();
		this.weekDay = weekDay;
		this.ammount = ammount;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	public long getAmmount() {
		return ammount;
	}

	public void setAmmount(long ammount) {
		this.ammount = ammount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (ammount ^ (ammount >>> 32));
		result = prime * result + ((weekDay == null) ? 0 : weekDay.hashCode());
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
		BarChartDto other = (BarChartDto) obj;
		if (ammount != other.ammount)
			return false;
		if (weekDay == null) {
			if (other.weekDay != null)
				return false;
		} else if (!weekDay.equals(other.weekDay))
			return false;
		return true;
	}
	
	

}
