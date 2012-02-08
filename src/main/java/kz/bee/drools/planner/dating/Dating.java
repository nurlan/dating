package kz.bee.drools.planner.dating;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.drools.planner.api.domain.solution.PlanningEntityCollectionProperty;
import org.drools.planner.core.score.HardAndSoftScore;
import org.drools.planner.core.solution.Solution;

public class Dating implements Solution<HardAndSoftScore> {

	private Long id;
	private int n;
	
	private List<Table> tableList;
	private List<Hour> hourList;
	
	private List<Man> manList;
	private List<Woman> womanList;
	
	private List<Meeting> meetingList;
	
	private HardAndSoftScore score;
	
	public HardAndSoftScore getScore() {
		return this.score;
	}

	public void setScore(HardAndSoftScore score) {
		this.score = score;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public List<Table> getTableList() {
		return tableList;
	}

	public void setTableList(List<Table> tableList) {
		this.tableList = tableList;
	}

	public List<Hour> getHourList() {
		return hourList;
	}

	public void setHourList(List<Hour> hourList) {
		this.hourList = hourList;
	}

	public List<Man> getManList() {
		return manList;
	}

	public void setManList(List<Man> manList) {
		this.manList = manList;
	}

	public List<Woman> getWomanList() {
		return womanList;
	}

	public void setWomanList(List<Woman> womanList) {
		this.womanList = womanList;
	}

	@PlanningEntityCollectionProperty
	public List<Meeting> getMeetingList() {
		return meetingList;
	}

	public void setMeetingList(List<Meeting> meetingList) {
		this.meetingList = meetingList;
	}

	public Collection<? extends Object> getProblemFacts() {
		List<Object> facts = new ArrayList<Object>();
		facts.add(tableList);
		facts.add(hourList);
		facts.add(manList);
		facts.add(womanList);
		
		return facts;
	}
	public Solution<HardAndSoftScore> cloneSolution() {
		Dating clone = new Dating();
        clone.id = id;
        clone.tableList = tableList;
        clone.hourList = hourList;
        clone.manList = manList;
        clone.womanList = womanList;
        
        List<Meeting> clonedMeetingList = new ArrayList<Meeting>(meetingList.size());
        for (Meeting meeting : meetingList) {
            clonedMeetingList.add(meeting.clone());
        }
        clone.meetingList = clonedMeetingList;
        clone.score = score;
        
        return clone;
	}

	public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((id == null) || !(o instanceof Dating)) {
            return false;
        } else {
            Dating other = (Dating) o;
            if (meetingList.size() != other.meetingList.size()) {
                return false;
            }
            for (Iterator<Meeting> it = meetingList.iterator(), otherIt = other.meetingList.iterator(); it.hasNext();) {
            	Meeting meeting = it.next();
            	Meeting otherMeeting = otherIt.next();
                // Notice: we don't use equals()
                if (!meeting.solutionEquals(otherMeeting)) {
                    return false;
                }
            }
            return true;
        }
    }

}
