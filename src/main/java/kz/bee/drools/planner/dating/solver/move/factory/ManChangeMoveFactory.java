package kz.bee.drools.planner.dating.solver.move.factory;

import java.util.ArrayList;
import java.util.List;

import kz.bee.drools.planner.dating.Dating;
import kz.bee.drools.planner.dating.Man;
import kz.bee.drools.planner.dating.Meeting;
import kz.bee.drools.planner.dating.solver.move.ManChangeMove;

import org.drools.planner.core.move.Move;
import org.drools.planner.core.move.factory.CachedMoveFactory;
import org.drools.planner.core.solution.Solution;

public class ManChangeMoveFactory extends CachedMoveFactory {

	@Override
	public List<Move> createCachedMoveList(Solution solution) {
		
		Dating dating = (Dating) solution;
        List<Move> moveList = new ArrayList<Move>();
        for (Meeting meeting : dating.getMeetingList()) {
            for (Man toMan : dating.getManList()) {
                moveList.add(new ManChangeMove(meeting, toMan));
            }
        }
        
		return moveList;
	}

}
