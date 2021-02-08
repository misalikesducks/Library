/**
 * @author Connie Chen
 * @author Tiffany Lee
 */
import java.util.StringTokenizer;
import java.util.Scanner;

public class Kiosk {
    public void run() { // handles inputs and outputs
       System.out.println("Library Kiosk running.");
       Scanner scan = new Scanner(System.in);

       boolean breakLoop = false;

       Library bag = new Library();

       while(!breakLoop && scan.hasNextLine()) { // loops while there are inputs

            String input  = scan.nextLine();
            String[] arrOfInput = input.split(",", 0); //splits string by commas

            switch(arrOfInput[0]) {
               case "A": // add
                  Date tempDate = new Date(arrOfInput[2]);
                  Book currentBook = new Book("" + bag.serialNumber, arrOfInput[1], false, tempDate);
                  if(!currentBook.getDatePublished().isValid())
                     System.out.println("Invalid Date!");
                  else {
                     bag.add(currentBook);
                     System.out.println(currentBook.getName() + " added to the Library.");
                  }
                  break;
               case "R": // remove
                  if(!bag.remove(bag.findFromNum(arrOfInput[1]))) // should i use find()
                     System.out.println("Unable to remove, the library does not have this book.");
                  else
                     System.out.println("Book# " + arrOfInput[1] + " removed.");
                  break;
               case "O": // check out
                  if(!bag.checkOut(bag.findFromNum(arrOfInput[1])))
                     System.out.println("Book#" + arrOfInput[1] + " is not available.");
                  else
                     System.out.println("You've checked out Book#" + arrOfInput[1] + ". Enjoy!");
                  break;
               case "I": // return
                  if(!bag.returns(bag.findFromNum(arrOfInput[1])))
                     System.out.println("Unable to return Book#" + arrOfInput[1] + ".");
                  else
                     System.out.println("Book#" + arrOfInput[1] + " return has been completed. Thanks!");
                  break;
               case "PA": // output the list of books to the console w/ the current sequence
                  if(bag.getNumBooks() == 0) {
                     System.out.println("Library catalog is empty!");
                     break;
                  }
                  System.out.println("**List of books in the library.");
                  bag.print();
                  System.out.println("**End of list");
                  break;
               case "PD": // output the list of books by the dates published in ascending order
                  if(bag.getNumBooks() == 0) {
                     System.out.println("Library catalog is empty!");
                     break;
                  }
                  System.out.println("**List of books by the dates published.");
                  bag.printByDate();
                  System.out.println("**End of list");
                  break;
               case "PN": // output the list of books by the book number in ascending order
                  if(bag.getNumBooks() == 0) {
                     System.out.println("Library catalog is empty!");
                     break;
                  }
                  System.out.println("**List of books by book numbers.");
                  bag.printByNumber();
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
