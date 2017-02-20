package comp5541_pivotTable_RWObj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Comp5541 group: iteration 1 - build pivot table
 * @author aiken 2017/Jan/28
 * @author Eden revisied 2017/Feb/5th
 */
public class Records {

   static DataReader inputData = new DataReader();
    public static int tableRowNum = DataReader.dataRowNum;// total row number of raw data
    public static int tableColNum = DataReader.dataColNum;// total column number of raw data
    public static String[][] pivotTableDataArray = DataReader.totalDataArray; // A 2D string array for storing whole records
    public static String[] titleArray = DataReader.dataTitleArray;
    public static ArrayList<String> selectedFilterIndex = new ArrayList<>();// A array for storing the index of fields as filter which were selected by user
    public static ArrayList<String> selectedFilterRecords = new ArrayList<>();// A array for storing the records which were selected by user
    public static ArrayList<String> selectedRowIndex = new ArrayList<>();
    public static ArrayList<String> selectedRowRecords = new ArrayList<>();
    public static ArrayList<String> selectedColIndex = new ArrayList<>();
    public static ArrayList<String> selectedColRecords = new ArrayList<>();
    public static ArrayList<String> selectedSumIndex = new ArrayList<>();
    public static ArrayList<String> selectedSumRecords = new ArrayList<>();
    // check user input for filter
    public static String[] pivotFilter() {
        String[] selectedNum = new String[2];
        System.out.println("");
        System.out.println("Please enter the number of the field records  as filter you want to select onto the empty spot below the message:");
        System.out.println("or type 'next' to finish selecting:");
        Scanner Type = new Scanner(System.in);
        selectedNum[0] = Type.nextLine();
        selectedNum[1] = "";
        if (selectedNum[0].equals("next")) {
            selectedNum[1] = "next";
            return selectedNum;
        } else if (isInteger(selectedNum[0])) {
            if (Integer.parseInt(selectedNum[0]) < tableColNum) {
                selectedNum[1] = titleArray[Integer.parseInt(selectedNum[0])];
                selectedFilterIndex.add(selectedNum[0]);
                selectedFilterRecords.add(selectedNum[1]);
                return selectedNum;
            } else {
                selectedNum[0] = "Invalid Input.";
                selectedNum[1] = "Please enter the avaible options again!";
                return selectedNum;
            }
        } else {
            selectedNum[0] = "Invalid Input.";
            selectedNum[1] = "Please enter the avaible options again!";
            return selectedNum;
        }
    }
    // check user input for Row
    public static String[] pivotRow() {
       String[] selectedNum = new String[2];
        System.out.println("");
        System.out.println("Please enter the number of the field records as Row label you want to select onto the empty spot below the message:");
        System.out.println("or type 'next' to finish selecting:");
        Scanner Type = new Scanner(System.in);
        selectedNum[0] = Type.nextLine();
        selectedNum[1] = "";
        if (selectedNum[0].equals("next")) {
            selectedNum[1] = "next";
            return selectedNum;
        } else if (isInteger(selectedNum[0])) {
            if (Integer.parseInt(selectedNum[0]) < tableColNum) {
                selectedNum[1] = titleArray[Integer.parseInt(selectedNum[0])];
                selectedRowIndex.add(selectedNum[0]);
                selectedRowRecords.add(selectedNum[1]);
                return selectedNum;
            } else {
                selectedNum[0] = "Invalid Input.";
                selectedNum[1] = "Please enter the avaible options again!";
                return selectedNum;
            }
        } else {
            selectedNum[0] = "Invalid Input.";
            selectedNum[1] = "Please enter the avaible options again!";
            return selectedNum;
        } 
    }
    // check user input for Column
    public static String[] pivotColumn() {
        String[] selectedNum = new String[2];
        System.out.println("");
        System.out.println("Please enter the number of the field records as column label you want to select onto the empty spot below the message:");
        System.out.println("or type 'next' to finish selecting:");
        Scanner Type = new Scanner(System.in);
        selectedNum[0] = Type.nextLine();
        selectedNum[1] = "";
        if (selectedNum[0].equals("next")) {
            selectedNum[1] = "next";
            return selectedNum;
        } else if (isInteger(selectedNum[0])) {
            if (Integer.parseInt(selectedNum[0]) < tableColNum) {
                selectedNum[1] = titleArray[Integer.parseInt(selectedNum[0])];
                selectedColIndex.add(selectedNum[0]);
                selectedColRecords.add(selectedNum[1]);
                return selectedNum;
            } else {
                selectedNum[0] = "Invalid Input.";
                selectedNum[1] = "Please enter the avaible options again!";
                return selectedNum;
            }
        } else {
            selectedNum[0] = "Invalid Input.";
            selectedNum[1] = "Please enter the avaible options again!";
            return selectedNum;
        } 
    }
    // check user input for final sum
    public static String[] pivotSum() {
        String[] selectedNum = new String[2];
        System.out.println("");
        System.out.println("Please enter the number of the field records as final sum label you want to select onto the empty spot below the message:");
        System.out.println("or type 'end' to finish selecting:");
        Scanner Type = new Scanner(System.in);
        selectedNum[0] = Type.nextLine();
        selectedNum[1] = "";
        if (selectedNum[0].equals("end")) {
            selectedNum[1] = "end";
            return selectedNum;
        } else if (isInteger(selectedNum[0])) {
            if (Integer.parseInt(selectedNum[0]) < tableColNum) {
                selectedNum[1] = titleArray[Integer.parseInt(selectedNum[0])];
                selectedSumIndex.add(selectedNum[0]);
                selectedSumRecords.add(selectedNum[1]);
                return selectedNum;
            } else {
                selectedNum[0] = "Invalid Input.";
                selectedNum[1] = "Please enter the avaible options again!";
                return selectedNum;
            }
        } else {
            selectedNum[0] = "Invalid Input.";
            selectedNum[1] = "Please enter the avaible options again!";
            return selectedNum;
        }  
    }
    // Make sure that user input is an integer.
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }   
    
}
