import java.util.Random;
/**
 * Clase que modela una casilla para un laberinto.
 * @author Reyes Ramos Luz María, 318211073
 * @author .....
 * @version 1.0 Octubre 2021
 * @since EDD 2022-1 LAB
 */
public class Box {

    /**True si representa una pared, en otro caso representa una casilla
     * para buscar.
     */
    private boolean wall;

    /**True si la casilla fue visitada, en otro caso false */
    private boolean visited;

    /**Queue que almacena números del 0 al 3, insertados aleatoriamente */
    Queue<Integer> neighbors;

    /**Coordenada de una casilla */
    private int[] coordenada = new int[2];

    /**Representación de casilla */
    String drawBox;

    /**Lista con los elementos visitados */ //Auxiliar  para fillNeighbors
    List<Integer> visitedList;


    /*
     *  Datos de la implementación
     *  visited: Sólo se pueden visitar las casillas que no hayan sido visitadas antes y que no sean 
     *  paredes.
     * 
     *  neighbors: Cada número representará el orden en que se recorrerá al siguiente vecino si es
     *  posible (que no sea pared y que no haya sido visitado antes)
     *  1.- Arriba -->0 
     *  2.- Derecha -->1
     *  3.- Abajo --> 2
     *  4.- Izquierda -->3
     */


    /**
     * Contructor I para box: Auxiliar para ArrayReader.
     * @param wall valor booleano para pared
     */
    public Box(boolean wall){
        this.wall = wall;
    }

    /**
     * Constructo II para casilla.
     * @param row coordenada X, denota fila.
     * @param column coordenada Y, denota columna.
     * @param wall true si es pared,false en otro caso.
     */
    public Box(int row, int column, boolean wall){
        coordenada[0] = row;
        coordenada[1] = column;
        this.wall = wall;
        drawBox = (wall) ? "$$$$" : "    ";
        neighbors = fillNeighbors();
    }
 
    /**Constructor vacio, auxiliar para maze toString */
    public Box(){};


    /**
     * Permite saber si una casilla es pared o no.
     * @return true si la casilla es pared, false en otro caso.
     */
     public boolean isWall(){return wall;}
    
    /**
     * Permite saber si una casilla está visitada o no.
     * @return true si la casilla ya fue visitada, false en otro caso.
     */
     public boolean isVisited(){return  visited;}

    
    /**
     * Modifica si un vesino fue visitado o no.
     * @param visited  true si ya fue visitado, false en otro caso.
     */
    public void setVisited(boolean visited){this.visited = visited;}

    /**
     * Obtiene las coordenadas de una casilla
     * @return un arreglo de longitud dos con la coordenada de la casilla (x,y).
     */
    public int[] getCoordenada(){return coordenada;}

    /**
     * Modifica los valores de las coordenadas de la casilla
     * @param x fila
     * @param y columna
     */
    public void setCoordenada(int x, int y){
        this.coordenada[0]= x;
        this.coordenada[1]=y;

    }
    /**
     * Obtiene la representación de una casilla: $$$$ si es pared, en otro caso "    ".
     * @return representación de la casilla.
     */
    public String getDrawBox(){return drawBox;}

    /**
     * Modifica la representación de una casilla, especificamente si esta ya fue visitada.
     * @param boxDraw Nueva representación de la casilla.
     */
    public void setBoxDraw(String boxDraw){this.drawBox = boxDraw;}


    /**
     * Visita una casilla
     */
     public void visit(){
         setVisited(true);
         setBoxDraw(" +  ");
         
     }

    /**
     * Obtiene la siguiente casilla a visitar:
     * Toma al siguiente en salir de neighbors  y lo remueve de
     * queue.
     * @return el siguiente neighbor
     */
    public int getNextBox(){
        return (int) neighbors.first();
    }

    /**
     * Fill neighbors Queue with all possible options to move.
     * 0-3  where:
     *  Top -->0 
     *  Right -->1
     *  Bottom --> 2
     *  Left -->3
     */
    public Queue<Integer> fillNeighbors(){
        if(!isWall()){
            neighbors = new Queue<>();
            visitedList = new List<>();
            Random rn = new Random();
            int random  = rn.nextInt(4);
            for(int k = 0; k<4; k++){//Considerar los 4 posibles movimientos
                while(visitedList.contains(random))
                    random = rn.nextInt(4);
                visitedList.addBeg(random);
                neighbors.enqueue(random);
                
            }
            return neighbors;
        }
        return new Queue<>();
    }
    @Override
    public String toString(){
        return "("+ coordenada[0]+ ","+ coordenada[1]+ ")";
    }

    

    



    
}
