/**
 * @author Connie Chen
 * @author Tiffany Lee
 *
 */
public class Book {
    private String number;
    private String name;
    private boolean checkedOut; // t = someone took it u fool, f = you can take it
    private Date datePublished;

    //CONSTRUCTOR
    public Book(String number, String name, boolean checkedOut, Date datePublished) {
        this.number = number;
        this.name = name;
        this.checkedOut = checkedOut;
        this.datePublished = datePublished;
    }

    @Override
    public boolean equals(Object obj) { //returns if the serial number of 2 book objects are the same
        Book comparingBook = (Book)obj;
        return comparingBook.getNumber().equals(this.number);
    }

    @Override
    public String toString() { //returns a textual representation of a book
        String availability;

        if(checkedOut)
            availability = "is checked out.";
        else
            availability = "is available.";

        return "Book#" + this.number + "::" + this.name + "::" + this.datePublished.toString() +
                "::" + availability;
    }

    //ACCESSOR METHODS
    public String getNumber() {
        return this.number;
    }

    public String getName() {
        return this.name;
    }

    public boolean getCheckedOut() {
        return this.checkedOut;
    }

    public Date getDatePublished() {
        return this.datePublished;
    }

    //MODIFIER METHODS
    public void setNumber(String num) { // need to implement the number system
        this.number = num;
    }

    public void setName(String bookName) {
        this.name = bookName;
    }

    public void setDatePublished(Date bookDate) {
        this.datePublished = bookDate;
    }

    public void setCheckedOut(boolean isCheckedOut) {
        this.checkedOut = isCheckedOut;
    }
}
