
import java.time.LocalDateTime;

public class Submission {
    private static int nextSubmissionNumber;
    private int number;
    private String sourceCode;
    private LocalDateTime upload;
    private String status;
    private Contest contest;
    private Problem problem;
	
	public Submission(  String code,  Contest  c,Problem p){
		this.sourceCode = code;
		this.contest = c;
		this.problem = p;
		
		addSubmission( );
		
	}
	
	public void addSubmission( ){
		addSubmission(  this );
		
	}
	
	

}
