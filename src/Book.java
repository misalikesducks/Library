/**
 * Book class implementing objects that have serial numbers and can be checked out.
 * Stored in Library class objects.
 * @author Connie Chen, Tiffany Lee
 */

public class Book {
    private String number;
    private String name;
    private boolean checkedOut;
    private Date datePublished;

    //CONSTRUCTOR

    /**
     * Creates a Book object that can be checkedOut
     * @param number of the serial number
     * @param name of the Book
     * @param checkedOut - variable representing if the Book is available
     * @param datePublished of the Book
     */
    public Book(String number, String name, boolean checkedOut, Date datePublished) {
        this.number = number;
        this.name = name;
        this.checkedOut = checkedOut;
        this.datePublished = datePublished;
    }

    //ACCESSOR METHODS

    /**
     * Accesses the number data member of Book
     * @return String number of the instance of Book
     */
    public String getNumber() {
        return this.number;
    }

    /**
     * Accesses the name data member of Book.
     * @return String name of the current instance of Book
     */
    public String getName() {
        return this.name;
    }

    /**
     * Accesses the checkedOut data member of Book
     * @return boolean variable, checkedOut, of the current instance of Book
     */
    public boolean getCheckedOut() {
        return this.checkedOut;
    }

    /**
     * Accesses the datePublished member of Book.
     * @return Date object of current instance of Book
     */
    public Date getDatePublished() {
        return this.datePublished;
    }

    //MODIFIER METHODS

    /**
     * Modifies the number member of Book with the given String
     * @param num to be given to the current Instance of Book
     */
    public void setNumber(String num) {
        this.number = num;
    }

    /**
     * Modifies the name member of Book with the given String
     * @param bookName to be given to the current Instance of Book
     */
    public void setName(String bookName) {
        this.name = bookName;
    }

    /**
     * Modifies the datePublished member of Book with the given Date
     * @param bookDate to be given to the current Instance of Book
     */
    public void setDatePublished(Date bookDate) {
        this.datePublished = bookDate;
    }

    /**
     * Modifies the checkedOut member of Book with given boolean
     * @param isCheckedOut to be given to the current Instance of Book
     */
    public void setCheckedOut(boolean isCheckedOut) {
        this.checkedOut = isCheckedOut;
    }

    /**
     * Overrides default method of equals from Java.lang*
     * Checks if current Instance of Book is equal to an Object
     * @param obj that could be an Instance of a Book
     * @return true if the serial number of 2 books objects are the same, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Book) {
            Book comparingBook = (Book)obj;
            return comparingBook.getNumber().equals(this.number);
        }
        return false;
    }

    /**
     * Overrides default method of toString from Java.Lang*
     * @return a String stating the textual representation of a Book
     */
    @Override
    public String toString() {
        String availability;

        if(checkedOut)
            availability = "is checked out.";
        else
            availability = "is available.";

        return "Book#" + this.number + "::" + this.name + "::" + this.datePublished.toString() +
                "::" + availability;
    }
}
