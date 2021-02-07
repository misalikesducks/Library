/**
 * @author Connie Chen
 * @author Tiffany Lee
 */

public class Library {
    private Book[] books; // array-based implementation of the bag data structure
    private int numBooks; // the number of books currently in the bag

    public static final int NOT_FOUND = -1;
    public static final int BAGSIZE = 4;

    public Library() { // default constructor to create an empty bag
        this.books = new Book[4];
        numBooks = 0;
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
        int newTotalNumBooks = this.numBooks + BAGSIZE; // see if we need to make non magic number
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
        if (book == null)
            return false;

        int targetBookIndex = this.find(book);
        if (targetBookIndex == NOT_FOUND)
            return false;
        for (int i = targetBookIndex; i < numBooks - 1; i++) {
            this.books[i] = this.books[i + 1];
            targetBookIndex++;
        }
        return true;
    }

    public boolean checkOut(Book book) {
        boolean isAvailable = false;
        int targetIndex = this.find(book);
        if (targetIndex == NOT_FOUND)
            return false;
        if(!books[targetIndex].getCheckedOut()) {
            books[targetIndex].setCheckedOut(true);
            isAvailable = true;
        }
        return isAvailable;
    }

    public boolean returns(Book book) {
        boolean canReturn = false;
        for(int i = 0; i < numBooks; i++) {
            if(this.books[i].equals(book) && this.books[i].getCheckedOut()) {
                this.books[i].setCheckedOut(false);
                canReturn = true;
                break;
            }
        }
        return canReturn;
    }

    // the only methods we can use system.out
    public void print() { // print the list of books in the bag
        for(int i = 0; i < numBooks; i++)
            System.out.println(this.books[i].toString());
    }

    public void printByDate() { // print the list of books by datePublished (ascending)
        Book[] sortedLibrary = sortBooks(this.books, 0, this.numBooks - 1);
        for(int i = 0; i < numBooks; i++)
            System.out.println(sortedLibrary[i].toString());
    }
    public void printByNumber() { // print the list of books by number (ascending)
        for(int i = 0; i < numBooks; i++)
            System.out.println(this.books[i].toString());
    }

    // HELPER METHODS

    public Book findFromNum(String num) { // creates a Book object if a book with the given serial number is found in the Library
        for(int i = 0; i < this.numBooks; i++)
            if(this.books[i].getNumber().equals(num))
                return this.books[i];
        return null;
    }

    public void quickSort(Book[] books, int low, int high, boolean sortType) {
        if(low < high) {
            int partitionIndex = partition(books, low, high, sortType);
            quickSort(books, low, partitionIndex - 1, sortType);
            quickSort(books, partitionIndex + 1, high, sortType);
        }
    }

    public int partition(Book[] books, int low, int high, boolean sortType) {
        Book pivot = books[high];
        int small = (low - 1);

        for(int j = low; j < high; j++) {
            if(sortType == BY_DATE && !checkDateGreater(books[j], pivot)) {
                small++;
                Book temp = books[small];
                books[small] = books[j];
                books[j] = temp;
            } else {
                if(!checkNumGreater(books[j], pivot)) {
                    small++;
                    Book temp = books[small];
                    books[small] = books[j];
                    books[j] = temp;
                }
            }
        }

        Book temp = books[small + 1];
        books[small + 1] = books[high];
        books[high] = temp;
        return small + 1;
    }

    // returns true if book1 is published earlier than book2
    public boolean checkDateGreater(Book book1, Book book2) {
        if(book1.getDatePublished().getYear() > book2.getDatePublished().getYear())
            return false;
        else {
            if(book1.getDatePublished().getMonth() > book2.getDatePublished().getMonth())
                return false;
            else {
                if(book1.getDatePublished().getDay() > book2.getDatePublished().getDay())
                    return false;
                else {
                    if(book1.getName().compareTo(book2.getName()) >= 0)
                        return false;
                    else
                        return true;
                }
            }
        }
    }

    // returns true if book1 has a smaller serial number than book2
    public boolean checkNumGreater(Book book1, Book book2) {
        int serial1 = Integer.parseInt(book1.getNumber());
        int serial2 = Integer.parseInt(book2.getNumber());
        if(serial1 < serial2)
            return true;
        return false;
    }
}
