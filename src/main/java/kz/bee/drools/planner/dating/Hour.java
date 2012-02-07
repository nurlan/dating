package kz.bee.drools.planner.dating;

import java.io.Serializable;

public class Hour implements Serializable {

	private Long id;
	private int value;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Hour [id=" + id + ", value=" + value + "]";
	}
	
}
