package comp5511_group02_assign04_addressbook.dao;

import static comp5511_group02_assign04_addressbook.dao.AddressBookConstants.MAX_LENGTH_FOR_BOOK_ISBN;
import static comp5511_group02_assign04_addressbook.dao.AddressBookConstants.MAX_LENGTH_FOR_BOOK_TITLE;
import comp5511_group02_assign04_addressbook.lib.BinarySearchTree;
import static comp5511_group02_assign04_addressbook.lib.BinarySearchTree.root;
import comp5511_group02_assign04_addressbook.lib.AddressBook;
import comp5511_group02_assign04_addressbook.lib.HeapSort;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import static java.lang.Math.toIntExact;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
// Just for testing 2016Nov13 by Aiken

/**
 *
 * @author aiken
 */
public class AddressBookArrayListData implements AddressBookDao {

    public ArrayList<AddressBook> bookCatalog = new ArrayList<AddressBook>();

    /**
     * Initializing the AddressBook catalog
     */
    public AddressBookArrayListData() {
        try {

            String path = "";
            // create recordInput for inputing records
            File inputFile = new File(path + "AddressBook.txt");
            // A connection stream connects to the text file
            FileReader fileReader = new FileReader(inputFile);
            // A file pointer always points to the text file
            BufferedReader filePointer = new BufferedReader(fileReader);
            String recordInput = null;
            // "dataArray" is an ArrayList data structure for storing records of the input text file
            List<String> dataArray = new ArrayList<>();
            while ((recordInput = filePointer.readLine()) != null) {
                dataArray.add(recordInput);
            }
            // Specify how many rows are there in the text file records
            int arrayListSize = dataArray.size();

            // Specify how many fields(columns) and what fields are there in the text file records
            String fieldsData = dataArray.get(0);
            String[] recordFields = fieldsData.split(";");
            int recordFieldsNum = 0;
            for (String itemData : recordFields) {
                System.out.println("Fields of records are: " + itemData);
                recordFieldsNum++;
            }

            // Creating a string array for all of the text file records
            String[][] bookInfoArray = new String[arrayListSize][recordFieldsNum];
            for (int i = 0; i < arrayListSize; i++) {
                String rowData = dataArray.get(i);
                String[] dataMarker = rowData.split(";");
                int indexCounter = 0;
                for (String itemData : dataMarker) {
                    bookInfoArray[i][indexCounter] = itemData;
                    indexCounter++;
                }
            }
            // Adding book records of the input file into bookCatalog ArrayList
            for (int i = 1; i < arrayListSize; i++) {
                addBook(bookInfoArray[i][0], bookInfoArray[i][1], bookInfoArray[i][2], bookInfoArray[i][3], bookInfoArray[i][4], bookInfoArray[i][5]);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Find the AddressBook and return a copy of that if invalid returns null
     *
     * @param isbn
     * @return
     */
    @Override
    public AddressBook fetchIsbn(String isbn) {
        AddressBook book = getBook(isbn);
        if (book != null) {
            return new AddressBook(book);
        }
        return null;
    }

    /**
     * Find the Book and return a copy of that if invalid returns null
     *
     * @param title
     * @return
    /**
     * Find the Book and return a copy of that if invalid returns null
     *
     * @param title
     * @return
     */
    
    /**
     * Find the AddressBook and return a copy of that if invalid returns null
     * @param title
     * @return
     */
    @Override
    public AddressBook fetchTitle(String title) {
        AddressBook book2 = getBookbyTitle(title);
        if (book2 != null) {
            return new AddressBook(book2);
        }
        return null;
    }

    /**
     * returns the size of the bookCatalog
     *
     * @return
     */
    @Override
    public int getTotalBookAmount() {
        return bookCatalog.size();
    }

    /**
     * Adding AddressBook with given arguments to the catalog ArrayList
     *
     * @param isbn
     * @param title
     * @param author
     * @param publisher
     * @param address
     * @param price
     */
    @Override
    public void addBook(String isbn, String title, String author, String publisher, String address, String price) {
        AddressBook book = new AddressBook(isbn, title, author, publisher, address, price);
        if (validate(book)) {
            bookCatalog.add(book);
        }
    }

    /**
     * Update a book of the given isbn if there are new updated records
     *
     * @param isbn
     * @param title
     * @param author
     * @param publisher
     * @param address
     * @param price
     */
    @Override
    public void updateBook(String isbn, String title, String author, String publisher, String address, String price) {
        //ToAsk: Why I didn't use fetchProduct Method
        AddressBook book = getBook(isbn);
        if (book != null) {
            book.setTitle(title);
            book.setAuthor(author);
            book.setPublisher(publisher);
            book.setAddress(address);
            book.setPrice(price);
        }

    }

    /**
     * Deletes a product with the given code if exists
     *
     * @param isbn
     */
    @Override
    public void deleteBook(String isbn) {
        AddressBook book = getBook(isbn);
        if (book != null) {
            deleteBook(book);
        }
    }

    /**
     * A String representation setting of the bookCatalog
     *
     * @return
     */
    @Override
    public String listBooks() {
        StringBuilder book_sb = new StringBuilder();
        String prefix = "";
        for (AddressBook book : bookCatalog) {
            book_sb.append(prefix);
            book_sb.append(book.toString());
            prefix = "\r\n";
        }
        return book_sb.toString();

    }

    /**
     * Heap Sorting the bookCatalog based on Isbn field.
     */
    @Override
    public void sortCatalog() {
        int isbnArraySize = bookCatalog.size();
        // Creating "isbnArray" for storing the isbn data of all books in the file
        long[] isbnArray = new long[isbnArraySize];
        int isbnArrayIndex = 0;
        for (AddressBook book : bookCatalog) {
            isbnArray[isbnArrayIndex] = Long.parseLong(book.toIsbn());
            isbnArrayIndex++;
        }
        // Creating a 2D array of isbnIndexArray, 
        //    so that the index can be moved together with its original Isbn value
        long[][] isbnIndexArray = new long[isbnArraySize][2];
        // The first column is for Isbn, and the second column is for storing index
        for (int i = 0; i < isbnArraySize; i++) {
            isbnIndexArray[i][0] = isbnArray[i];
            isbnIndexArray[i][1] = i;
        }
        // Creating an array for storing the heap-sorting result of Isbn.
        long[] heapSorted_isbnArray = isbnArray;
        // Using HeapSort class for sorting Isbn.
        HeapSort.sortIsbn(heapSorted_isbnArray);
        // Creating "sortedIsbnIndex" array for storing the index moved 
        // along with Isbn number after Heap-Sorting, then take it as a reference
        // for changing the sequence of the AddressBook arrayList
        long[] sortedIsbnIndex = new long[isbnArraySize];
        for (int i = 0; i < isbnArraySize; i++) {
            for (int j = 0; j < isbnArraySize; j++) {
                if (heapSorted_isbnArray[i] == isbnIndexArray[j][0]) {
                    sortedIsbnIndex[i] = isbnIndexArray[j][1];
                }
            }
        }
        // Creating string arrays for storing the data with the new sequence.
        String[] bookCatalogNew_isbn = new String[isbnArraySize];
        String[] bookCatalogNew_title = new String[isbnArraySize];
        String[] bookCatalogNew_author = new String[isbnArraySize];
        String[] bookCatalogNew_publisher = new String[isbnArraySize];
        String[] bookCatalogNew_address = new String[isbnArraySize];
        String[] bookCatalogNew_price = new String[isbnArraySize];
        for (int k = 0; k < isbnArraySize; k++) {
            bookCatalogNew_isbn[k] = bookCatalog.get(toIntExact(sortedIsbnIndex[k])).getIsbn();
            bookCatalogNew_title[k] = bookCatalog.get(toIntExact(sortedIsbnIndex[k])).getTitle();
            bookCatalogNew_author[k] = bookCatalog.get(toIntExact(sortedIsbnIndex[k])).getAuthor();
            bookCatalogNew_publisher[k] = bookCatalog.get(toIntExact(sortedIsbnIndex[k])).getPublisher();
            bookCatalogNew_address[k] = bookCatalog.get(toIntExact(sortedIsbnIndex[k])).getAddress();
            bookCatalogNew_price[k] = bookCatalog.get(toIntExact(sortedIsbnIndex[k])).getPrice();
        }
        // Updating the new-sorted data into AddressBook arrayList.
        for (int m = 0; m < isbnArraySize; m++) {
            bookCatalog.get(m).setIsbn(bookCatalogNew_isbn[m]);
            bookCatalog.get(m).setTitle(bookCatalogNew_title[m]);
            bookCatalog.get(m).setAuthor(bookCatalogNew_author[m]);
            bookCatalog.get(m).setPublisher(bookCatalogNew_publisher[m]);
            bookCatalog.get(m).setAddress(bookCatalogNew_address[m]);
            bookCatalog.get(m).setPrice(bookCatalogNew_price[m]);
        }
    }

    /**
     * Deletes a book (used internally)
     *
     * @param book
     */
    private void deleteBook(AddressBook book) {
        bookCatalog.remove(book);
    }

    /**
     * Validates a book before adding
     *
     * @param book
     * @return
     */
    private boolean validate(AddressBook book) {
        if (book.getTitle().length() > MAX_LENGTH_FOR_BOOK_TITLE
                || book.getIsbn().length() > MAX_LENGTH_FOR_BOOK_ISBN
                || book.getPrice().length() < 0
                ) 
        {
            return false;
        }
        if (bookCatalog.contains(book)) {
            return false;
        }
        return true;
    }

    /**
     * Search for a book using binary search
     *
     * @param isbn
     * @return
     */
    private AddressBook getBook(String isbn) {
        //Collections.sort(bookCatalog, Collections.reverseOrder());
        int index = Collections.binarySearch(bookCatalog, new AddressBook(isbn));
        if (index >= 0) {
            return bookCatalog.get(index);
        } else {
            return null;
        }
    }

    /**
     * Search for a book using binary search
     *
     * @param title
     * @return
     */
    private AddressBook getBookbyTitle(String title) {
        int titleArraySize = bookCatalog.size();
        String[][] bookTitle = new String[titleArraySize][2];
        int titleArrayIndex = 0;
        for (AddressBook book : bookCatalog) {
            bookTitle[titleArrayIndex][0] = book.bookTitle();
            bookTitle[titleArrayIndex][1] = Integer.toString(titleArrayIndex);
            titleArrayIndex++;
        }
        // Creating a binary search tree for title records
        for (int i=0;i<titleArraySize;i++) {
            BinarySearchTree.treeInsert(bookTitle[i][0]);  // Add user's string to the tree.
        }
        int titleIndex = 0;
        if (BinarySearchTree.treeContains(root,title)) {
            for(int i=0;i<titleArraySize;i++){
               if (title.equals(bookTitle[i][0]))
                   titleIndex = i;
            }
            return bookCatalog.get(titleIndex);
        } else {
            return null;
        }
    }
}
