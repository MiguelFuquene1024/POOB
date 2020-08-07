
/**
 * Fraccionario
 * Esta clase implementa el tipo de dato Fraccionario; es decir, un n�mero racional que se pueden escribir de la forma p/q, donde p y q son enteros, con q <> 0
 * La implemantacion se hace mediante objetos inmutables
 * INV: El fraccionario se encuentra representado en forma irreductible.
 * @author E.C.I.
 *
 */

public class Fraccionario {

    private int numerador ;
    private int denominador ;
    private int mcd_;
    /**Calcula el maximo comun divisor de dos enteros
     * Lo implementaremos mediante el algoritmo recursivo
     * @param a primer entero
     * @param b segundo entero
     * @return el Maximo Comun Divisor de a y b
     */

    public static int mcd(int a,int b){
        int modulus;
        a  = ( a < 0 ) ? -a: a;
        b = ( b <  0 ) ? -b: b;

        if ( b == 0){ return a;}

        else if ( a < b ){ 
            return mcd( b ,a );}

        return mcd( b , a % b);
    }  

    /**Crea un nuevo fraccionario, dado el numerador y el denominador
     * @param numerador
     * @param denominador. denominador <> 0
     * 
     */
    public Fraccionario (int numerador, int denominador) {

        this.numerador = ( denominador <  0 ) ? -numerador: numerador;
        this.denominador = ( denominador <  0 ) ? -denominador: denominador;

        mcd_= mcd( this.numerador, this.denominador);

        this.numerador /= mcd_;
        this.denominador /= mcd_;

    }

    /**Crea un fraccionario correspondiente a un entero
     * @param entero el entero a crear
     */
    public Fraccionario (int entero) {
        this.numerador = entero;
        this.denominador = 1;
    }

    /**Crea un fraccionario, a partir de su representacion mixta. 
     * El numero creado es enteroMixto + numeradorMixto/denominadorMixto
     * @param enteroMixto la parte entera del numero
     * @param numeradorMixto el numerador de la parte fraccionaria
     * @param denominadorMixto el denominador de la parte fraccionaria. denominadorMixto<>0
     */
    public Fraccionario (int enteroMixto, int numeradorMixto, int denominadorMixto) {
        if ( denominadorMixto != 0 ){
            this.numerador = enteroMixto + numeradorMixto;
            this.denominador = denominadorMixto;
        }
    }

    
    /**
     * Un fraccionario p/q esta escrito en forma simplificada si q es un entero positivo y 
     * el maximo comun divisor de p y q es 1.
     * @return El numerador simplificado del fraccionario
     */

    public int numerador() {
        return this.numerador;
    }

    /**
     * Un fraccionario p/q esta escrito en forma simplificada si q es un entero Positivo y 
     * el maximo comun divisor de p y q es 1.
     * @return el denominador simplificado del fraccionario
     */
    public int denominador() {
        return this.denominador;
    }

    /**Suma este fraccionario con otro fraccionario
     * @param otro es otro fraccionario
     * @return este+otro
     */
    public Fraccionario sume (Fraccionario otro, int type ) {
        int calculo1, calculo2, numerador,denominador;
        Fraccionario answ;

        if (  denominador() == otro.denominador() ){
            numerador = numerador() + otro.numerador() ;
            denominador = denominador();

            answ = new Fraccionario( numerador , denominador); 

        }
        else{
            calculo1 = this.numerador * otro.denominador();
            calculo2 = this.denominador * otro.numerador();

            numerador = ( type == 0 ) ? calculo1 + calculo2 : calculo1 - calculo2;
            denominador = this.denominador * otro.denominador();

            answ = new Fraccionario( numerador , denominador); 
        }

        return answ;
    }
    
    /**Resta este fraccionario con otro fraccionario
     * @param otro es otro fraccionario
     * @return este-otro
     */
    public Fraccionario substract (Fraccionario otro) {
        return sume(otro, 1 );
    }

    /**Multiplica este fraccionario con otro fraccionario
     * @param otro El otro fraccionario
     * @return este * otro
     */
    public Fraccionario multiplique (Fraccionario otro) {
        int numerador,denominador;
        numerador=this.numerador * otro.numerador();
        denominador=this.denominador * otro.denominador();

        Fraccionario fraccionario= new Fraccionario( numerador, denominador);
        return fraccionario;

    }

    /**Divide este fraccionario sobre otro fraccionario
     * @param otro El otro fraccionario
     * @return este * otro
     */
    public Fraccionario divida (Fraccionario otro) {
        int numerador,denominador;
        numerador=this.numerador * otro.denominador();
        denominador=this.denominador * otro.numerador();

        Fraccionario fraccionario= new Fraccionario( numerador, denominador);
        return fraccionario;
    }

    @Override
    
    
    public boolean equals(Object obj) {
        boolean equal;

        equal = ( obj == null || getClass() != obj.getClass() )? false: true;

        return equal;
    }    

    /**Compara este fraccionario con otro fraccionario
     * @param otro eL otro fraccionario
     * @return true si este fraccionario es igual matematicamente al otro fraccionario, False d.l.c.
     */
    public boolean equals (Fraccionario otro) {
        boolean equal;

        equal = ( this.numerador == otro.numerador() ) ? true : false ;
        equal = ( this.denominador == otro.denominador() ) ? true && equal: false ;

        return equal;
    }

    /** Calcula la representacion en cadena de un fraccionario en formato mixto simplificado
     * @see java.lang.Object#toString(java.lang.Object)
     */
    @Override
    public String toString() {   
        String answ = Integer.toString( this.numerador )+"/"+Integer.toString( this.denominador );

        return answ;
    }

}
