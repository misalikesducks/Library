/**
 * @author Connie Chen
 * @author Tiffany Lee
 */

public class Library {
    private Book[] books; // array-based implementation of the bag data structure
    private int numBooks; // the number of books currently in the bag

    public static final int NOT_FOUND = -1;

    public Library() { // default constructor to create an empty bag
        numBooks = 0;
        this.books = new Book[4]; // idk if we need this
    }

    private int find(Book book) { // helper method to find a book in the bag
        int bookNum = NOT_FOUND;
        for(int i = 0; i < numBooks; i++) { //traverses through books to find target
            if(books[i].equals(book)) {
                bookNum = i;
                break;
            }
        }
        return bookNum;
    }

    private void grow() { // helper method to grow the capacity by 4
        int newTotalNumBooks = this.numBooks + 4; // see if we need to make non magic number
        Book[] newLibrary = new Book[newTotalNumBooks];
        for(int i = 0; i < this.numBooks; i++)
            newLibrary[i] = books[i];
        this.books = newLibrary;
    }

    public void add(Book book) {
        if(this.numBooks == this.books.length)
            this.grow();
        this.books[numBooks] = book;
        numBooks++;
    }

    public boolean remove(Book book) {
        boolean foundBook = false;
        for(int i = 0; i < numBooks; i++) { //moves target book to the end and remove
            if(this.books[i].equals(book)) {
                this.books[i] = this.books[numBooks - 1];
                this.books[numBooks - 1] = null;
                foundBook = true;
                return foundBook;
            }
        }
        return foundBook;
    }

    public boolean checkOut(Book book) {
        boolean isAvailable = false;
        for(int i = 0; i < numBooks; i++) {
            if(this.book[i].equals(book) && !this.book[i].checkedOut) {
                this.book[i].checkedOut = true;
                isAvailable = true;
                break;
            }
        }
        return isAvailable;
    }

    public boolean returns(Book book) {
        boolean canReturn = false;
        for(int i = 0; i < numBooks; i++) {
            if(this.book[i].equals(book) && this.book[i].checkedOut) {
                this.book[i].checkedOut = false;
                canReturn = true;
                break;
            }
        }
        return canReturn;
    }

    // the only methods we can use system.out
    public void print(){
        System.out.println("idk man help");
    } // print the list of books in the bag
    public void printByDate(){} // print the list of books by datePublished (ascending)
    public void printByNumber(){} // print the list of books by number (ascending)
}
