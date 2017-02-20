package comp5541_excelpivottable;

import comp5541_pivotTable_RWObj.DataExport;
import comp5541_pivotTable_RWObj.ExcelExport;
import comp5541_pivotTable_RWObj.Records;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Comp5541 group: iteration 1 - build pivot table
 * @author aiken 2017/Jan/28
 */
public class UserSelectedData {
    public static String[][] userSelectedFilterData;
    public static ArrayList<String> userSelectedFilterDataIndex;
    public static String[][] userSelectedRowData;
    public static ArrayList<String> userSelectedRowDataIndex;
    public static String[][] userSelectedColData;
    public static ArrayList<String> userSelectedColDataIndex;
    public static String[][] userSelectedSumData;
    public static ArrayList<String> userSelectedSumDataIndex;
   
    public static void UI_begin() throws FileNotFoundException, IOException {

        String userInput[] = Records.pivotFilter();
        String status = userInput[0];
        System.out.println("Selected number = " + userInput[0]);
        System.out.println("Selected Filter = " + userInput[1]);
        // User selected data fields
        switch (status) {
            case "next":
                // Creating string array for user selected pivot table
                userSelectedFilterData = new String[Records.tableRowNum][Records.selectedFilterIndex.size()];
                // Put field of records in the first row
                for (int i = 0; i < Records.selectedFilterIndex.size(); i++) {
                    userSelectedFilterData[0][i] = Records.titleArray[Integer.parseInt(Records.selectedFilterIndex.get(i))];
                }
                // Fill in the rest of user selected data
                for (int j = 0; j < Records.selectedFilterIndex.size(); j++) {
                    for (int k = 1; k < Records.tableRowNum; k++) {
                        userSelectedFilterData[k][j] = Records.pivotTableDataArray[k][Integer.parseInt(Records.selectedFilterIndex.get(j))];
                    }
                }
                userSelectedFilterDataIndex = Records.selectedFilterIndex;
                // export excel file
                UI_row();
            case "typo":
                UI_begin();
            default:
                UI_begin();
        }
    }
    // asking user for row label 
    public static void UI_row() throws IOException {
        for (int i = 0; i < Records.tableColNum; i++) {
            System.out.print(i + "->" + Records.titleArray[i] + "; ");
        }
        String userInput[] = Records.pivotRow();
        String status = userInput[0];
        System.out.println("Selected number = " + userInput[0]);
        System.out.println("Selected records for row label = " + userInput[1]);
        // User selected data fields
        switch (status) {
            case "next":
                // Creating string array for user selected pivot table
                userSelectedRowData = new String[Records.tableRowNum][Records.selectedRowIndex.size()];
                // Put field of records in the first row
                for (int i = 0; i < Records.selectedRowIndex.size(); i++) {
                    userSelectedRowData[0][i] = Records.titleArray[Integer.parseInt(Records.selectedRowIndex.get(i))];
                }
                // Fill in the rest of user selected data
                for (int j = 0; j < Records.selectedRowIndex.size(); j++) {
                    for (int k = 1; k < Records.tableRowNum; k++) {
                        userSelectedRowData[k][j] = Records.pivotTableDataArray[k][Integer.parseInt(Records.selectedRowIndex.get(j))];
                    }
                }
                userSelectedRowDataIndex = Records.selectedRowIndex;
                UI_column();
            case "typo":
                UI_row();
            default:
                UI_row();
        }
    }
    // asking user for column label 
    public static void UI_column() throws IOException {
        for (int i = 0; i < Records.tableColNum; i++) {
            System.out.print(i + "->" + Records.titleArray[i] + "; ");
        }
        String userInput[] = Records.pivotColumn();
        String status = userInput[0];
        System.out.println("Selected number = " + userInput[0]);
        System.out.println("Selected records for column label = " + userInput[1]);
        // User selected data fields
        switch (status) {
            case "next":
                // Creating string array for user selected pivot table
                userSelectedColData = new String[Records.tableRowNum][Records.selectedColIndex.size()];
                // Put field of records in the first row
                for (int i = 0; i < Records.selectedColIndex.size(); i++) {
                    userSelectedColData[0][i] = Records.titleArray[Integer.parseInt(Records.selectedColIndex.get(i))];
                }
                // Fill in the rest of user selected data
                for (int j = 0; j < Records.selectedColIndex.size(); j++) {
                    for (int k = 1; k < Records.tableRowNum; k++) {
                        userSelectedColData[k][j] = Records.pivotTableDataArray[k][Integer.parseInt(Records.selectedColIndex.get(j))];
                    }
                }
                userSelectedColDataIndex = Records.selectedColIndex;
                UI_sum();
            case "typo":
                UI_column();
            default:
                UI_column();
        }
    }
    // asking user for final sum 
    public static void UI_sum() throws IOException {
       for (int i = 0; i < Records.tableColNum; i++) {
            System.out.print(i + "->" + Records.titleArray[i] + "; ");
        }
        String userInput[] = Records.pivotSum();
        String status = userInput[0];
        System.out.println("Selected number = " + userInput[0]);
        System.out.println("Selected records for final sum label = " + userInput[1]);
        // User selected data fields
        switch (status) {
            case "end":
                // Creating string array for user selected pivot table
                userSelectedSumData = new String[Records.tableRowNum][Records.selectedSumIndex.size()];
                // Put field of records in the first row
                for (int i = 0; i < Records.selectedSumIndex.size(); i++) {
                    userSelectedSumData[0][i] = Records.titleArray[Integer.parseInt(Records.selectedSumIndex.get(i))];
                }
                // Fill in the rest of user selected data
                for (int j = 0; j < Records.selectedSumIndex.size(); j++) {
                    for (int k = 1; k < Records.tableRowNum; k++) {
                        userSelectedSumData[k][j] = Records.pivotTableDataArray[k][Integer.parseInt(Records.selectedSumIndex.get(j))];
                    }
                }
                userSelectedSumDataIndex = Records.selectedSumIndex;
                // export excel file
                ExcelExport output = new ExcelExport();
                output.excelOutput();
                DataExport.DataExport(userSelectedFilterData);
                System.exit(0);
            case "typo":
                UI_sum();
            default:
                UI_sum();
        }
    } 
}
