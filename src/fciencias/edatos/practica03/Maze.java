import java.util.EmptyStackException;

/**
 * Clase que representa un laberinto.
 * @author Reyes Ramos Luz María, 318211073
 * @author -----
 * @version 1.0 Octubre 2021
 * @since EDD 2022-1 LAB
 */
public class Maze {

    /** Representación del tablero del laberinto; Almacena las casillas */
    private Box[][] mazeBoard;

    /**Casilla de inicio */ /* Determina el inicio del recorrido del laberinto, debe ser una casilla que no es pared */
    private Box inicio;

    /**Casilla de fin de recorrido  */ /* Determina la casilla objetivo o el final del laberinto,
     * cuando se encuentra un recorrido desde el inicio hasta esta casilla entonces el laberinto tiene solución.
     */
    private Box fin;
    
    /**Casila actual del recorrido */ /* Corresponde a la casilla del recorrido actual. Cuando se extiende un s, esta 
      *esta casilla es la que se actualiza, modificando el valor de la casilla actual después del cambio para s*/
    private Box actual;

    /**Representan las coordenadas de incio y fin de las casillas en mazeBoard */ //se le solicitaran al usuario
    private int[] coordInicio ={9,0}; // por default
    private int [] coordFin = { 9,20}; //CAMBIAR};
    

    /**Casilla siguiente para avanzar en maze */
    private Box moveNext;


    /** Pila de utilidad para solve*/
    Stack<Box> solving = new Stack<>();
    
    

    /**
     * Constructor del tablero del laberinto
     * @param file el archivo que contiene los datos del tablero.
     */
    public Maze (String file){
        ArrayReader ar = new ArrayReader();
        mazeBoard = ar.readMatrix(file); //si no instancio objeto da problemas D:
        inicio = mazeBoard[coordInicio[0]][coordInicio[1]];//por default
        actual =  inicio; //inicio = actual al principio del laberinto. 
        mazeBoard[coordInicio[0]][coordInicio[1]].setBoxDraw(".-./");
        fin = mazeBoard[coordFin[0]][coordFin[1]]; 
        mazeBoard[9][20].setBoxDraw("uwu/");
    }
    
    
    /**
     * Define si el laberinto está resuelto.
     * @return true si esta resuelto, false en otro caso.
     */
    public boolean isSolution(){
        return actual.getCoordenada()[0] == coordFin[0] && actual.getCoordenada()[1] == coordFin[1];
    }

    /**
     * Regresa true si la casilla actual tiene vecinos que verificar. El vecino proximo a 
     * verificar es aquel resultante del método dequeue() del atributo neighbors de la casilla actual.
     * @return true si actual si tiene vecinos por visitar, false en otro caso.
     */
    public boolean isExtensible(){ //thow nullpointer exception
        return actual.neighbors.size() != 0 && !isSolution();
    }
    public  String toString(){
        Box casilla = null;
        String drawing = "", coordY = "";
        for(int n =0 ; n<mazeBoard.length; n++){
            coordY = (n<9) ? coordY+ " "+  + n + "  "  : coordY +" " + n +" ";
            for(int k = 0; k<mazeBoard.length; k++){
                casilla = mazeBoard[n][k];
                drawing+= casilla.getDrawBox();
            }
            drawing+="  "+n + "\n";
        }
        return coordY+"\n"+drawing;
    }

    /**
     * Mueve la casilla actual a una casilla vecina que no sea pared y no haya sido visitada.
     * Esta casilla se elige a partir del método queue() --del atributo neighbors de la casilla actual.
     */
    public void extend(){
        //----MOVERSE -->Mover casilla actual
        int x = actual.getCoordenada()[0];
        int y = actual.getCoordenada()[1];
        try {
            do{
                switch (actual.getNextBox()) {
                    case 0: //Moverse derecha
                        moveNext = mazeBoard[x][y+1];
                        break;
                    case 1: //arriba
                        moveNext = mazeBoard[x+1][y];
                        break;
                    case 2://izquierda
                        if(y-1<0)
                            break;                        
                        moveNext = mazeBoard[x][y-1];
                        break;
                    case 3: //abajo
                        if(x-1<0)
                            break;
                        moveNext = mazeBoard[x-1][y];
                        break;
                    default:
                        System.out.println("NO OPCION VALIDA PARA MOVERSE");//tecnicamente no deberia entrar aquí :v
                        break;
                }
                actual.neighbors.dequeue();               
            }while((moveNext.isWall() || moveNext.isVisited()));
        } catch (NullPointerException | IndexOutOfBoundsException  e) {return;}
        actual = moveNext;
        actual.visit();
        solving.push(actual); 
    }



