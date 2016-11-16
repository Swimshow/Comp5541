
package comp5511_group02_assign04_addressbook.dao;


/**
 *
 * @author aiken
 * 
 */
public interface AddressBookWriter {
    void addBook(String isbn, String title, String author, String publisher, String address, String price);

    void updateBook(String isbn, String title, String author, String publisher, String address,String price);

    void deleteBook(String isbn);
}
