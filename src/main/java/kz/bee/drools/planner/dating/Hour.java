package kz.bee.drools.planner.dating;

import java.io.Serializable;

import org.apache.commons.lang.builder.CompareToBuilder;

public class Hour implements Serializable, Comparable<Hour> {

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
	
	public int compareTo(Hour other) {
		return new CompareToBuilder()
	        .append(value, other.value)
	        .append(id, other.id)
	        .toComparison();
	}
	
}
