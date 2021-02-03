/**
 * @author Connie Chen
 * @author Tiffany Lee
 *
 */
public class Book {
    private String number; // a five digit serial number unique to the book -- starting 10001
    private String name;
    private boolean checkedOut; // t = someone took it u fool, f = you can take it
    private Date datePublished;

    @Override
    public boolean equals(Object obj){
        //returns if the serial number of 2 book objects are the same
        return this.number.equals(obj.number);
    }
    @Override
    public String toString(){
        //returns a textual representation of a book
        //Book#10007::Design Patterns::5/30/1996::is available.
        return null;
    }


}
