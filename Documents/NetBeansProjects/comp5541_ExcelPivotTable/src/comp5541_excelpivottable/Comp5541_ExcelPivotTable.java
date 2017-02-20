
package comp5541_excelpivottable;

import comp5541_pivotTable_RWObj.Records;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**Comp5541 group:
 * comp5541 iteration 1 - build pivot table @ aiken 2017/Jan/28
 *                                  revised @ Eden
 */
public class Comp5541_ExcelPivotTable {
   
    public static void main(String[] args) throws IOException, FileNotFoundException, InvalidFormatException {
        System.out.println("");
        System.out.println("There are "+Records.tableColNum+" fields and "+Records.tableRowNum+" records in the input data.");
        System.out.println("");
        System.out.println("Fields of records are: ");
        // Display avaible records for user
        for (int i=0;i<Records.tableColNum;i++){
            System.out.print(i+"->"+Records.titleArray[i]+"; ");
        }
       UserSelectedData.UI_begin();
    }
    
}
