import java.util.Scanner; 
import java.util.Random; 

public class Nim extends Board {

    static Board b = new Board();

     /************************** MAIN **************************/
    public static void main(String args[]) {

        Scanner fpScanner = new Scanner(System.in);     // create scanner

        System.out.println("Welcome to Double Trouble! \nIf you want to start, enter \"yes\"; otherwise, enter \"no\".");
        String fpString = fpScanner.nextLine(); 

        int fpInt = (fpString.equals("yes")) ? 0 : 1; // set the starting player (0: player starts, 1: computer starts)

        runGame(fpInt);        // run the game

        fpScanner.close();
    }

    /*
     * public static void RunRound(int firstPlayer)
     * Input: int for the person who is playing (0 for player, 1 for computer)
     * Output: Runs a round, no output
    */

    public static void runGame(int player) {

        int currentPlayer = player;         // store current player

        b.getBoard();

        while (!checkWinner()) {

            if (currentPlayer == 0) {           // player's turn

                System.out.println("YOUR TURN!");
                playerMechanics();

                // switch sides
                currentPlayer = 1;

            } else {                            // computer's turn

                System.out.println("COMPUTER'S TURN!");
                nimMechanics();

                // switch sides
                currentPlayer = 0;
            }

            b.getBoard();

        }

        System.out.println("The winner is... ");
        System.out.println((currentPlayer == 0 ? "the computer! Better luck next time!" : "you! Great job!"));

    }

     /*
     * public static void nimMechanics()
     * Output: runs mechanics for nim and removes beads from the pile optimally
    */

    public static void nimMechanics() {

        int nimSum = b.amt("green") ^ b.amt("yellow") ^ b.amt("orange");


        int lastRound = (b.amt("green") + b.amt("yellow") + b.amt("orange") == 1) ?  1 : 0;

        if (lastRound == 1) {
            if (b.amt("green") == 1) {
                b.setColor("green", 1);
            } else if (b.amt("yellow") == 1) {
                b.setColor("yellow", 1);
            } else if (b.amt("orange") == 1) {
                b.setColor("orange", 1);
            }
        }



        if (nimSum == 0) {         // winning move does not exist, random color and number removed

            Random rand = new Random();

            String rColor = getRandomColor();
            int rNum = rand.nextInt(b.amt(rColor)) + 1;

            // print out results and the new board 
            System.out.println(rNum + " " + rColor + (rNum == 1 ? " bead was " : " beads were ") + "taken out.");
            
            b.setColor(rColor, rNum);

        } else {                   // winning move exists, number removed to make the nim sum 0

            int nimGreen = b.amt("green");
            int nimYellow = b.amt("yellow");
            int nimOrange = b.amt("orange");

            if ((nimOrange < (nimGreen ^ nimYellow)) && 
                (nimOrange - (nimOrange - (nimGreen ^ nimYellow))) >= 0) {

                b.setColor("orange", nimOrange - (nimOrange - (nimGreen ^ nimYellow)));

            } else if (nimYellow > (nimGreen ^ nimOrange) && 
                      (nimYellow - (nimYellow - (nimGreen ^ nimOrange))) >= 0) {

                b.setColor("yellow", nimYellow - (nimYellow - (nimGreen ^ nimOrange)));

            } else if ((nimGreen > (nimYellow ^ nimOrange)) && 
                       (nimGreen - (nimGreen - (nimYellow ^ nimOrange))) >= 0) {

                b.setColor("orange", nimGreen - (nimGreen - (nimYellow ^ nimOrange)));
            } else {                        // no optimal move: move random

            Random rand = new Random();

            String rColor = getRandomColor();
            int rNum = rand.nextInt(b.amt(rColor)) + 1;

            // print out results and the new board 
            System.out.println(rNum + " " + rColor + (rNum == 1 ? " bead was " : " beads were ") + "taken out.");
            
            b.setColor(rColor, rNum);
            }
        }
    }
    
    /*
     * public static void playerMechanics()
     * run player's turn
    */

    public static void playerMechanics() {

        String rColor;      // round color
        int rNum;           // round number

        // get player inputs 
        Scanner userInput = new Scanner(System.in);
                
        // color 
        System.out.println("Which color would you like to take from? (green, yellow, or orange)");
        rColor = userInput.nextLine(); 
        
        while (b.amt(rColor) == 0) {          // check that the input is valid
            System.out.println("There are no more " + rColor + " beads left. Please choose another color!");
            rColor = userInput.nextLine();
        }

        System.out.println("There are " + b.amt(rColor) + " " + rColor + " beads left.");

        // number 
        System.out.println("How many beads would you like to take?");
        rNum = userInput.nextInt();

        while (rNum > b.amt(rColor)) {      // check that the input is valid
            System.out.println("Please enter a number less than or equal to " + b.amt(rColor) + "!");
            rNum = userInput.nextInt();
        }

        // print out results and the new board 
        System.out.println(rNum + " " + rColor + (rNum == 1 ? " bead was " : " beads were ") + "taken out.");
            
        b.setColor(rColor, rNum);

    }

    /*
     * public static String getRandomColor()
     * return a random available color
    */

    public static String getRandomColor() {
        
        String rColor;

        // get a random color
        Random rand = new Random();

        String[] colorArr = {"green","yellow","orange"};

        rColor = colorArr[rand.nextInt(colorArr.length)];      // choose color from array

        while (b.amt(rColor) == 0) {                           // if there are no more, pick another
            rColor = colorArr[rand.nextInt(colorArr.length)];
        }

        return rColor;
    }

    /*
     * public boolean checkNim(int nimColor, int nimSum, String color)
     * check if we can remove nim sum
    */
    public static boolean checkNim(int nimColor, int nimSum, String color) {
        return ((nimColor ^ nimSum) < nimSum && nimColor < b.amt(color));
    }

    /*
     * public static boolean checkWinner()
     * checks if all colors sum to 0
     * Output: Returns true if there is a winner and false if there isn't one yet
    */

    public static boolean checkWinner() {       
        return b.amt("green") + b.amt("yellow") + b.amt("orange") == 0 ? true : false;
    }

}


