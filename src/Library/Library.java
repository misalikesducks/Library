package Library;

/**
 * Library class representing catalog of Book objects.
 * Store books in multiples of 4.
 * @author Connie Chen, Tiffany Lee
 */

public class Library {
    private Book[] books; //array-based implementation of the bag data structure
    private int numBooks; //the number of books currently in the bag


    public static final int NOT_FOUND = -1;
    public static final int BAG_SIZE = 4;

    //Constants for sortType
    public static final boolean BY_DATE = true;
    public static final boolean BY_NUMBER = false;

    public static int serialNumber = 10001;

    /**
     * Creates an array based object that stores Book objects.
     */
    public Library() {
        this.books = new Book[4];
        numBooks = 0;
    }

    //ACCESSOR METHODS
    /**
     * Accesses the numBooks data member in Library
     * @return int representing the numBooks of the instance of Library
     */
    public int getNumBooks(){
        return this.numBooks;
    }

    //HELPER METHODS
    /**
     * Finds the index number the given book in the Library object.
     * @param book that is being searched for
     * @return int representing the index number of the book in the array
     */
    private int find(Book book) {
        int bookNum = NOT_FOUND;
        for(int i = 0; i < numBooks; i++) {//traverses through books to find target
            if(books[i].equals(book)) {
                bookNum = i;
                break;
            }
        }
        return bookNum;
    }

    /**
     * Increases the capacity of Book[] books by 4.
     */
    private void grow() {
        int newTotalNumBooks = this.numBooks + BAG_SIZE;
        Book[] newLibrary = new Book[newTotalNumBooks];
        for(int i = 0; i < this.numBooks; i++)
            newLibrary[i] = books[i];
        this.books = newLibrary;
    }

    /**
     * Inserts a Book object in the Library bag data structure.
     * @param book
     */
    public void add(Book book) {
        if(this.numBooks == this.books.length)
            this.grow();
        this.books[numBooks] = book;
        numBooks++;
        serialNumber++;
    }

    /**
     * Removes the given Book from the Library.
     * @param book to be deleted
     * @return true if the book was deleted, false otherwise
     */
    public boolean remove(Book book) {
        if(book == null)
            return false;

        int targetBookIndex = this.find(book);
        if(targetBookIndex == NOT_FOUND)
            return false;

        this.books[targetBookIndex] = null;

        for(int i = targetBookIndex; i < numBooks - 1; i++) {
            this.books[i] = this.books[i + 1];
            targetBookIndex++;
        }

        this.books[numBooks - 1] = null;
        this.numBooks--;
        return true;
    }

    /**
     * Check out a given Book in the Library.
     * The Book becomes unavailable when checked out.
     * @param book to be checked out
     * @return true if the book is checked out, false otherwise
     */
    public boolean checkOut(Book book) {
        if(book == null)
            return false;

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

    /**
     * Returns a given Book to the Library.
     * The Book becomes available again when returned.
     * @param book to be returned to the Library
     * @return true if the book is returned, false otherwise
     */
    public boolean returns(Book book) {
        if (book == null)
            return false;

        boolean canReturn = false;
        int targetIndex = this.find(book);
        if (this.books[targetIndex].getCheckedOut()) {
            this.books[targetIndex].setCheckedOut(false);
            canReturn = true;
        }
        return canReturn;
    }

    /**
     * Prints the list of Books in the Library as is
     */
    public void print() {
        for(int i = 0; i < numBooks; i++)
            System.out.println(this.books[i].toString());
    }

    /**
     * Prints the list of Books in the Library by datePublished in ascending order.
     */
    public void printByDate() {
        quickSort(this.books, 0, this.numBooks - 1, BY_DATE);
        this.print();
    }

    /**
     * Prints the list of Books in the Library by its number in ascending order.
     */
    public void printByNumber() {
        quickSort(this.books, 0, this.numBooks - 1, BY_NUMBER);
        this.print();
    }

    /**
     * Returns the Book with the given serial number in the Library.
     * @param num that represents the serial number of the Book
     * @return Book object if serial number exists in Library, null otherwise
     */
    public Book findFromNum(String num) {
        for(int i = 0; i < this.numBooks; i++)
            if(this.books[i].getNumber().equals(num))
                return this.books[i];
        return null;
    }

    /**
     * Quick sort method that sorts the Book in Library in ascending order.
     * Divide and conquer method for the array of Books.
     * @param books the bag of Books to be sorted
     * @param low the beginning index of the array to be sorted
     * @param high the end index of the array to be sorted
     * @param sortType sorting either by Date or by serial number
     */
    public void quickSort(Book[] books, int low, int high, boolean sortType) {
        if(low < high) {
            int partitionIndex = partition(books, low, high, sortType);
            quickSort(books, low, partitionIndex - 1, sortType);
            quickSort(books, partitionIndex + 1, high, sortType);
        }
    }

    /**
     * Helper method for Quick sort that partitions the array to swap elements.
     * @param books the array to be sorted
     * @param low the beginning index of the array
     * @param high the end index of the array
     * @param sortType sorting either by Date or by serial number
     * @return int - index number of the element to be partitioned
     */
    public int partition(Book[] books, int low, int high, boolean sortType) {
        Book pivot = books[high];
        int small = (low - 1);

        for(int j = low; j < high; j++) {
            if(sortType == BY_DATE) {
                if(books[j].getDatePublished().earlierDate(pivot.getDatePublished(),
                        books[j].getName(), pivot.getName())) {
                    small++;
                    Book temp = books[small];
                    books[small] = books[j];
                    books[j] = temp;
                }
            } else {
                if(checkNumGreater(books[j], pivot)) {
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

    /**
     * Compares the serial number of two books
     * @param book1 to be compared
     * @param book2 to be compared
     * @return true if book1 has a smaller serial number than book2, false otherwise
     */
    public boolean checkNumGreater(Book book1, Book book2) {
        int serial1 = Integer.parseInt(book1.getNumber());
        int serial2 = Integer.parseInt(book2.getNumber());
        if(serial1 <= serial2)
            return true;
        return false;
    }
}
