import java.util.ArrayList;

public class ECIJudge{
    private ArrayList<Contest> contests = new ArrayList<Contest>();
    
    public ECIJudge(){
    } 
    
    public List<String> getFinalScoreBoard( String contestName ) throws ECIJudgeException{
		for ( Contest c: contests ){
			if ( contestName.equals( Contest.getName() ) ){
				
				
				for ( Team t:teams  ){
					int numSubmissionsRejected = 0, totalSubmissions = 0;
					List<Submission> submissions = t.getSubmissions();
					LocalDateTime end = c.getFinalDate();
					
					for( Submission s: submissions ){
						
						if ( "Pending".equals( s.getStatus() ) ){
							throw new ECIJudgeException(ECIJudgeException.PENDING_SUBMMISIONS);
						}
						if ( s.getUpload() > end ){
							throw new ECIJudgeException(ECIJudgeException.CONTEST_IN_PROGRESS);
						}
						if ( "Rejected".equals( s.getStatus() ) ){
							numSubmissionsRejected++;
						}
						totalSubmissions++;
					}
					
					if ( numSubmissionsRejected == totalSubmissions ){
						throw new ECIJudgeException(ECIJudgeException.ALL_SUBMMISIONS_REJECTED);
					}
				}
				
				
				
				return 
				
			
				
			}
		}
		
        
    }
    
       
}
