package kz.bee.drools.planner.dating;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.xml.DOMConfigurator;
import org.drools.planner.config.XmlSolverConfigurer;
import org.drools.planner.core.Solver;
import org.drools.planner.core.event.BestSolutionChangedEvent;
import org.drools.planner.core.event.SolverEventListener;
import org.drools.planner.core.solution.Solution;
import org.drools.planner.core.solver.DefaultSolverScope;
import org.slf4j.LoggerFactory;


/**
 * @author Nurlan Rakhimzhanov
 *
 */

public class DatingMain {

	private static final int PERSON_PER_GENDER = 10;

	private static final int HOUR_COUNT = 5;

	private static final int TABLE_COUNT = 5;

	public static final String SOLVER_CONFIG
    	= "/datingSolverConfig.xml";
	
	private volatile Solver solver;
    
    public DatingMain() {
        DOMConfigurator.configure(getClass().getResource("/log4j-test.xml"));
	}
    
	public static void main(String[] args) {
		DatingMain datingMain = new DatingMain();
		datingMain.init();
		datingMain.start();
	}
	
	private void init() {
		XmlSolverConfigurer configurer = new XmlSolverConfigurer();
		configurer.configure(SOLVER_CONFIG);
		solver = configurer.buildSolver();
		this.solver.addEventListener( new SolverEventListener() {
		    public void bestSolutionChanged(BestSolutionChangedEvent event) {
		        Dating dating = (Dating) solver.getBestSolution();
		        
		        print(dating);
		    }
		
		});
		setPlanningProblem();
	}

	private void start() {
		System.out.println("Start solving ...");
		this.solver.solve();
		
		Dating dating = (Dating) solver.getBestSolution();
		
		
		System.out.println("Best solution:");

		print(dating);
        
		System.out.println("End solving.");
	}
	
	private void setPlanningProblem() {
		List<Table> tableList = new ArrayList<Table>();
		List<Hour> hourList = new ArrayList<Hour>();
		List<Man> manList = new ArrayList<Man>();
		List<Woman> womanList = new ArrayList<Woman>();
		List<Meeting> meetingList = new ArrayList<Meeting>();
		
		
		for( int i = 0; i < TABLE_COUNT; i++ ) {
			Table t = new Table();
			t.setId(Long.parseLong(""+i));
			t.setNumber(i+10);
			
			tableList.add(t);
		}
		
		for( int i = 0; i < HOUR_COUNT; i++ ) {
			Hour h = new Hour();
			h.setId(Long.parseLong(""+i));
			h.setValue(i+1);
			
			hourList.add(h);
		}

		
		for( int j = 0; j < PERSON_PER_GENDER; j++ ) {
			Man m = new Man();
			m.setId(Long.parseLong(""+j));
			m.setName("Mr. #"+(j+1));
			
			Woman w = new Woman();
			w.setId(Long.parseLong(""+j));
			w.setName("Ms. #"+(j+1));
			
			manList.add(m);
			womanList.add(w);
		}
		
		for( int j = 0; j < hourList.size(); j++ ) {
			for( int i = 0; i < tableList.size(); i++ ) {
				Meeting m = new Meeting();
				m.setId(Long.parseLong(""+(j*hourList.size() + i)));
				m.setTable(tableList.get(i));
				m.setHour(hourList.get(j));
				m.setMan(manList.get(1));
				m.setWoman(womanList.get(1));
				
				meetingList.add(m);
			}
		}
		
		Dating dating = new Dating();
		dating.setId(Long.parseLong("1"));
		dating.setN(25);
		dating.setTableList(tableList);
		dating.setHourList(hourList);
		dating.setManList(manList);
		dating.setWomanList(womanList);
		dating.setMeetingList(meetingList);
		
        this.solver.setPlanningProblem((Solution) dating);
	}
	
	private void print(Dating dating) {
		List<Meeting> meetingList = dating.getMeetingList();
        
        System.out.println("===============================");
        for(Meeting m : meetingList) {
        	System.out.printf("# %3s ",m.getId());
        	System.out.println(" t:" + m.getTable().getNumber() + " h:"+ m.getHour().getValue() + " m:"+m.getMan() + " w:" + m.getWoman());
        }
        System.out.println("Score: " + dating.getScore().getScore() + " time: " + solver.getTimeMillisSpend() );
        
        System.out.println("===============================");
	}
}
