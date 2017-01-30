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
 */
public class PivotTableData {

    public int tableRowNum;
    public int tableColNum;
    public String[][] pivotTableDataArray;
    public String[] titleArray;
    public ArrayList<String> selectedRecordIndex = new ArrayList<>();
    public ArrayList<String> selectedRecord = new ArrayList<>();

    /**
     * Initializing the pivot table data
     */
    public PivotTableData() {
        try {
            //String inputFileName = "customers.csv"; 
            File inputFile = new File("customers.csv");
            // A connection stream connects to the csv file
            FileReader fileReader = new FileReader(inputFile);
            // A file pointer always points to the csv file
            BufferedReader filePointer = new BufferedReader(fileReader);
            String recordInput;
            // "dataArray" is an ArrayList data structure for storing records of the input csv file
            List<String> dataArray = new ArrayList<>();
            while ((recordInput = filePointer.readLine()) != null) {
                dataArray.add(recordInput);
            }
            // Specify how many rows are there in the csv file records
            int dataRowNumber = dataArray.size();
            this.tableRowNum = dataRowNumber;
            // Specify how many fields(columns) and what fields are there in the csv file records
            String fieldsData = dataArray.get(0);
            String[] recordFields = fieldsData.split(",");
            this.titleArray = recordFields;
            int dataColumnNumber = 0;
            // counting the number of fields
            for (String itemData : recordFields) {
                itemData = itemData.trim();
                dataColumnNumber++;
            }
            this.tableColNum = dataColumnNumber;

            // Creating a string array for all of the csv file records
            String[][] pivotDataArray = new String[dataRowNumber][dataColumnNumber];
            for (int i = 0; i < dataRowNumber; i++) {
                String rowData = dataArray.get(i);
                String[] dataMarker = rowData.split(",");
                int indexCounter = 0;
                for (String itemData : dataMarker) {
                    itemData = itemData.trim();
                    pivotDataArray[i][indexCounter] = itemData;
                    indexCounter++;
                }
            }
            this.pivotTableDataArray = pivotDataArray;

        } catch (Exception ex) {
            ex.printStackTrace();
        } // end of try
    }

    // get total row number of data
    public int getRowNumber() {
        int rowNum;
        rowNum = pivotTableDataArray[0].length;
        return rowNum;
    }

    // get total column number of data 
    public int getColumnNumber() {
        int colNum;
        colNum = pivotTableDataArray[1].length;
        return colNum;
    }

    // check user input
    public String[] pivotInput() {
        String[] selectedNum = new String[2];
        System.out.println("");
        System.out.println("Please enter the number of the field records you want to select onto the empty spot below the message:");
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
                selectedRecordIndex.add(selectedNum[0]);
                selectedRecord.add(selectedNum[1]);
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

    // Make sure that user inputs an integer.
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }
}
