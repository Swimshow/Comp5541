import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;


public class Driver {

	public static void main(String[] args){



		try {
			Connection conn1 = DatabaseHandler.connectToDatabase("sqlserver", "0.tcp.ngrok.io", "14759", "master", "sa", "P@55w0rd");
			String tables = "COFFEES SUPPLIERS";
			String query = DatabaseHandler.joinTables(tables, "join", "SUP_ID");
			DatabaseHandler.printJoinedTable(conn1, query);
			DatabaseHandler.saveJoinedTableAsCSV(conn1, query, "testJoin");

			String tables2 = "COFFEES SUPPLIERS MSreplication_options";
			System.out.println();
			String query2 = DatabaseHandler.joinTables(tables2, "crossjoin", "");  
			DatabaseHandler.printJoinedTable(conn1, query2);
			DatabaseHandler.saveJoinedTableAsCSV(conn1, query2, "testJoin");
			DatabaseHandler.closeConnection(conn1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		DataSet.setData(RawDataReader.loadRawRecords("MOCK_TXT.csv"));

		DataSet.setFields(RawDataReader.readFields("MOCK_TXT.csv"));

		int[] f = {3,0,2}; //row + column selections

		PivotTree tree = new PivotTree(f, DataSet.getDataSet());

		int[] r = {3,0};  //row selections

		int[] c = {2};   //column selections 
		
		

		LabelTree row = new LabelTree(r, DataSet.getDataSet());

		LabelTree col = new LabelTree(c, DataSet.getDataSet());

		String[][] test = TablePrinter.printToArray(tree, row, col, -1, -1);  //return a 2D array of table


		/*for(int i = 0; i < 5; i++){				//print array to console (test)

			for(int x = 0; x < 5; x++){
				System.out.print(test[i][x] + "\t");
			}

			System.out.println();

		}*/

		for(String[] x : test){
			for(String s : x){
				System.out.print(s + " ");
			}
			System.out.println();
		}





	}
}