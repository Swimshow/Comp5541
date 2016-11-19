package comp5511_group02_assign04_addressbook.dao;

import comp5511_group02_assign04_addressbook.KeyboardGUI;
import static comp5511_group02_assign04_addressbook.dao.AddressBookConstants.MAX_LENGTH_FOR_BOOK_firstName;
import static comp5511_group02_assign04_addressbook.dao.AddressBookConstants.MAX_LENGTH_FOR_BOOK_lastName;
import comp5511_group02_assign04_addressbook.lib.AddressBook;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * Comp5511, Group02, Assignment 4
 */
public class AddressBookArrayListData implements AddressBookDao {

    public ArrayList<AddressBook> addressBookCatalog = new ArrayList<>();
    public ArrayList<String> matchedRecordArray = new ArrayList<>();
    File inputFile = new File("AddressBook.txt");
    // Check if there is a new file. If there is, read the new file.
    public boolean checkFile = new File("newAddressBook.txt").exists();

    /**
     * Initializing the AddressBook catalog
     */
    public AddressBookArrayListData() {
        try {
            if (checkFile) {
                inputFile = new File("newAddressBook.txt");
            }
            // A connection stream connects to the text file
            FileReader fileReader = new FileReader(inputFile);
            // A file pointer always points to the text file
            BufferedReader filePointer = new BufferedReader(fileReader);
            String recordInput;
            // "dataArray" is an ArrayList data structure for storing records of the input text file
            List<String> dataArray = new ArrayList<>();
            while ((recordInput = filePointer.readLine()) != null) {
                dataArray.add(recordInput);
            }
            // Specify how many rows are there in the text file records
            int arrayListSize = dataArray.size();
            // Specify how many fields(columns) and what fields are there in the text file records
            String fieldsData = dataArray.get(0);
            String[] recordFields = fieldsData.split(";");
            int recordFieldsNum = 0;
            for (String itemData : recordFields) {
                itemData = itemData.trim();
                //System.out.println("Fields of records are: " + itemData);
                recordFieldsNum++;
            }

            // Creating a string array for all of the text file records
            String[][] addressBookInfoArray = new String[arrayListSize][recordFieldsNum];
            for (int i = 0; i < arrayListSize; i++) {
                String rowData = dataArray.get(i);
                String[] dataMarker = rowData.split(";");
                int indexCounter = 0;
                for (String itemData : dataMarker) {
                    itemData = itemData.trim();
                    addressBookInfoArray[i][indexCounter] = itemData;
                    indexCounter++;
                }
            }
            // Adding book records of the input file into addressBookCatalog ArrayList
            for (int i = 1; i < arrayListSize; i++) {
                addAddressBook(addressBookInfoArray[i][0], addressBookInfoArray[i][1], addressBookInfoArray[i][2], addressBookInfoArray[i][3], addressBookInfoArray[i][4], addressBookInfoArray[i][5], addressBookInfoArray[i][6], addressBookInfoArray[i][7], addressBookInfoArray[i][8]);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Find the pattern and return the matched result, if invalid then returns null
     *
     * @param pattern
     * @return
     */
    @Override
    public String fetchPattern(String pattern) {
        for (int i = 0; i < getTotalRecordsAmount(); i++) {
            String refRecord = addressBookCatalog.get(i).toString();
            Pattern inputPattern = Pattern.compile(pattern);
            Matcher matching = inputPattern.matcher(refRecord);
            boolean matchCheck = matching.find();
            if (matchCheck) {
                matchedRecordArray.add(refRecord);
            }
        }
        if(!matchedRecordArray.isEmpty()){
            System.out.println("There are total '"+matchedRecordArray.size()+"' records.");
            return listMatchedRecord();
        }
        return null;
    }
    
    /**
     * returns the size of the addressBookCatalog
     *
     * @return
     */
    @Override
    public int getTotalRecordsAmount() {
        return addressBookCatalog.size();
    }

    /**
     * Adding AddressBook with given arguments to the catalog ArrayList.
     * @param firstName
     * @param lastName
     * @param companyName
     * @param address
     * @param city
     * @param province
     * @param postal
     * @param phone
     * @param email
     */
    @Override
    public void addAddressBook(String firstName, String lastName, String companyName, String address, String city, String province, String postal, String phone, String email) {
        AddressBook book = new AddressBook(firstName, lastName, companyName, address, city, province, postal, phone, email);
        if (validate(book)) {
            addressBookCatalog.add(book);
        }
    }

    /**
     * Update a book of the given phone number if there are records.
     * If there is no record of the given phone number in the file, then there is no updating.
     * @param firstName
     * @param lastName
     * @param companyName
     * @param city
     * @param address
     * @param province
     * @param postal
     * @param phone
     * @param email
     */
    @Override
    public void updateAddressBook(String firstName, String lastName, String companyName, String address, String city, String province, String postal, String phone, String email) {
        AddressBook book = getPeopleInfo(phone);
        if (book!=null && checkRecord(book.getFirstName(),book.getPhone())) {
            book.setLastName(lastName);
            book.setCompanyName(companyName);
            book.setCity(city);
            book.setAddress(address);
            book.setProvince(province);
            book.setPostal(postal);
            book.setPhone(phone);
            book.setEmail(email);
            System.out.println("The record has been updated!");
            sortCatalog();
            WriteToFile();
        } else {
            System.out.println("Sorry~!! There is no record of this person, so you cannot update information for this person!");
            System.out.println("or please check the information you typed in.");
        }
        
    }

    /**
     * Deletes a product with the given code if exists
     * 
     * @param phone
     */
    @Override
    public void deleteRecord(String phone) {
        AddressBook book = getPeopleInfo(phone);
        if (book!=null&&checkRecord(book.getFirstName(),book.getPhone())){
            deletePeopleInfo(book);
            System.out.println("The "+book.getFirstName()+" "+book.getLastName()+"'s record has been deleted!");
            sortCatalog();
            WriteToFile();
        } else {
            System.out.println("Sorry~!! There is no record of this person, so there is no deleting!");
            System.out.println("or please check the information you typed in.");
        }
    }

    /**
     * A String representation setting of the addressBookCatalog
     *
     * @return
     */
    @Override
    public String listPeople() {
        StringBuilder book_sb = new StringBuilder();
        String prefix = "";
        for (AddressBook book : addressBookCatalog) {
            book_sb.append(prefix);
            book_sb.append(book.listToString());
            prefix = "\r\n";
        }
        return book_sb.toString();

    }

    /**
     * Sorting the "addressBookCatalog" based on firstName field.
     */
    @Override
    public void sortCatalog() {
        Collections.sort(addressBookCatalog,AddressBook.compareFirstName);
    }

    /**
     * Deletes a record of a person in the addressBook (used internally)
     *
     * @param book
     */
    private void deletePeopleInfo(AddressBook book) {

        addressBookCatalog.remove(book);
    }

    /**
     * Validates a record for the addressBook before adding
     *
     * @param book
     * @return
     */
    private boolean validate(AddressBook book) {
        if (book.getLastName().length() > MAX_LENGTH_FOR_BOOK_lastName
                || book.getFirstName().length() > MAX_LENGTH_FOR_BOOK_firstName
                || book.getProvince().length() < 0) {
            return false;
        }
        if (addressBookCatalog.contains(book)) {
            return false;
        }
        return true;
    }

    /**
     * Search for the tatal record of a person based on the firstName by using binary search
     *
     * @param phone
     * @return
     */
    private AddressBook getPeopleInfo(String phone) {
        Collections.sort(addressBookCatalog);
        int index = Collections.binarySearch(addressBookCatalog, new AddressBook(phone));
        if (index >= 0) {
            return addressBookCatalog.get(index);
        } else {
            return null;
        }
    }
    
    // Writing the updated records into "newAddressBook.txt" file
    @Override
    public void WriteToFile() {
        File outputFile = new File("newAddressBook.txt");
        int newArrarListsize = addressBookCatalog.size();
        try {
            BufferedWriter writeBuffer = new BufferedWriter(new FileWriter(outputFile));
            //need to add header
            writeBuffer.write("first_Name; last_Name; companyName; address;city;province; postal; phone; email;");
            writeBuffer.newLine();
            for (int i = 0; i < newArrarListsize; i++) {
                writeBuffer.write(addressBookCatalog.get(i).toString());
                writeBuffer.newLine();
            }
            writeBuffer.close();
            sortCatalog();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // For listing the matched records in "fetchPatter()" method
    public String listMatchedRecord() {
        StringBuilder record_sb = new StringBuilder();
        String prefix = "";
        for (String record : matchedRecordArray) {
            record_sb.append(prefix);
            String[] recordFields = record.split(";");
            int recordFieldsNum = 1;
            for (String itemData : recordFields) {
                itemData = itemData.trim();
                switch(recordFieldsNum){
                    case 1: record_sb.append(" First Name: ");
                            record_sb.append(itemData);
                            record_sb.append(",  ");
                            break;
                    case 2: record_sb.append(" Last Name: ");
                            record_sb.append(itemData);
                            record_sb.append(",  ");
                            break;
                    case 3: record_sb.append(" Company Name: ");
                            record_sb.append(itemData);
                            record_sb.append(",  ");
                            break;
                    case 4: record_sb.append(" Address: ");
                            record_sb.append(itemData);
                            record_sb.append(",  ");
                            break;
                    case 5: record_sb.append(" City: ");
                            record_sb.append(itemData);
                            record_sb.append(",  ");
                            break;
                    case 6: record_sb.append(" Province: ");
                            record_sb.append(itemData);
                            record_sb.append(",  ");
                            break;
                    case 7: record_sb.append(" Postal Code: ");
                            record_sb.append(itemData);
                            record_sb.append(",  ");
                            break;
                    case 8: record_sb.append(" Phone Number: ");
                            record_sb.append(itemData);
                            record_sb.append(",  ");
                            break;
                    case 9: record_sb.append(" Email: ");
                            record_sb.append(itemData);
                            record_sb.append(",  ");
                            break;
                }
                recordFieldsNum++;
            }
            prefix = "\r\n";
        }
        return record_sb.toString();
    }
    
    /**
     * Adding new records with given arguments to the catalog ArrayList.
     * If the firstName and the phone of adding record are the same with the record in the file, 
     *    then there is no adding.
     * @param firstName
     * @param lastName
     * @param companyName
     * @param address
     * @param city
     * @param province
     * @param postal
     * @param phone
     * @param email
     */
    @Override
    public void addNewRecord(String firstName, String lastName, String companyName, String address, String city, String province, String postal, String phone, String email) {
        AddressBook book = new AddressBook(firstName, lastName, companyName, address, city, province, postal, phone, email);
        if (!checkRecord(book.getFirstName(),book.getPhone())){
            if (validate(book)) {
                addressBookCatalog.add(book);
                System.out.println("You have added: " + firstName+" "+lastName+"'s record !");
                WriteToFile();
            }
        } else{
            System.out.println("There is a record for this person already, so you cannot add records for this person.");
            System.out.println("Please try 'Update' the record, or check the input information again!");
        }
    }
    /**
     * Find the pattern and return the matched result, if invalid then returns null
     *
     * @param inputFirstName
     * @param inputPhone
     * @return
     */
    @Override
    public boolean checkRecord(String inputFirstName,String inputPhone) {
        int recordsNumber = getTotalRecordsAmount();
        while (recordsNumber>0) {
            String refRecord = addressBookCatalog.get(recordsNumber-1).toString();
            String[] recordFields = refRecord.split(";");
            boolean matchNameCheck = inputFirstName.equals(recordFields[0].trim());
            boolean matchPhoneCheck = inputPhone.equals(recordFields[7].trim());
            recordsNumber--;
            if (matchNameCheck && matchPhoneCheck) {
                //break;
                return true;
            }
        }
        return false;
    }
    // keep running the program until 'exit' input
    @Override
     public boolean runCheck(){
            KeyboardGUI GUI = new KeyboardGUI();
            GUI.GUI();
            return true; 
     }
}
