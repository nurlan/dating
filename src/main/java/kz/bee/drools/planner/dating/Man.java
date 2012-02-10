package kz.bee.drools.planner.dating;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.CompareToBuilder;

public class Man implements Serializable, Comparable<Man> {

	private Long id;
	private String name;
	private List<Long> suggestionList;
	
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
	
	public List<Long> getSuggestionList() {
		return suggestionList;
	}
	public void setSuggestionList(List<Long> suggestionList) {
		this.suggestionList = suggestionList;
	}
	
	@Override
	public String toString() {
		return "Man [id=" + id + ", name=" + name + ", suggestion="
				+ suggestionList + "]";
	}
	
	public int compareTo(Man other) {
		return new CompareToBuilder()
	        .append(name, other.name)
	        .append(suggestionList, other.suggestionList)
	        .append(id, other.id)
	        .toComparison();
	}
}
