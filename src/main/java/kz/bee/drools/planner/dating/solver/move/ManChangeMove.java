package kz.bee.drools.planner.dating.solver.move;

import java.util.Collection;
import java.util.Collections;

import kz.bee.drools.planner.dating.Man;
import kz.bee.drools.planner.dating.Meeting;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.drools.FactHandle;
import org.drools.WorkingMemory;
import org.drools.planner.core.localsearch.decider.acceptor.tabu.TabuPropertyEnabled;
import org.drools.planner.core.move.Move;

public class ManChangeMove implements Move, TabuPropertyEnabled {

	private Meeting meeting;
	private Man toMan;
	
	public ManChangeMove(Meeting meeting, Man toMan) {
		this.meeting = meeting;
		this.toMan = toMan;
	}

	public Collection<? extends Object> getTabuProperties() {
		return Collections.singletonList(meeting);
	}

	public boolean isMoveDoable(WorkingMemory workingMemory) {
		return !ObjectUtils.equals(meeting.getMan(), toMan);
	}

	public Move createUndoMove(WorkingMemory workingMemory) {
		return new ManChangeMove(meeting, meeting.getMan());
	}

	public void doMove(WorkingMemory workingMemory) {
		FactHandle meetingHandle = workingMemory.getFactHandle(meeting);
        meeting.setMan(toMan);
        workingMemory.update(meetingHandle, meeting); // after changes are made
	}
	
	public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof ManChangeMove) {
            ManChangeMove other = (ManChangeMove) o;
            return new EqualsBuilder()
                    .append(meeting, other.meeting)
                    .append(toMan, other.toMan)
                    .isEquals();
        } else {
            return false;
        }
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(meeting)
                .append(toMan)
                .toHashCode();
    }

	@Override
	public String toString() {
		return "ManChangeMove [meeting=" + meeting + ", toMan=" + toMan + "]";
	}
}
