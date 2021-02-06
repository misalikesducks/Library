/**
 * @author Connie Chen
 * @author Tiffany Lee
 */
import Java.util.Calendar;

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
      day = c.get(Calendar.DAY);
   }


   // need to check for leap year
   public boolean isValid() {
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
            if(this.date > SHORTMONTH)
               return false;
         case JANUARY:
         case MARCH:
         case MAY:
         case JULY:
         case AUGUST:
         case OCTOBER:
         case DECEMBER:
            if(this.date > LONGMONTH)
               return false;
         case FEBRUARY:
            if(this.year % QUADRENNIAL == 0){
               if(this.year % CENTENNIAL == 0) {
                  if(this.year % QUATERCENTENNIAL == 0) {
                     if(this.date > LEAP)
                        return false;
                  }
               }
               else {
                  if(this.date > LEAP)
                     return false;
               }

            }
            else {
               if(this.date > NOT_LEAP)
                  return false;
            }

      }
      return true;
   }
}
