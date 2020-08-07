
import java.time.Duration;
import java.util.HashMap;

public class Problem {
    private String name;
    private int score;
    private Duration executionLimit;
    private String topic;
    private HashMap<Integer, Test> tests;
    private HashMap<Integer, Submission> submissions;
	
	public void addSubmission(String key, Submission s){
		submissions.put( key, s);
		
	}

}
