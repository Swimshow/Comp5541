
package comp5511_group02_assign04_addressbook.lib;

import java.util.Comparator;

/**
 *  Comp5511, group02, Assignment4
 *  
 */
public class AddressBook implements Comparable<AddressBook>,Comparator<AddressBook>{
    private String firstName;
    private String lastName;
    private String companyName;
    private String city;
    private String address;
    private String province;
    private String postal;
    private String phone;
    private String email;
    
    // Constructor for AddressBook
    public AddressBook(String firstName,String lastName,String companyName,String address,String city,String province,String postal,String phone,String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postal = postal;
        this.phone = phone;
        this.email = email;
    }
    
    /**
     * Constructor for Binary Search to add or delete book
     * @param phone 
     */
    public AddressBook(String phone) {
        this.phone = phone;
    }

    public AddressBook(AddressBook book) {
        this.firstName = book.getFirstName();
        this.lastName = book.getLastName();
        this.companyName = book.getCompanyName();
        this.address = book.getAddress();
        this.city = book.getCity();
        this.province = book.getProvince();
        this.postal = book.getPostal();
        this.phone = book.getPhone();
        this.email = book.getEmail();
    }    
    // Geter and Seter for each field of the records
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostal() {
        return postal;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
   
    //Returning records of the AddressBook as strings for outputing to new file
    public String toString() {
        StringBuilder book_sb = new StringBuilder();
        book_sb.append(getFirstName());
        book_sb.append("; ");
        book_sb.append(getLastName());
        book_sb.append("; ");
        book_sb.append(getCompanyName());
        book_sb.append("; ");
        book_sb.append(getAddress());
        book_sb.append("; ");
        book_sb.append(getCity());
        book_sb.append("; ");
        book_sb.append(getProvince());
        book_sb.append("; ");
        book_sb.append(getPostal());
        book_sb.append("; ");
        book_sb.append(getPhone());
        book_sb.append("; ");
        book_sb.append(getEmail());
        book_sb.append(";");
        return book_sb.toString();
    }
    //Only for returning records of the AddressBook and displaying on console
    public String listToString() {
        StringBuilder book_sb = new StringBuilder();
        book_sb.append(" firstName: ");
        book_sb.append(getFirstName());
        book_sb.append(", lastName: ");
        book_sb.append(getLastName());
        book_sb.append(", companyName: ");
        book_sb.append(getCompanyName());
        book_sb.append(", address: ");
        book_sb.append(getAddress());
        book_sb.append(", city: ");
        book_sb.append(getCity());
        book_sb.append(", province: ");
        book_sb.append(getProvince());
        book_sb.append(", postal: ");
        book_sb.append(getPostal());
        book_sb.append(", phone: ");
        book_sb.append(getPhone());
        book_sb.append(", email: ");
        book_sb.append(getEmail());
        return book_sb.toString();
    }
    /**
     * this method is used for sorting based on phone number
     * @param inputPhone
     * @return 
     */
    @Override
    public int compareTo(AddressBook inputPhone) {
        return this.getPhone().compareTo(inputPhone.getPhone());
    }
    /**
     * This method in lamda expression is used for sorting based on first name.
     */
    public static Comparator<AddressBook> compareFirstName = (AddressBook firstName1, AddressBook firstName2) -> {
        String firstName_1 = firstName1.getFirstName();
        String firstName_2 = firstName2.getFirstName();
        
        //ascending order
        return firstName_1.compareTo(firstName_2);
        
        //descending order
        //return firstName_2.compareTo(firstName_1);
    };
    @Override
    public int compare(AddressBook o1, AddressBook o2) {
        //To change body of generated methods, choose Tools | Templates.
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
