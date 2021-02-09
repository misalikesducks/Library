/**
 * Date class represents String of date object.
 * Stores Year, Month, and Day of objects to compare.
 * @author Connie Chen, Tiffany Lee
 */
import java.util.Calendar;

public class Date {
   private int year;
   private int month;
   private int day;

   public static final int NOT_TRUE = 0;

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

   /**
    * Create an object with a given String.
    * @param date in String format of MM/DD/YYYY
    */
   public Date(String date) {
      String[] arrOfDate = date.split("/", 0);
      year = Integer.parseInt(arrOfDate[2]);
      month = Integer.parseInt(arrOfDate[0]);
      day = Integer.parseInt(arrOfDate[1]);
   }

   /**
    * Create an object with the current date.
    */
   public Date() {
      Calendar c = Calendar.getInstance();
      year = c.get(Calendar.YEAR);
      month = c.get(Calendar.MONTH);
      day = c.get(Calendar.DATE);
   }

   //ACCESSOR METHODS

   /**
    * Accesses a Date object's data member month.
    * @return the object's Month
    */
   public int getMonth() {
      return this.month;
   }

   /**
    * Accesses a Date object's data member year.
    * @return the object's year
    */
   public int getYear() {
      return this.year;
   }

   /**
    * Accesses a Date object's data member Day.
    * @return the object's day
    */
   public int getDay() {
      return this.day;
   }

   /**
    * Returns the date of an object in String format.
    * @return String of the date of the object in MM/DD/YYYY format
    */
   public String toString() {
      return this.getMonth() + "/" + this.getDay() + "/" + this.getYear();
   }

   /**
    * Checks if a Date object has a valid date.
    * A valid date has the year after 1900 and does not surpass current date.
    * Leap year and valid days of a month is checked also.
    * @return true if the date is valid and false otherwise
    */
   public boolean isValid() { //checks if a Date is valid
      Calendar c = Calendar.getInstance();
      int currMonth = c.get(Calendar.MONTH) + 1;

      if(this.year < 1900 || this.year > c.get(Calendar.YEAR))
         return false;

      if(this.month > DECEMBER || this.month < JANUARY)
         return false;

      //checks if the date is after our current date
      if((this.year == c.get(Calendar.YEAR) && this.month > currMonth) //same year, month is later
            || (this.year == c.get(Calendar.YEAR) && this.month == currMonth && this.day > c.get(Calendar.DATE))) //same year + month, day is later
         return false;

      if(this.year == c.get(Calendar.YEAR) && this.month == currMonth && this.day == c.get(Calendar.DATE)) //it's the current date
         return true;

      switch(this.month) {
         case APRIL:
         case JUNE:
         case SEPTEMBER:
         case NOVEMBER:
            if(this.day > SHORTMONTH)
               return false;
            break;
         case JANUARY:
         case MARCH:
         case MAY:
         case JULY:
         case AUGUST:
         case OCTOBER:
         case DECEMBER:
            if(this.day > LONGMONTH)
               return false;
            break;
         case FEBRUARY:
            if(this.year % QUADRENNIAL == 0){
               if(this.year % CENTENNIAL == 0) {
                  if(this.year % QUATERCENTENNIAL == 0) {
                     if(this.day > LEAP){
                        return false;
                     }
                  }
               } else {
                  if(this.day > LEAP) {
                     return false;
                  }
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

   /**
    * Compares the current instance of Date with a second Date object
    * @param date object to be compared
    * @param name1 of the first book to be compared
    * @param name2 of the second book to be compared
    * @return true if the current Date instance is earlier than Date object passed in, false otherwise
    */
   public boolean earlierDate(Date date, String name1, String name2) {
      Calendar date1 = Calendar.getInstance();
      Calendar date2 = Calendar.getInstance();

      date1.set(Calendar.MONTH, this.getMonth());
      date1.set(Calendar.DATE, this.getDay());
      date1.set(Calendar.YEAR, this.getYear());

      date2.set(Calendar.MONTH, date.getMonth());
      date2.set(Calendar.DATE, date.getDay());
      date2.set(Calendar.YEAR, date.getYear());

      int compareDates = date1.compareTo(date2);
      if(compareDates < NOT_TRUE) {
         return true;
      } else if(compareDates == 0) {
            if(name1.compareTo(name2) < 0)
               return true;
      }
      return false;
   }

   /**
    * Testbed main for .isValid() method
    * Runs test cases
    * @param arg
    */
   public static void main(String arg[]) {
      //Testing the isValid() method
      //Test case 1: A date with year after current year
      System.out.println("Running test case #1");
      Date date1 = new Date("4/5/2022");
      if(date1.isValid())
         System.out.println("Test case #1, date is after our current date. PASSED.");
      else
         System.out.println("Test case #1, date is after our current date. FAILED.");

      //Test Case 2: A valid month
      System.out.println("Running test case #2");
      Date date2 = new Date("2/13/2020");
      if(date2.isValid())
         System.out.println("Test case #2, month is valid. PASSED.");
      else
         System.out.println("Test case #2, month is valid. FAILED.");

      System.out.println("Running test case #3");
      Date date3 = new Date("2/29/2021");
      if(date3.isValid())
         System.out.println("Test case #3, date is same year and month but after current day. PASSED.");
      else
         System.out.println("Test case #3, date is same year and month but after current day. FAILED.");

      System.out.println("Running test case #4");
      Date date4 = new Date("3/40/2020");
      if(date4.isValid())
         System.out.println("Test case #4, invalid day. PASSED.");
      else
         System.out.println("Test case #4, invalid day. FAILED.");
   }

}
