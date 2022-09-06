package shm.model;

public class Revenue {
	private int year;
	private int month;
	private Long total;
	public Revenue() {
		
	}
	public Revenue(int year, int month, Long total) {
		super();
		this.year = year;
		this.month = month;
		this.total = total;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	
}
