package kz.bee.drools.planner.dating;

import java.io.Serializable;

import org.apache.commons.lang.builder.CompareToBuilder;

public class Woman implements Serializable, Comparable<Woman> {

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
		return "Woman [id=" + id + ", name=" + name + "]";
	}
	
	public int compareTo(Woman other) {
		return new CompareToBuilder()
	        .append(name, other.name)
	        .append(id, other.id)
	        .toComparison();
	}
}
