package comp5541_pivotTable_RWObj;

import comp5541_excelpivottable.UserSelectedData;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author aiken
 */
public class ExcelExport {

    public String[][] userSelectingData = ExcelExport.dataSort(UserSelectedData.userSelectedFilterData);
    public String[][] userSelectingRowData = UserSelectedData.userSelectedRowData;
    public String[][] userSelectingColData = UserSelectedData.userSelectedColData;
    public String[][] userSelectingSumData = UserSelectedData.userSelectedSumData;

    public void excelOutput() throws FileNotFoundException, IOException {
        //Create excel file for user selected pivot data
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("pivotTable");
        int rowCount = 0;
        // Filling user selected data into .xlsx file 
        for (Object[] rowData : userSelectingData) {
            Row row = sheet.createRow(++rowCount);
            int columnCount = 0;
            for (Object field : rowData) {
                Cell cell = row.createCell(++columnCount);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }
        XSSFSheet sheet2 = workbook.createSheet("SelectedRow");
        int rowCount2 = 0;
        // Filling user selected data into .xlsx file 
        for (Object[] rowData2 : userSelectingRowData) {
            Row row2 = sheet2.createRow(++rowCount2);
            int columnCount2 = 0;
            for (Object field : rowData2) {
                Cell cell = row2.createCell(++columnCount2);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }
        XSSFSheet sheet3 = workbook.createSheet("SelectedColumn");
        int rowCount3 = 0;
        // Filling user selected data into .xlsx file 
        for (Object[] rowData3 : userSelectingColData) {
            Row row3 = sheet3.createRow(++rowCount3);
            int columnCount3 = 0;
            for (Object field : rowData3) {
                Cell cell = row3.createCell(++columnCount3);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        } // end of filling user selected data
        XSSFSheet sheet4 = workbook.createSheet("finalSum");
        int rowCount4 = 0;
        // Filling user selected data into .xlsx file 
        for (Object[] rowData4 : userSelectingSumData) {
            Row row4 = sheet4.createRow(++rowCount4);
            int columnCount4 = 0;
            for (Object field : rowData4) {
                Cell cell = row4.createCell(++columnCount4);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        } // end of filling user selected data
        try (FileOutputStream outputStream = new FileOutputStream("pivotTableUserSelected.xlsx")) {
            workbook.write(outputStream);
        }
        System.out.println("The selected fields have been complied in an excel file (userSelectedData.xlsx) !!");
    }

    public static String[][] dataSort(String[][] inputData) {
        String[][] inputDataNoTitle = new String[inputData.length-1][inputData[0].length];
        
        for(int i=0;i<(inputData.length-1);i++){
            
            for(int j=0;j<inputData[0].length;j++){
                inputDataNoTitle[i][j] = inputData[i+1][j];
            }
        }
        ArrayList dataArrayList = new ArrayList<>();
        for (int i=0;i<inputDataNoTitle.length;i++) {
            StringBuilder str = new StringBuilder();
            String prefix = "";
            for(int j=0;j<inputDataNoTitle[0].length;j++){
                str.append(prefix);
                str.append(inputDataNoTitle[i][j]);
                str.append(",");
                prefix = "\r\n";
            }
            dataArrayList.add(str.toString());
        }
        Collections.sort(dataArrayList);        
        String[][] sortedInputData = new String[dataArrayList.size()+1][inputData[0].length];
        for(int k=0;k<inputData[0].length;k++){
            sortedInputData[0][k] = inputData[0][k];
        }
        System.out.println("sortedInputData.length= "+sortedInputData.length);
        for (int m = 0; m < (sortedInputData.length-1); m++) {
            String row = dataArrayList.get(m).toString();
            String[] rowArray = row.split(",");
            sortedInputData[m+1] = rowArray;
        }
        return sortedInputData;
    }
}
