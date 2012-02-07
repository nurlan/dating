package kz.bee.drools.planner.dating;

import java.io.Serializable;

public class Table implements Serializable {

	private Long id;
	private int number;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	@Override
	public String toString() {
		return "Table [id=" + id + ", number=" + number + "]";
	}
}
