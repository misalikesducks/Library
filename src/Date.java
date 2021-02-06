/**
 * @author Connie Chen
 * @author Tiffany Lee
 */
import java.util.Calendar;

public class Date {
   private int year;
   private int month;
   private int day;

   public static final int QUADRENNIAL = 4;
   public static final int CENTENNIAL = 100;
   public static final int QUATERCENTENNIAL = 400;

   public static final int JANUARY = 1;
   public static final int FEBRUARY = 2;
   public static final int MARCH = 3;
   public static final int APRIL = 4;
   public static final int MAY = 5;
   public static final int JUNE = 6;
   public static final int JULY = 7;
   public static final int AUGUST = 8;
   public static final int SEPTEMBER = 9;
   public static final int OCTOBER = 10;
   public static final int NOVEMBER = 11;
   public static final int DECEMBER = 12;

   public static final int LONGMONTH = 31;
   public static final int SHORTMONTH = 30;
   public static final int LEAP = 29;
   public static final int NOT_LEAP = 28;

   public Date(String date) { // taking mm//dd//yyyy and create a Date Object
      String[] arrOfDate = date.split("//", 0);
      year = Integer.parseInt(arrOfDate[2]);
      month = Integer.parseInt(arrOfDate[0]);
      day = Integer.parseInt(arrOfDate[1]);
   }

   public Date() { // create an object with today's date
      Calendar c = Calendar.getInstance();
      year = c.get(Calendar.YEAR);
      month = c.get(Calendar.MONTH);
      day = c.get(Calendar.DATE);
   }

   // accessor methods
   public int getMonth() {
      return this.month;
   }

   public int getYear() {
      return this.year;
   }

   public int getDay() {
      return this.day;
   }

   public boolean isValid() { // checks if a Date is valid
      Calendar c = Calendar.getInstance();
      if(this.year < 1900 || this.year > c.get(Calendar.YEAR))
         return false;

      if(this.month > DECEMBER || this.month < JANUARY)
         return false;

      switch(this.month) {
         case APRIL:
         case JUNE:
         case SEPTEMBER:
         case NOVEMBER:
            if(this.day > SHORTMONTH)
               return false;
         case JANUARY:
         case MARCH:
         case MAY:
         case JULY:
         case AUGUST:
         case OCTOBER:
         case DECEMBER:
            if(this.day > LONGMONTH)
               return false;
         case FEBRUARY:
            if(this.year % QUADRENNIAL == 0){
               if(this.year % CENTENNIAL == 0) {
                  if(this.year % QUATERCENTENNIAL == 0) {
                     if(this.day > LEAP)
                        return false;
                  }
               } else {
                  if(this.day > LEAP)
                     return false;
               }
            } else {
               if(this.day > NOT_LEAP)
                  return false;
            }
         default:
            break;
      }
      return true;
   }


   public static void main(String arg[]) {
      System.out.println("Running testcase 1: ");
      Date date1 = new Date("2//29//2019");
      if(date1.isValid()) {
         System.out.println("THIS IS RIGHT");
      } else {
         System.out.println("No");
      }
   }
}
