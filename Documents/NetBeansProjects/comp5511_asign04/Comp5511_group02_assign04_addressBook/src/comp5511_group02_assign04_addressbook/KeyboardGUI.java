package comp5511_group02_assign04_addressbook;
/**
 *
 * Comp5511, Group02, Assignment 4
 */
import comp5511_group02_assign04_addressbook.dao.AddressBookArrayListData;
import comp5511_group02_assign04_addressbook.dao.AddressBookDao;
import java.util.Scanner;

public class KeyboardGUI {
     AddressBookDao bookDao = new AddressBookArrayListData();
     
    public void GUI(){
        System.out.println("");
        System.out.println("Fields of records are: first_name, last_name, company_name,address, city,");
        System.out.println("                       province,postal, phone, email.");
        Scanner Type = new Scanner(System.in);
        System.out.println("Please type first letter of your desired function -> ");
        System.out.println("'show' for displaying all records, 'a' for adding, 'd' for deleting, 'u' for update,");
        System.out.println(" 'sp' for searching by pattern, 'e' for exiting the program (please type in the line below):");
        String Function = Type.nextLine();
        
        if(Function.equals("show")){              
        bookDao.sortCatalog();
        System.out.println(bookDao.listPeople());
        System.out.println("  Name-Sorted people list is represented above!");
        bookDao.runCheck();
        }
        else if(Function.equals("a")){
        System.out.println("Okay let's add a new record! ");
        System.out.println("Please Type firstName");
        String firstName = Type.nextLine();
        System.out.println("Please Type the lastName:");
        String lastName = Type.nextLine();
        System.out.println("Please Type the companyName name:");
        String companyName = Type.nextLine();
        System.out.println("Please Type the Address:");
        String address = Type.nextLine();
        System.out.println("Please Type the city name:");
        String city = Type.nextLine();
        System.out.println("Please Type the province:");
        String province = Type.nextLine();
        System.out.println("Please Type the Postal Code:");
        String postal = Type.nextLine();
        System.out.println("Please Type the Phone:");
        String phone = Type.nextLine();
        System.out.println("Please Type the email:");
        String email = Type.nextLine();
        bookDao.addNewRecord(firstName, lastName,companyName,address,city, province,postal,phone,email);
            if (!bookDao.checkRecord(firstName, phone)){
                bookDao.sortCatalog();
                System.out.println(bookDao.listPeople());
                System.out.println("You have added: " + firstName+" "+lastName+"'s record !");
                System.out.println("  Name-Sorted people list is represented above!");
            }
        bookDao.runCheck();
        }
        else if(Function.equals("d")){
        System.out.println("Okay let's delete a record! ");
        System.out.println("Which record would you like to delete?");
        System.out.println("Please Type the first name of the person you want to delete:");
        String firstName = Type.nextLine();
        System.out.println("Please Type the phone number of the person you want to delete with format : XXX-XXX-XXXX");
        String phone = Type.nextLine();
        bookDao.deleteRecord(phone);
            if (bookDao.checkRecord(firstName, phone)){
                bookDao.sortCatalog();
                System.out.println(bookDao.listPeople());
                System.out.println("You have deleted a preson's reord with phone number: " + phone);
                System.out.println("  Name-Sorted people list is represented above!");
            }
        bookDao.runCheck();
        }
        else if(Function.equals("u")){
        System.out.println("Okay let's update a record! ");
        System.out.println("Which person's record would you like to update?");
        System.out.println("Please Type phone number of the person you want to change");
        System.out.println("The format of the phone number is like : XXX-XXX-XXXX");
        String phone = Type.nextLine();
        System.out.println(" Please Type the firstName:");
        String firstName = Type.nextLine();
        System.out.println(" Please Type the updated lastName:");
        String lastName = Type.nextLine();
        System.out.println(" Please Type the updated companyName name:");
        String companyName = Type.nextLine();
        System.out.println(" Please Type the updated Address:");
        String address = Type.nextLine();
        System.out.println(" Please Type the updated city name:");
        String city = Type.nextLine();
        System.out.println(" Please Type the updated province:");
        String province = Type.nextLine();
        System.out.println(" Please Type the Postal Code:");
        String postal = Type.nextLine();
        System.out.println(" Please Type the email:");
        String email = Type.nextLine();
        bookDao.updateAddressBook(firstName, lastName,companyName,address,city,province,postal,phone,email);
            if (bookDao.checkRecord(firstName, phone)){
                bookDao.sortCatalog();
                System.out.println(bookDao.listPeople());
                System.out.println(" You have Updated: " + firstName+" "+lastName+"'s record !");
                System.out.println(" Name-Sorted people list is represented above!");
            }
        bookDao.runCheck();
        }
        else if(Function.equals("sp")){
        System.out.println("Okay let's search for a pattern in all the records! ");
        System.out.println("Note: the pattern searching is 'case-sensitive' !!");
        System.out.println("Please Type a 'key word' or a 'pattern' you want to look for");
        String pattern = Type.nextLine();
        String findRecord = bookDao.fetchPattern(pattern);
            if (findRecord!=null){
                System.out.println("The search result is as below: ");   
                System.out.println(findRecord);
                System.out.println(" The firstName-sorted searching result for '"+pattern+"' is listed above. "); 
            } else {
                System.out.println("Sorry~!! There is no '"+pattern+"' can be found in the records."); 
            }
        bookDao.runCheck();
        }
        else if(Function.equals("e")){
            System.out.println("This program is stop running.");
            System.exit(0);
        }
        else
        { System.out.println("You have not entered a valid letter!");}
        bookDao.runCheck();
    }
}
