
import java.util.Scanner;
/**
 * Clase auxiliar para la ejecucicon de Maze
 * @author Reyes Ramos Luz María. 318211073
 * @version 1.0 Noviembre 2021
 * @since EDD-2022-1 Lab
 */
public class Main {
    private  Scanner sc = new Scanner(System.in);
    private String opciones = " \n\t\t W E L C O M E!! \\(0w0)/\n" +
    "\n\t What do u wanna do? (.-.): \n:"+
    "\t1.- Solve maze A. "+
    "\n\t2.- Solve maze B.\n" +
    "\t3.- Exit (u.u)\n"+
    "\tPick your option (O_o):";

    private  String start = ".-./";

    
    public void repertorio(){
        String directory ="Laberintos/";
        int[] inicioC;
        int option = 0;
        int opDefA =0;
        int opDefB =0;
        int[] inicioCB;
        while (true) {
            while(true){
                System.out.println(opciones);
                try {
                    option = Integer.parseInt(sc.nextLine());
                    switch (option) {
                        case 1: //resolver lab A
                            Maze  labA = new Maze(directory+"LaberintoA.txt");
                            //default or user coordinates
                            opDefA = pickDef();
                            
                            if (opDefA == 1) { //default
                                labA.solve();
                                break;
                            }
                            
                            inicioC = askCoord();
                            //Que no sea pared
                            Box [][] board = labA.getBoard();
                            if(board[inicioC[0]][inicioC[1]].isWall()){
                                while (board[inicioC[0]][inicioC[1]].isWall()) {
                                    System.out.println("\n\t The selected coordinate is a wall, select another one :(.");
                                    inicioC = askCoord();
                                }
                            }
                            labA.setStartCoor(inicioC);
                            labA.setInicio(inicioC, start);
                            //default star coordinates (9,0)
                            System.out.println("\n\tYour start coordinate is:"+ board[inicioC[0]][inicioC[1]]);
                            labA.getBoard()[9][0].setBoxDraw("    ");
                            labA.solve();
                            break;
                        case 2:  // resolver lab B
                            Maze  labB = new Maze(directory+"LaberintoB.txt");
                            //default or user coordinates
                            opDefB = pickDef();
                            if (opDefB == 1) { //default
                                labB.solve();
                                break;
                            }
                            inicioCB = askCoord();
                            Box[][] boardB = labB.getBoard();
                            if(boardB[inicioCB[0]][inicioCB[1]].isWall()){
                                while (boardB[inicioCB[0]][inicioCB[1]].isWall()) {
                                    System.out.println("\n\t The selected coordinate is a wall, select another one :(.");
                                    inicioCB = askCoord();
                                }
                            }
                            labB.setStartCoor(inicioCB);
                            labB.setInicio(inicioCB, start);
                            //default star coordinates (9,0)
                            System.out.println("\n\tYour start coordinate is:"+ boardB[inicioCB[0]][inicioCB[1]]);
                            labB.getBoard()[9][0].setBoxDraw("    ");
                            labB.solve();
                            break;
                        case 3:
                            try {
                                System.out.println("\n\tExit....");
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                System.out.println("\n\tSomething went wrong :(.");
                            }
                            return;
                            
                        default:
                            System.out.println("\n\tThat option doesn't even exist O_o");
                    }       break;
                } catch (Exception e) {
                    System.out.println("\n\t Ivalid input, try again. The valid number range is [1-3] ");
                    sc.nextLine();
                }
            }
        }
        
    }

    private int[] askCoord(){
        int x = -1;
        int y = -1;
        int[] r = new int[2];
        while (true) {
            try {
                System.out.println("\n\t Start Coordinates; Type your input, range is from 0 to 20 (for both x and y):"+ "\n\tRow = ");
                x = Integer.parseInt(sc.nextLine());
                System.out.println( "\tColumn = ");
                y = Integer.parseInt(sc.nextLine());
                if(x>=0 && x<=20 &&  y>=0 && y<=20)
                    break;
                
            } catch (Exception e) {
                System.out.println("\n\t Ivalid input, try again. ");
                sc.nextLine();
            }
        }
        
        r[0] = x;
        r[1] = y;
        return r;
    }

    private int pickDef(){
        int op2 = 0;
        while (true) {
            try {
                System.out.println("\n\tSelect coordinates.\n\t\t1) Default \t\t2) My own coordinates. \n\tType an option:");
                op2 = Integer.parseInt(sc.nextLine());
                if(op2==2 || op2==1 )
                    return op2;
                
            } catch (Exception e) {
                System.out.println("\n\t Ivalid input, try again. ");
                sc.nextLine();
            }
        }
    
    }

    
    public static void main(String[] args) {
        Main m = new Main();
            m.repertorio();
        
       
    }
}
