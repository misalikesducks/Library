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
        boolean foundBook = false;
        int targetBookIndex;
        for(int i = 0; i < numBooks; i++) { //traverses through books
            if(this.books[i].equals(book)) {
                targetBookIndex = i;
                while(targetBookIndex < numBooks - 1) // found target index, shifting all elements after target
                {
                    this.books[i] = this.books[i+1];
                    targetBookIndex++;
                }
                foundBook = true;
                return foundBook;
            }
        }
        return foundBook;
    }

    public boolean checkOut(Book book) {
        boolean isAvailable = false;
        for(int i = 0; i < numBooks; i++) {
            if(this.books[i].equals(book) && !this.books[i].getCheckedOut()) {
                this.books[i].setCheckedOut(true);
                isAvailable = true;
                break;
            }
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
        Book[] sortedLibrary = sortBooks(this.books[]);
        for(int i = 0; i < numBooks; i++)
            System.out.println(sortedLibrary[i].toString());
    }
    public void printByNumber() { // print the list of books by number (ascending)
        for(int i = 0; i < numBooks; i++)
            System.out.println(this.books[i].toString());
    }

    // HELPER METHODS

    public Book[] sortBooks(Book[] originalBooks, int left, int right) {
        Book[] sortedByDate = originalBooks;
        if(left < right) {
            int middle = left + (right - 1) / 2; // find the middle point

            // sort the first and second halves
            sortBooks(sortedByDate, left, middle);
            sortBooks(sortedByDate, middle + 1, right);

            merge(sortedByDate, left, middle, right); // merge the sorted halves
        }
        return sortedByDate;
    }

    void merge(Book[] mergingBooks, int left, int middle, int right) {
        // sizes of two subarrays to be merged
        int sizeOfArray1 = middle - left + 1;
        int sizeOfArray2 = right - middle;

        // temp arrays
        Book[] leftTemp = new Book[sizeOfArray1];
        Book[] rightTemp = new Book[sizeOfArray2];

        // copy data into temp arrays
        for(int i = 0; i < sizeOfArray1; ++i) {
            leftTemp[i] = mergingBooks[left + i];
        }
        for(int j = 0; j < sizeOfArray2; ++j) {
            rightTemp[j] = mergingBooks[middle + 1 + j];
        }

        // MERGING TEMP ARRAYS

        // initial indices of first and second subArrays
        int firstIndex = 0, secondIndex = 0;

        // initial index of merged subArray array
        int mergedArrayIndex = left;
        while(firstIndex < sizeOfArray1 && secondIndex < sizeOfArray2) {
            if (!checkDateGreater(leftTemp[firstIndex], rightTemp[secondIndex])) {
                mergingBooks[mergedArrayIndex] = leftTemp[firstIndex];
                firstIndex++;
            } else {
                mergingBooks[mergedArrayIndex] = rightTemp[secondIndex];
                secondIndex++;
            }
            mergedArrayIndex++;
        }

        // copy remaining elements of leftTemp if left over
        while(firstIndex < sizeOfArray1) {
            mergingBooks[mergedArrayIndex] = leftTemp[firstIndex];
            firstIndex++;
            mergedArrayIndex++;
        }

        // copy remaining elements of rightTemp if left over
        while(secondIndex < sizeOfArray2) {
            mergingBooks[mergedArrayIndex] = rightTemp[secondIndex];
            secondIndex++;
            mergedArrayIndex++;
        }
    }

    //returns true if book1 is published earlier than book2
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
}
