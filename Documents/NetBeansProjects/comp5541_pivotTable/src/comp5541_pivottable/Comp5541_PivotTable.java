
package comp5541_pivottable;

import comp5541_pivotTable_db.PivotTableData;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**Comp5541 group:
 * comp5541 iteration 1 - build pivot table @ aiken 2017/Jan/28
 */
public class Comp5541_PivotTable {
   
    public static void main(String[] args) throws IOException, FileNotFoundException, InvalidFormatException {
        UserInterface UI_Start = new UserInterface();
        PivotTableData tableData = new PivotTableData();
        System.out.println("");
        System.out.println("There are "+tableData.tableColNum+" fields and "+tableData.tableRowNum+" records in the input data.");
        System.out.println("");
        System.out.println("Fields of records are: ");
        // Display avaible records for user
        for (int i=0;i<tableData.tableColNum;i++){
            System.out.print(i+"->"+tableData.titleArray[i]+"; ");
        }
       UI_Start.UI_begin(); 
    }
    
}
