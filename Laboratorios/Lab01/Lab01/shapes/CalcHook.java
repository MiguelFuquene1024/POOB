import java.util.ArrayList;
import java.lang.Math;
import javax.swing.JOptionPane;

/**
 * Write a description of class CalcHook here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CalcHook{
    // instance variables - replace the example below with your own
    private ArrayList<Xook> listOfNumbers;
    private Xook numM;
    private int cases = 0;
    private String colorN;
    /**
     * Constructor for objects of class CalcHook
     */
    public CalcHook( String color  )    {
        colorN = color;
        listOfNumbers = new ArrayList();
    }

    /**
     * allows you to set the color in which the Mayan numbers 
     * will be painted on the calculator and then paint them
     *
     * @param  input
     * 
     */
    public void addNumber( int input)    {
        // put your code here
        
        if ( input >= 0 ){
            numM = new Xook( input );
            

            listOfNumbers.add( numM );
          
            numM.draw( );
            numM.changeColor( colorN );
            
            numM.moveHorizontal( 110 * cases );
            
        
        
            cases++;
        }
        else{
            JOptionPane.showMessageDialog( null, "Debe ingresar un número positivo. " );
        }
        
        
    }
    /**Allows to show an error to the user in case there are less
     * than 2 numbers in the stack and you want to do an operation 
     * 
     */
    private void errorLength(){
        JOptionPane.showMessageDialog( null, "Deben existir al menos 2 números para realizar la operacion. " );
        
        
    }
    /**Allows the last Mayan number to be removed from a stack
     * 
     */
   private Xook pop(){
        int index,current ;
        index = listOfNumbers.size() - 1;
        
        listOfNumbers.get(index).makeInvisible();
        
        return listOfNumbers.remove( index  );
            
        
        
    }
    /**It allows to make the sum of the last two Mayan numbers 
     * that are in the pile
     * 
     */
    public void sum( ){
        int  num1, num2;
        if ( listOfNumbers.size() >= 2 ){
            cases = cases - 2 ;
            num1 = pop().getVaLue();
            num2 = pop().getVaLue();
            
            numM = new Xook( num1 + num2 );
            numM.changeColor( colorN );
            addNumber( numM.getVaLue() );
            numM.draw( );
            
            
        }
        
        else{
            errorLength();
        }
        
    }
    /**It allows to make the substract of the last two Mayan numbers 
     * that are in the pile
     * 
     */
    public void substract( ){
        int  num1, num2;
        if ( listOfNumbers.size() >= 2 ){
            cases= cases - 2;
            num1 = pop().getVaLue();
            num2 = pop().getVaLue();
            
            numM = new Xook( num1 - num2 );
            numM.changeColor( colorN );
            addNumber( numM.getVaLue() );
            numM.draw( );
            
            
        }
        
        
        else{
            errorLength();
        }
        
        
    }
    /**It allows to make the multiplication of the last two Mayan
     * numbersthat are in the pile
     * 
     */
    public void mult( ){
        int  num1, num2;
        if ( listOfNumbers.size() >= 2 ){
            cases= cases - 2;
            num1 = pop().getVaLue();
            num2 = pop().getVaLue();
            
            numM = new Xook( num1 * num2 ) ;
            numM.changeColor( colorN );
            listOfNumbers.add( numM );
            numM.draw( );
            

        }
        
        else{
            errorLength();
        }
        
    }
    /**Allows the division of the last two Mayan numbers in the 
     * stack.
     * 
     */
    public void divI( ){
        int  num1, num2;
        if ( listOfNumbers.size() >= 2 ){
            cases= cases - 2;
            num1 = pop().getVaLue();
            num2 = pop().getVaLue();
            
            numM = new Xook(  num2 / num1 ) ;
            numM.changeColor( colorN );
            listOfNumbers.add( numM );
            numM.draw( );
           
            
        }
        
        else{
            errorLength();
        }
        
    }
    /**Allows to make invisible the first n Mayan numbers in the
     * stack that the user wants
     * 
     */
    public void makeInvisible( int times ){
        int i ;
       
        if ( 0 < times &&  times   <= listOfNumbers.size() ){
            
            for ( i = 0 ; i < times ; i++ ){
            listOfNumbers.get( i ).makeInvisible( );
            
            }
        }
        
        else{
            JOptionPane.showMessageDialog( null, "El numero de indicado debe ser minimo cero y maximo la cantidad de números encolados." );;
        }
        
        
        
        
    
    
    }
    /**allows you to perform the power operation between the last
     * two Mayan numbers in the stack.
     * 
     */
    public void powN(  ){
        int num1 , num2;
        if ( listOfNumbers.size() >= 2 ){
            cases= cases - 2;
            num1 = pop().getVaLue();
            num2 = pop().getVaLue();
            
            numM =  new Xook( (int ) Math.pow( num2,  num1 ) );
            listOfNumbers.add( numM );
            numM.draw( );
            
            
            
        }
        
    }
    
    
    
    
}
