
import java.util.Random;
import java.util.ArrayList;

/**
 * allows you to transform an integer as we know it into a Mayan 
 * number and draw it.
 *
 * @author (Ivan Camilo y Miguel Fuquene )
 * @version 1.0 29/02/2020
 */
public class Xook{
    // instance variables - replace the example below with your own
    private int number;
    
    private int circles; 
    private int sticks;
    
    private int xPositionR;
    private int yPositionR;
    
    private int xPositionC;
    private int yPositionC;
    
    private int xPositionT;
    private int yPositionT;
    
    private String color;
    private Random random;
    
    private ArrayList<Triangle> triangulos;
    private ArrayList<Rectangle> rectangulos;    
    private ArrayList<Circle>  circulos;   
    
    private boolean isVisible;
    

    

    /**
     * Constructor for objects of class Xook which has the initi
     * al values of the positions where the Mayan number will be
     * drawn
     */
    
    
    
    public Xook( int input ){
        number = input;
        
        xPositionR = 50;
        yPositionR = 225;
        
        
        xPositionC = 50;
        yPositionC = 200;
       
        isVisible =  false;

        circulos = new ArrayList();
        triangulos = new ArrayList();
        rectangulos = new ArrayList();
        
        
    }
    
    /**
       returns the value of the number established in the constructor
       */
    public int  getVaLue(){
        return number;
          
    }
    
    /**
       *sets the main number between 0 and 100
       */
    public void random(){
        int div , mod = 0;
        
        Random random = new Random();
        setValue ( random.nextInt( 100 ) );
       
        makeInvisible();
        
        draw();
        
        xPositionR = 50;
        yPositionR = 225;
        
        xPositionC = 50;
        yPositionC = 200;

    }
    
    
    /**
       dibuja el numero ingresado en maya
       @param integer number to draw
       */
    public void draw( int numero){ 
        int x;
       
        circles = numero % 5;
        sticks = numero / 5 ;
        switch ( circles ){
            case 1:
                xPositionC = 90;
                break;
            case 2:
                xPositionC = 70;
                break;
            case 3 :
                xPositionC = 70;
                break;
            
            default:
                xPositionC = 60;
                break;
        }
                
    
            
            
        if ( numero == 0 ){
                Triangle cero = new Triangle();
                triangulos.add( cero );
                
                cero.moveVertical( yPositionT ) ;
                cero.makeVisible();
   
                
                
            }  
            
        for ( x = 0; x < sticks ; x++ ){
            Rectangle rect = new Rectangle();
            rectangulos.add( rect );
            
            
            rect.moveHorizontal( xPositionR );
            rect.moveVertical( yPositionR );
 
            rect.makeVisible();
                
            yPositionR = yPositionR + 15;
                
                
                
                

        }
            
            
               
            
            
        for ( x = 0 ; x  < circles ; x++ ){
            Circle circle = new Circle();
            circulos.add( circle );
            
            circle.moveHorizontal( xPositionC );
            circle.moveVertical( yPositionC );
           
            circle.makeVisible();
            xPositionC+= 80 / circles ;
   
        }
    }
    
    /**allows to draw each one of the figures that conform the 
     * Mayan number and to locate it according to the 
     * 
     */
    
    public void draw(){
        int  expo = 0 ,div = number, mod = -1 ;
        
        

        if ( isVisible == false ){
            
            if ( number == 0){
                draw( 0 );
            }
            
            isVisible = true;
            
            while ( div != 0  ){
            
                mod = div %  20;
              
                
                
                draw( mod );
                
                yPositionT = yPositionT - 2*50;
                yPositionR = yPositionR - 2*50;
                yPositionC = yPositionC - 2*50;
                
                
                
                div =  div / 20;
            }
        }

    }

    
    /** allows you to make the Mayan number invisible on the canvas
     * 
     */
    public void makeInvisible(){
        int i;
        if (isVisible==true){
            
            for (i=0;i<circulos.size();i++){
                circulos.get(i).makeInvisible();
            }
            
            for (i=0;i<rectangulos.size();i++){
                rectangulos.get(i).makeInvisible();
            }
            
            for (i=0;i<triangulos.size();i++){
                triangulos.get(i).makeInvisible();
            }
            isVisible =false;
        }
        

    }
    
    /** allows you to make the Mayan number visible on the canvas
     * 
     */
    public void makeVisible(){
        int i;
        if (isVisible==false){
            
            for (i=0;i<circulos.size();i++){
                circulos.get(i).makeVisible();
            }
            
            for (i=0;i<rectangulos.size();i++){
                rectangulos.get(i).makeVisible();
            }
            
            for (i=0;i<triangulos.size();i++){
                triangulos.get(i).makeVisible();
            }
            isVisible = true;
        }
        

    }
    
    /** Allows you to change the color of the Mayan number
     * 
     */
    public void changeColor( String color ){
        int i;
        
        for ( i = 0 ; i < circulos.size() ; i++ ) {
            circulos.get( i ).changeColor( color );
        }
            
        for ( i = 0 ; i < rectangulos.size() ; i++ ){
            rectangulos.get( i ).changeColor( color );
        }
            
        for ( i = 0 ; i < triangulos.size() ; i++ ){
            triangulos.get( i ).changeColor( color );
        }
        
        
        

    }
    
    /**Allows to move horizontally the Mayan number on the canvas
     * 
     */
    public void moveHorizontal( int dist ){
        int i;
        
        for (i=0;i<circulos.size();i++){
            circulos.get(i).moveHorizontal(dist);
        }
            
        for (i=0;i<rectangulos.size();i++){
            rectangulos.get(i).moveHorizontal(dist);
        }
            
        for (i=0;i<triangulos.size();i++){
            triangulos.get(i).moveHorizontal(dist);
        }
        
        
        

    }
    /** Allows to show the Mayan number that has been drawn
     * 
     */
    public void setValue( int input ){
        number = input;
    }
}
 
    
    