    /**
     * Solve this labyrinth.
     * @return an stack with all  solution coordinates .
     */
    public TDAStack<Box> solve(){
        // Inicializando maze
        actual.visit();
        solving.push(actual);
        moveNext = actual;   
        System.out.println("\n"+this); 
        //Backtracking
        try {
            while (!isSolution()){
                if(isExtensible())
                    extend();
                else{
                    actual.setBoxDraw("    ");
                    actual = solving.pop();
                }
            }
        } catch (EmptyStackException emse) {
            System.out.println("\t\tDAMN NO HAY SOLUCIÓN!! ¯\\(°_o)/¯");
            return solving;
        }
        mazeBoard[coordInicio[0]][coordInicio[1]].setBoxDraw(".-./");
        mazeBoard[coordFin[0]][coordFin[1]].setBoxDraw("\\uwu");
        System.out.println(this+ "\n\t\tS O L U  C I O N!! \\(owo)/");
        
        return stackSolution();
    }

    /**
     * Obtiene las coordenadas de incio del laberinto.
     * @return row, column.
     */
    public int[] getStartCoor(){return coordInicio;}

    /**
     * Modifica las coordenadas de incio para el laberinto.
     * @param coor Nuevas coordenadas de incio.
     */
    public void setStartCoor(int[] coor){coordInicio =  coor;}
    
    /**
    * Obtiene las coordenadas de fin del laberinto.
    * @return row, column.
    */
   public int[] getEndCoor(){return coordFin;}

   /**
    * Modifica las coordenadas de fin para el laberinto.
    * @param coor Nuevas coordenadas de fin.
    */
   public void setEndCoor(int[] coor){coordFin =  coor;}

   /**
    * Obtiene la casilla de incio.
    * @return casilla de inicio.
    */
   public Box getInicio(){return inicio;}

   /**
    * Realiza modificaciones en la casilla de inicio
    * @param coord Nuevas coordenadas.
    * @param draw Nueva representación de casilla.
    */
    public void setInicio(int[]coord, String draw){
        inicio.getCoordenada()[0] = coord[0];
        inicio.getCoordenada()[1] = coord[1];
        inicio.setBoxDraw(draw);
    }


    /**
    * Obtiene la casilla de fin.
    * @return casilla de inicio.
    */
   public Box getFin(){return fin;}

   /**
    * Realiza modificaciones en la casilla de fin.
    * @param coord Nuevas coordenadas.
    * @param draw Nueva representación de casilla.
    */
    public void setFin(int[]coord, String draw){
        fin.getCoordenada()[0] = coord[0];
        fin.getCoordenada()[1] = coord[1];
        fin.setBoxDraw(draw);
    }

    
    /**
     * Regresa el tablero de un laberinto
     * @return tablero.
     */
    public Box[][] getBoard(){
        return mazeBoard;
    }




    //----------- Métodos auxiliares: acondicionamiento presentacion de solución ----------
    
    /* Regresa la solucion desde inicio a final. */
    private TDAStack<Box> stackSolution(){
        Stack<Box> auxSolution = new Stack<>();
        auxSolution.push(solving.top());
        while(solving.size() !=0 ){
            try {
                auxSolution.push(solving.pop());
            } catch (NullPointerException | EmptyStackException  e) {return auxSolution;}
            
        }
        
        System.out.println("\n\tCoordenadas:\n"+ auxSolution);
        return auxSolution;
    }

    
    


}
