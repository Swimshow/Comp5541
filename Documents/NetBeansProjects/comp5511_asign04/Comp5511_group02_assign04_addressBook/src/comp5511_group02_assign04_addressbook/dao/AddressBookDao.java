
package comp5511_group02_assign04_addressbook.dao;

/**
 *
 * @author aiken
 */
public interface AddressBookDao extends AddressBookConstants, AddressBookReader, AddressBookWriter {
    public void sortCatalog();
}
