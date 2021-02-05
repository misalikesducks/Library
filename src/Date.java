/**
 * @author Connie Chen
 * @author Tiffany Lee
 */
import Java.util.Calendar;

public class Date {
   private int year;
   private int month;
   private int day;

   public Date(String date) { //taking mm//dd//yyyy and create a Date Object

   }
   public Date() { //create an object with today's date

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
