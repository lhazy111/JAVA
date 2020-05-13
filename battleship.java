
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
public class battleship {
    //---------------------M A I N --------------------------------------------------
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String again;// play again answer
        int[][] board1 = new int[10][10];
        char[] signs  = {' ', '@','-','X','!', ' ' };
        System.out.println("****** Welcome to Battle Ships game by L.Hazy ****** ");
        System.out.println (signs[1]);
        //---------------------GAME LOOP--------------------------------------------
        do {
            System.out.println("Right now the sea is empty ;-) ");
            board(board1,signs);//empty game board
            playerShips(board1);
            board(board1,signs);
            compShips(board1);
            board(board1,signs);
            gameOn(board1,signs);
            System.out.println("Play again?(y/n)");
            again = input.next();
            clearBoard(board1);
        } while (again.equals("y"));
    }

    //--------------drawing board---------------------------------------------------
    public static void board(int[][] board1, char[]signs) {
        System.out.println ("================================================================================");
        System.out.print("  ");

        for (int i = 0; i < 10; i++) {
            System.out.print(i);
        }
        System.out.println();
        for (int row = 0; row < 10; row++) {
            System.out.print(row + "|");
            for (int col = 0; col < 10; col++) {
                System.out.print (signs[board1[row][col]]);
            }
            System.out.print("|" + row);
//------------------------------symbols description----------------------------------------------
            if (row==1) {
                System.out.println ("     @ - your ship");
            }else if (row==2) {
                System.out.println ("     X - your sunken ship");
            }else if (row==3) {
                System.out.println ("     ! - computer's sunken ship");
            }else if (row==4) {
                System.out.println ("     - - these coordinates have been used");
            }else System.out.println();
//------------------------------------------------------------------------------------------------
        }
        System.out.print("  ");
        for (int i = 0; i < 10; i++) {
            System.out.print(i);
        }
        System.out.println();
    }

    //------------------------------PLAYER'S SHIPS-------------------------------------------
    public static void playerShips(int[][] board1) {
        Scanner input = new Scanner(System.in);
        System.out.println("Now You are gonna choose your ships position");
        for (int i = 1; i <= 5; i++) {
            System.out.print("Enter X coordinate for your ship nr " + i + " : ");
            int x = input.nextInt();
            System.out.print("Enter Y coordinate for your ship nr " + i + " : ");
            int y = input.nextInt();
            //----------------checking coordinates proper range-------------
            if (invalidCoordinates(x,y)) {
                System.out.println(">>>>Wrong coordinates. Both must be 0-9. Try again<<<<<");
                i--;
                //-----------------checking if the place is occupied ------------
            } else if (board1[y][x] == 1) {
                System.out.println("You've already put a ship at x" + x + " y" + y);
                i--;
            }
            //-----------------if everything is OK --------------------------
            else board1[y][x] = 1;
        }
    }


    // ------------------------------COMPUTER'S SHIPS--------------------------------------------------
    public static void compShips(int[][] board1) {
        System.out.println("Now The Computer is about to deploy ships. Hold on...");
        for (int i = 1; i <= 5; i++) {
            int rndX = randomizer();
            int rndY = randomizer();
           //System.out.println(rndX + " " + rndY);
            if (board1[rndY][rndX] != 0) {
                System.out.println("Position occupied");
                i--;
            } else board1[rndY][rndX] = 5;
            System.out.println("Ship nr " + i + " sucessfully deployed");

        }
    }

    //-----------------------------G A M E   O N --------------------------------------------
    public static void gameOn(int[][] board1, char[]signs) {
        int compShips = 5;
        int playerShips = 5;
        int x,y ;
        Scanner input = new Scanner(System.in);
        System.out.println("Now it's time to start the G A M E !!!");
     //---------------------player's move---------------------------------------------------
        do {
            System.out.println("Your turn.");
            do  {
            System.out.print("Enter X coordinate : ");
            x = input.nextInt();
            System.out.print("Enter Y coordinate : ");
            y = input.nextInt();
            if (invalidCoordinates(x,y)) {
                System.out.println (">>>>Both must be 0-9. Try again.<<<<<<");
            }
            } while (invalidCoordinates(x,y));
            if (board1[y][x] == 0) {
                System.out.println("You missed !");
                board1[y][x] = 2;
            } else if (board1[y][x] == 1) {
                System.out.println("Oh no, you sunk your own ship :(");
                board1[y][x] = 3;
                playerShips--;
            } else if (board1[y][x] == 5) {
                System.out.println("Boom! You sunk the ship!");
                board1[y][x] = 4;
                compShips--;
            } else System.out.println ("Hahaha. You are losing ammo. These coordinates have already been used.");
     //-----------------------------Computer's move -------------------------------------------------------------
            int rndX;
            int rndY;
            do {
                rndX = randomizer();
                rndY = randomizer();
                //System.out.println(rndX + " " + rndY);
            } while (board1[rndY][rndX] == 10 || board1[rndY][rndX] == 11 || board1[rndY][rndX] == 12);
            if (board1[rndY][rndX] == 0) {
                System.out.println("Computer missed choosing X" + rndX + " Y" + rndY);
                board1[rndY][rndX] = 2 ;
            } else if (board1[rndY][rndX] == 1) {
                System.out.println("Computer sunk your ship at X" + rndX + " Y" + rndY);
                board1[rndY][rndX] = 3;
                playerShips--;
            } else if (board1[rndY][rndX] == 5) {
                System.out.println("Lucky You ! Computer sunk its own ship at X" + rndX + " Y" + rndY);
                board1[rndY][rndX] = 4;
                compShips--;

            }
            board(board1,signs);
            System.out.println("Your Ships: " + playerShips + "  Computer Ships: " + compShips);
        } while (compShips != 0 && playerShips != 0);
    //-----------------------end of the game ---------------------------------------------------
        if (compShips == 0) {
            System.out.println("HOORRRRAY. YOU WIN !!!!");
        } else {
            System.out.println("Sorry ! Computer wins");
        }
    }
//----------------------------wrong coordinates---------------------------------------------------
    public static boolean invalidCoordinates (int x, int y) {
        return (x<0 || x>9 || y<0 || y>9);
    }

    //-------------------------------------clearing the board for the next game ------------------
    public static void clearBoard(int[][] board1) {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                board1[row][col] = 0;
            }
        }

    }
    public static int randomizer() {
        Random rand = new Random();
        int rnd = rand.nextInt(10);
        return rnd;
    }

}