
package comp5511_group02_assign04_addressbook.dao;

/**
 *  Comp5511, group02, Assignment4
 *  
 */
public interface AddressBookReader {
    // checking if there is a pattern in the records
    String fetchPattern(String pattern);
    // if there there is a pattern in the records, return true.
    boolean checkRecord(String inputFirstName,String inputPhone);
    // total number of records in the arraylist ADT
    int getTotalRecordsAmount();
    // display all the records in the arraylist ADT
    public String listPeople(); 
}
