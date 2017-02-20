
package comp5541_pivotTable_RWObj;

import comp5541_excelpivottable.UserSelectedData;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 *
 * @author aiken
 */
public class DataExport {
    public String[][] userSelectingData = ExcelExport.dataSort(UserSelectedData.userSelectedFilterData);
    public String[][] userSelectingRowData = UserSelectedData.userSelectedRowData;
    public String[][] userSelectingColData = UserSelectedData.userSelectedColData;
    public String[][] userSelectingSumData = UserSelectedData.userSelectedSumData;
    public static void DataExport(String[][] inputString) throws FileNotFoundException{
        ArrayList dataArrayList = new ArrayList<>();
        for (int i=0;i<inputString.length;i++) {
            StringBuilder str = new StringBuilder();
            String prefix = "";
            for(int j=0;j<inputString[0].length;j++){
                str.append(prefix);
                str.append(inputString[i][j]);
                str.append(",");
            }
            dataArrayList.add(str.toString());
        }
        PrintStream outData = new PrintStream(new FileOutputStream("outputTxetFile.txt"));
         //output to the file a line
        for(int k=0;k<dataArrayList.size();k++){
            outData.println(dataArrayList.get(k).toString());
        }
        //close the file
        outData.close();
    }
}
