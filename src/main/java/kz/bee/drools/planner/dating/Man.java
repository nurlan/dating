package kz.bee.drools.planner.dating;

import java.io.Serializable;

import org.apache.commons.lang.builder.CompareToBuilder;

public class Man implements Serializable, Comparable<Man> {

	private Long id;
	private String name;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Man [id=" + id + ", name=" + name + "]";
	}
	
	public int compareTo(Man other) {
		return new CompareToBuilder()
	        .append(name, other.name)
	        .append(id, other.id)
	        .toComparison();
	}
}
