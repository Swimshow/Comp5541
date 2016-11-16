
package comp5511_group02_assign04_addressbook.lib;

// Just for testing 2016Nov13 by Aiken

/**
 *
 * @author aiken
 */
public class AddressBook implements Comparable<AddressBook>{
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private String address;
    private String price;
    
    public AddressBook(String isbn, String title, String author, String publisher, String address, String price) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.address = address;
        this.price = price;
    }
    
    /**
     * Constructor for Binary Search to add or delete book
     * @param isbn 
     */
    public AddressBook(String isbn) {
        this.isbn = isbn;
    }

    public AddressBook(AddressBook book) {
        this.isbn = book.getIsbn();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
        this.address = book.getAddress();
        this.price = book.getPrice();
    }    
    
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
    /**
     * Two book are equal if they have the same isbn
     * @param isbnCode
     * @return 
     */
    public boolean equals(AddressBook isbnCode) {
        if (isbnCode == null) {
            return false;
        }
        return isbnCode.getIsbn().equals(getIsbn());
    }
         
    //Returning records of the AddressBook and displaying on console
    public String toString() {
        StringBuilder book_sb = new StringBuilder();
        book_sb.append("Book ISBN: ");
        book_sb.append(getIsbn());
        book_sb.append(", Title: ");
        book_sb.append(getTitle());
        book_sb.append(", Author: ");
        book_sb.append(getAuthor());
        book_sb.append(", Publisher: ");
        book_sb.append(getPublisher());
        book_sb.append(", Publisher Address: ");
        book_sb.append(getAddress());
        book_sb.append(", Price: ");
        book_sb.append(getPrice());
        return book_sb.toString();
    }
    //Returning records of the AddressBook and displaying on console
    public String toIsbn() {
        StringBuilder book_isbn_sb = new StringBuilder();
        book_isbn_sb.append(getIsbn());
        return book_isbn_sb.toString();
    }
    
    //Returning title of the AddressBook and displaying on console
    public String bookTitle() {
        StringBuilder book_title_sb = new StringBuilder();
        book_title_sb.append(getTitle());
        return book_title_sb.toString();
    }
    
    /**
     * this method is used in sort and binary search
     * @param isbnCode
     * @return 
     */
    @Override
    public int compareTo(AddressBook isbnCode) {
        return this.getIsbn().compareTo(isbnCode.getIsbn());
    }
}
