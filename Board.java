public class Board {
    
    private int green, yellow, orange;

    // BOARD CONSTRUCTOR
    public Board() {
        green = 3;
        yellow = 7;
        orange = 5;
    }
    
    /*
     * getBoard()
     * prints out board 
     */
    public void getBoard() {
        System.out.println("----------");
        System.out.println("Green: " + green + "\nYellow: " + yellow + "\nOrange: " + orange);
        System.out.println("----------");
    } // end getBoard

    /*
     * amt(String color)
     * returns number of that color
     */
    public int amt(String color) {
        switch (color) {
            case "green":   return green;
            case "yellow":  return yellow;
            case "orange":  return orange;
            default:        return 0;
        }
    } // end amt

    /*
     * setColor(String color, int number)
     * sets the color to a certain number of beads
     */
    public void setColor(String color, int number) {
        switch (color) {
            case "green":   green -= number;    break; 
            case "yellow":  yellow -= number;   break; 
            case "orange":  orange -= number;   break; 
        }
    } // end setColor

    
}