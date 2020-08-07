
public class ECIJudgeException extends Exception{

    public static final String  PENDING_SUBMMISIONS  String= "hay submissions pendientes";
    public static final String  ALL_SUBMMISIONS_REJECTED ="  ALL_SUBMMISIONS_REJECTED ";
    public static final CONTEST_IN_PROGRESS="la competencia no a acabado";
    public static final String  TIMEOUT="time limit";
    public ECIJudgeException(String message){
        super(message);
    }

}
