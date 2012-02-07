package kz.bee.drools.planner.dating.solver.move;

import java.util.Collection;
import java.util.Collections;

import kz.bee.drools.planner.dating.Meeting;
import kz.bee.drools.planner.dating.Woman;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.drools.FactHandle;
import org.drools.WorkingMemory;
import org.drools.planner.core.localsearch.decider.acceptor.tabu.TabuPropertyEnabled;
import org.drools.planner.core.move.Move;

public class WomanChangeMove implements Move, TabuPropertyEnabled {

	private Meeting meeting;
	private Woman toWoman;
	
	public WomanChangeMove(Meeting meeting, Woman toWoman) {
		this.meeting = meeting;
		this.toWoman = toWoman;
	}

	public Collection<? extends Object> getTabuProperties() {
		return Collections.singletonList(meeting);
	}

	public boolean isMoveDoable(WorkingMemory workingMemory) {
		return !ObjectUtils.equals(meeting.getWoman(), toWoman);
	}

	public Move createUndoMove(WorkingMemory workingMemory) {
		return new WomanChangeMove(meeting, meeting.getWoman());
	}

	public void doMove(WorkingMemory workingMemory) {
		FactHandle meetingHandle = workingMemory.getFactHandle(meeting);
        meeting.setWoman(toWoman);
        workingMemory.update(meetingHandle, meeting); // after changes are made
	}
	
	public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof WomanChangeMove) {
            WomanChangeMove other = (WomanChangeMove) o;
            return new EqualsBuilder()
                    .append(meeting, other.meeting)
                    .append(toWoman, other.toWoman)
                    .isEquals();
        } else {
            return false;
        }
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(meeting)
                .append(toWoman)
                .toHashCode();
    }

	@Override
	public String toString() {
		return "WomanChangeMove [meeting=" + meeting + ", toWoman=" + toWoman + "]";
	}

}
