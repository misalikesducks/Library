/**
 * @author Connie Chen
 * @author Tiffany Lee
 */
import java.util.StringTokenizer;
import java.util.Scanner;

public class Kiosk {
    // handles inputs and outputs
    public void run() {
       System.out.println("Library Kiosk running.");
       Scanner scan = new Scanner(System.in);

       boolean breakLoop = false;

       while(!breakLoop && scan.hasNextLine()) { // loops while there are inputs

            String input  = scan.nextLine();
            String[] arrOfInput = input.split(",", 0); //splits string by commas

            switch(arrOfInput[0]) {
               case "A": // add
                  System.out.println("Invalid Date!");
                  System.out.println("--name of book--" + " added to the Library.");
                  break;
               case "R": // remove
                  System.out.println("Unable to remove, the library does not have this book.");
                  System.out.println("Book# " + arrOfInput[1] + " removed.");
                  break;
               case "O": // check out
                  System.out.println("Book#" + arrOfInput[1] + " is not available.");
                  System.out.println("You've checked out Book#" + arrOfInput[1]);
                  break;
               case "I": // return
                  System.out.println("Unable to return Book#" + arrOfInput[1]);
                  System.out.println("Book#" + arrOfInput[1] + " return has been completed. Thanks!");
                  break;
               case "PA": // output the list of books to the console w/ the current sequence
                  System.out.println("**List of books in the library.");
                  // use the print() method from Library
                  System.out.println("**End of list");
                  break;
               case "PD": // output the list of books by the dates published in ascending order
                  System.out.println("**List of books by the dates published.");
                  // use the printbyDate() method from library
                  System.out.println("**End of list");
                  break;
               case "PN": // output the list of books by the book number in ascending order
                  System.out.println("**List of books by book numbers.");
                  System.out.println("**End of list");
                  break;
               case "Q":
                  breakLoop = true;
                  break;
               default: // invalid command or input
                  System.out.println("Invalid command!");
                  break;
            }
       }
       scan.close();

       System.out.println("Kiosk session ended.");
    }
}
