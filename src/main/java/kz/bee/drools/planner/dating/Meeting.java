package kz.bee.drools.planner.dating;

import java.io.Serializable;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.drools.planner.api.domain.entity.PlanningEntity;
import org.drools.planner.api.domain.variable.PlanningVariable;
import org.drools.planner.api.domain.variable.ValueRangeFromSolutionProperty;

@PlanningEntity//(difficultyWeightFactoryClass = MeetingDifficultyWeightFactory.class)
public class Meeting implements Serializable, Comparable<Meeting> {

	private Long id;
	
	private Table table;
	private Hour hour;
	
	
	private Man man;
	private Woman woman;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Table getTable() {
		return table;
	}
	public void setTable(Table table) {
		this.table = table;
	}
	
	public Hour getHour() {
		return hour;
	}
	public void setHour(Hour hour) {
		this.hour = hour;
	}
	
	
	@PlanningVariable//(strengthWeightFactoryClass=ManStrengthWeightFactory.class)
	@ValueRangeFromSolutionProperty(propertyName="manList")
	public Man getMan() {
		return man;
	}
	public void setMan(Man man) {
		this.man = man;
	}
	
	
	@PlanningVariable//(strengthWeightFactoryClass=WomanStrengthWeightFactory.class)
	@ValueRangeFromSolutionProperty(propertyName="womanList")
	public Woman getWoman() {
		return woman;
	}
	public void setWoman(Woman woman) {
		this.woman = woman;
	}
	
	public Meeting clone() {
		Meeting m = new Meeting();
		
		m.id = this.id;
		m.table = this.table;
		m.hour = this.hour;
		m.man = this.man;
		m.woman = this.woman;
		
		return m;
	}
	
	public int compareTo(Meeting other) {
		return new CompareToBuilder()
				.append(table, other.table)
				.append(hour, other.hour)
				.append(man, other.man)
				.append(woman, other.woman)
				.append(id, other.id)
				.toComparison();
	}
	
	/**
     * The normal methods {@link #equals(Object)} and {@link #hashCode()} cannot be used
     * because the rule engine already requires them (for performance in their original state).
     * @see #solutionHashCode()
     */
    public boolean solutionEquals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Meeting) {
        	Meeting other = (Meeting) o;
            return new EqualsBuilder()
                    .append(id, other.id)
                    .append(table, other.table)
					.append(hour, other.hour)
					.append(man, other.man)
					.append(woman, other.woman)
                    .isEquals();
        } else {
            return false;
        }
    }

    /**
     * The normal methods {@link #equals(Object)} and {@link #hashCode()} cannot be used
     * because the rule engine already requires them (for performance in their original state).
     * @see #solutionEquals(Object)
     */
    public int solutionHashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(table)
				.append(hour)
				.append(man)
				.append(woman)
                .toHashCode();
    }
    
    @Override
	public String toString() {
		return "Meeting [table=" + table + ", hour=" + hour + ", man=" + man
				+ ", woman=" + woman + "]";
	}
}
