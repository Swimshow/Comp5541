package comp5511_group02_assign04_addressbook;

import comp5511_group02_assign04_addressbook.dao.AddressBookArrayListData;
import comp5511_group02_assign04_addressbook.dao.AddressBookDao;
import comp5511_group02_assign04_addressbook.lib.AddressBook;
import java.util.Scanner;

public class KeyboardGUI {
     AddressBookDao bookDao = new AddressBookArrayListData();
     
    public void GUI(){
       
        bookDao.sortCatalog();
        System.out.println(bookDao.listBooks());
        System.out.println("Heap-Sorted book list is represented above");
        Scanner Type = new Scanner(System.in);
        
        System.out.println("Please type first letter of your desired function ");
        System.out.println("'a' for adding, 'd' for deleting, 'u' for update, 'si' for searching by ISBN, 'st' for searching by Title");
        String Function = Type.nextLine();
                      
        if(Function.equals("a")){
        System.out.println("Okay let's add a record! ");
        System.out.println("What would you like to add?");
        System.out.println("Please Type ISBN");
        String ISBN = Type.nextLine();
        System.out.println("Please Type the Title");
        String TITLE = Type.nextLine();
        System.out.println("Please Type the Author name");
        String AUTHOR = Type.nextLine();
        System.out.println("Please Type the Publisher name");
        String PUBLISHER = Type.nextLine();
        System.out.println("Please Type the Address");
        String ADDRESS = Type.nextLine();
        System.out.println("Please Type the Price");
        String PRICE = Type.nextLine();
        bookDao.addBook(ISBN, TITLE,AUTHOR,PUBLISHER,ADDRESS, PRICE);
        bookDao.sortCatalog();
        System.out.println(bookDao.listBooks());
        System.out.println("You have Added:   " + ISBN);
        System.out.println("Heap-Sorted book list is represented above");
        }
        
        //else
        else if(Function.equals("d")){
        System.out.println("Okay let's delete a record! ");
        System.out.println("What would you like to delete?");
      
        System.out.println("Please Type ISBN");
        String ISBN = Type.nextLine();
        //What if the value is not in the book?
        //could run a search first
        bookDao.deleteBook(ISBN);
        bookDao.sortCatalog();
        System.out.println(bookDao.listBooks());
        System.out.println("You have deleted:   " + ISBN);
        System.out.println("Heap-Sorted book list is represented above");
        }
       
        else if(Function.equals("u")){
        System.out.println("Okay let's update a record! ");
        System.out.println("Which record would you like to update?");
        System.out.println("Please Type ISBN of the record you want to change");
        String ISBN = Type.nextLine();
        System.out.println("Please Type the updated Title");
        String TITLE = Type.nextLine();
        System.out.println("Please Type the updated Author name");
        String AUTHOR = Type.nextLine();
        System.out.println("Please Type the updated Publisher name");
        String PUBLISHER = Type.nextLine();
        System.out.println("Please Type the updated Address");
        String ADDRESS = Type.nextLine();
        System.out.println("Please Type the updated Price");
        String PRICE = Type.nextLine();
        bookDao.updateBook(ISBN, TITLE,AUTHOR,PUBLISHER,ADDRESS, PRICE);
        bookDao.sortCatalog();
        System.out.println(bookDao.listBooks());
        System.out.println("You have Updated:   " + ISBN);
        System.out.println("Heap-Sorted book list is represented above");
        }
        
       
           
        else if(Function.equals("si")){
        System.out.println("Okay let's search for a record! ");
        System.out.println("Which record would you like to search for?");
        System.out.println("Please Type ISBN of the record you want to look for");
        String ISBN = Type.nextLine();
        System.out.println("Now please choose the category you would like for: " + ISBN);
        System.out.println("For ISBN type 'is', for Title type 'ti', for Author type 'au', for Publisher type 'pu', for Address type 'ad', for Price type 'pr' ");
        String Category = Type.nextLine();
       
        
        if(Category.equals("is")){
         AddressBook book = bookDao.fetchIsbn(ISBN);
        System.out.println("isbn for your search is: "+book.getIsbn());
         
            
        }
        else if(Category.equals("ti")){
            
         AddressBook book = bookDao.fetchIsbn(ISBN);
         System.out.println("Title for your search is: "+book.getTitle());
            
        }
        else  if(Category.equals("au")){
            AddressBook book = bookDao.fetchIsbn(ISBN);
            System.out.println("Author name for your search is: "+book.getAuthor());
        }
        else if(Category.equals("pu")){
         AddressBook book = bookDao.fetchIsbn(ISBN);
        System.out.println("Publisher Name for your search is: "+book.getPublisher());
        }
        else if(Category.equals("ad")){
            AddressBook book = bookDao.fetchIsbn(ISBN);
            System.out.println("Address for your search is: "+book.getAddress());
        }
        else if(Category.equals("pr")){
           AddressBook book = bookDao.fetchIsbn(ISBN);
            System.out.println("Price for your search is: "+book.getPrice());
        }
       
        }
        
        else if(Function.equals("st")){
        System.out.println("Okay let's search for a record! ");
        System.out.println("Which record would you like to search for?");
        System.out.println("Please Type Title of the record you want to look for");
        String Title = Type.nextLine();
        System.out.println("Now please choose the category you would like for: " + Title);
        System.out.println("For ISBN type is, for Title type ti, for Author type au, for Publisher type pu, for Address type ad, for Price type pr");

        String Category = Type.nextLine();
        
        
        if(Category.equals("is")){
        AddressBook bookTitle = bookDao.fetchTitle(Title);
        
        System.out.println("isbn for your search is: "+ bookTitle.getIsbn());
        }
        else if(Category.equals("ti")){
        AddressBook bookTitle = bookDao.fetchTitle(Title);
         System.out.println("Title for your search is: "+bookTitle.getTitle());
            
        }
        else  if(Category.equals("au")){
            AddressBook bookTitle = bookDao.fetchTitle(Title);
            System.out.println("Author name for your search is: "+bookTitle.getAuthor());
        }
        else if(Category.equals("pu")){
        AddressBook bookTitle = bookDao.fetchTitle(Title);
        System.out.println("Publisher Name for your search is: "+bookTitle.getPublisher());
        }
        else if(Category.equals("ad")){
            AddressBook bookTitle = bookDao.fetchTitle(Title);
            System.out.println("Address for your search is: "+bookTitle.getAddress());
        }
        else if(Category.equals("pr")){
            AddressBook bookTitle = bookDao.fetchTitle(Title);
            System.out.println("Price for your search is: "+bookTitle.getPrice());
        }
       
        }
        else
        { System.out.println("You have not entered a valid letter!");}
             
    }
}
