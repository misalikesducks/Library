/**
 * @author Connie Chen
 * @author Tiffany Lee
 */
import java.util.StringTokenizer;
import java.util.Scanner;

public class Kiosk {
    // handles inputs and outputs
    public void run(){
       System.out.println("Library Kiosk running.");
       Scanner scan = new Scanner(System.in);

       while(scan.hasNextLine()) //loops while there are inputs
       {
            String input  = scan.nextLine();
            String[] arrOfInput = input.split(); //fix
            System.out.println(input);
       }

       System.out.println("Kiosk session ended.");
    }
}
