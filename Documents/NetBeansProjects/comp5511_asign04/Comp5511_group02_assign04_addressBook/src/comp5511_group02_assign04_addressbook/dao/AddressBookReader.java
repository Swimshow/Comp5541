/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp5511_group02_assign04_addressbook.dao;

import comp5511_group02_assign04_addressbook.lib.AddressBook;

/**
 *
 * @author aiken
 */
public interface AddressBookReader {
    
    AddressBook fetchIsbn(String isbn);
    AddressBook fetchTitle(String title);
    int getTotalBookAmount();
    
    
    public String listBooks(); 
}
