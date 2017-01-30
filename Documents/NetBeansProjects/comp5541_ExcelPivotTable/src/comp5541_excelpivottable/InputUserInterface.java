package comp5541_excelpivottable;

import comp5541_pivotTable_RWObj.PivotTableData;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Comp5541 group: iteration 1 - build pivot table
 * @author aiken 2017/Jan/28
 */
public class InputUserInterface {

    PivotTableData tableData = new PivotTableData();

    public void UI_begin() throws FileNotFoundException, IOException, InvalidFormatException {

        String userInput[] = tableData.pivotInput();
        String status = userInput[0];
        System.out.println("Selected number = " + userInput[0]);
        System.out.println("Selected Field = " + userInput[1]);
        // User selected data fields
        switch (status) {
            case "end":
                // Creating string array for user selected pivot table
                String[][] userSelectedData = new String[tableData.tableRowNum][tableData.selectedRecordIndex.size()];
                // Put field of records in the first row
                for (int i = 0; i < tableData.selectedRecordIndex.size(); i++) {
                    userSelectedData[0][i] = tableData.titleArray[Integer.parseInt(tableData.selectedRecordIndex.get(i))];
                }
                // Fill in the rest of user selected data
                System.out.println(tableData.selectedRecordIndex.size());
                for (int j = 0; j < tableData.selectedRecordIndex.size(); j++) {
                    for (int k = 1; k < tableData.tableRowNum; k++) {
                        userSelectedData[k][j] = tableData.pivotTableDataArray[k][Integer.parseInt(tableData.selectedRecordIndex.get(j))];
                    }
                }
                //Create excel file for user selected pivot data
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("Pivot Table");
                int rowCount = 0;
                // Filling user selected data into .xlsx file 
                for (Object[] rowData : userSelectedData) {
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
                } // end of filling user selected data
                try (FileOutputStream outputStream = new FileOutputStream("pivotTableFile.xlsx")) {
                    workbook.write(outputStream);
                }
                System.out.println("The selected fields have been complied in an excel file (pivotTableFile.xlsx) !!");
                System.exit(0);
            case "typo":
                UI_begin();
            default:
                UI_begin();
        }
    }
}
