
package comp5511_group02_assign04_addressbook.dao;

/**
 *
 * Comp5511, Group02, Assignment 4
 */
public interface AddressBookDao extends AddressBookConstants, AddressBookReader, AddressBookWriter {
    // sorting function
    public void sortCatalog();
    // running check function
    public boolean runCheck();
}
