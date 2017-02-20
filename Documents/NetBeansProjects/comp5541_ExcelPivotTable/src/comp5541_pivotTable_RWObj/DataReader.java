
package comp5541_pivotTable_RWObj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aiken
 */
public class DataReader {
    public static int dataRowNum;// total row number of raw data
    public static int dataColNum;// total column number of raw data
    public static String[][]totalDataArray; // A 2D string array for storing whole records
    public static String[] dataTitleArray;
    
    public DataReader(){
        try {
            //String inputFileName = "customers.csv"; 
            File inputFile = new File("salesData.csv");
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
            DataReader.dataRowNum = dataRowNumber;
            // Specify how many fields(columns) and what fields are there in the csv file records
            String fieldsData = dataArray.get(0);
            String[] recordFields = fieldsData.split(",");
            DataReader.dataTitleArray = recordFields;
            int dataColumnNumber = 0;
            // counting the number of fields
            for (String itemData : recordFields) {
                itemData = itemData.trim();
                dataColumnNumber++;
            }
            DataReader.dataColNum = dataColumnNumber;

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
            DataReader.totalDataArray = pivotDataArray;
        } catch (Exception ex) {
        } // end of try
    }
}
