
package comp5511_group02_assign04_addressbook.dao;

/**
 *  Comp5511, group02, Assignment4
 *  
 */
public interface AddressBookWriter {
    
    // adding records in arraylist ADT
    void addAddressBook(String firstName,String lastName,String companyName,String address,String city,String province,String postal,String phone,String email);
    // add a new record function
    void addNewRecord(String firstName,String lastName,String companyName,String address,String city,String province,String postal,String phone,String email);
    // update a record
    void updateAddressBook(String firstName,String lastName,String companyName,String address,String city,String province,String postal,String phone,String email);
    // delete a record
    void deleteRecord(String phone);
    // output to a new file
    void WriteToFile();
}
