import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;


public class Driver {

	public static void main(String[] args){

		SQLServerDatabase abc = new SQLServerDatabase();

		try {
			Connection conn1 = abc.connectToDatabase("0.tcp.ngrok.io", "19501", "master", "sa", "P@55w0rd");
			
			System.out.println("Showing tables in database master");
			abc.showTables(conn1, "master");
			//TimeUnit.SECONDS.sleep(15);
			System.out.println();
			System.out.println("Showing the table schema for COFFEES...");
			abc.showTableSchema(conn1, "COFFEES");
			//TimeUnit.SECONDS.sleep(10);
			System.out.println();
			System.out.println("Showing the table schema for SUPPLIERS...");
			abc.showTableSchema(conn1, "SUPPLIERS");
			//TimeUnit.SECONDS.sleep(10);
			System.out.println();
			System.out.println("Showing the table schema for MERCH_INVENTORY...");
			abc.showTableSchema(conn1, "MERCH_INVENTORY");
			//TimeUnit.SECONDS.sleep(10);
			
			
			String tables = "COFFEES SUPPLIERS";
			String query = DatabaseHandler.joinTables(tables, "join", "SUP_ID");
			DatabaseHandler.printJoinedTable(conn1, query, "SUP_ID");
			DatabaseHandler.saveJoinedTableAsCSV(conn1, query, "testJoinTwoTables", "SUP_ID");
			//TimeUnit.SECONDS.sleep(15);


			String tables2 = "COFFEES SUPPLIERS";
			System.out.println();
			String query2 = DatabaseHandler.joinTables(tables2, "crossjoin", "");  
			DatabaseHandler.printJoinedTable(conn1, query2, "SUP_ID");
			DatabaseHandler.saveJoinedTableAsCSV(conn1, query2, "testCrossJoin1", "SUP_ID");
			//TimeUnit.SECONDS.sleep(15);


			tables = "COFFEES SUPPLIERS MERCH_INVENTORY";
			query = DatabaseHandler.joinTables(tables, "join", "SUP_ID");
			DatabaseHandler.printJoinedTable(conn1, query, "SUP_ID");
			DatabaseHandler.saveJoinedTableAsCSV(conn1, query, "testJoinThreeTables", "SUP_ID");
			//TimeUnit.SECONDS.sleep(15);

			tables = "COFFEES SUPPLIERS MERCH_INVENTORY";
			query = DatabaseHandler.joinTables(tables, "outerjoin", "SUP_ID");
			DatabaseHandler.printJoinedTable(conn1, query, "SUP_ID");
			DatabaseHandler.saveJoinedTableAsCSV(conn1, query, "testOuterJoinThreeTables", "SUP_ID");
			//TimeUnit.SECONDS.sleep(15);



			DatabaseHandler.closeConnection(conn1);
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//catch (InterruptedException e) {
			
			//e.printStackTrace();
		//}


		System.out.println();
		
		DataSet.setData(RawDataReader.loadRawRecords("testJoinThreeTables.csv"));

		DataSet.setFields(RawDataReader.readFields("testJoinThreeTables.csv"));

		int[] f = {0,7,5}; //row + column selections

		PivotTree tree = new PivotTree(f, DataSet.getDataSet());

		int[] r = {0};  //row selections

		int[] c = {7,5};   //column selections 
		
		

		LabelTree row = new LabelTree(r, DataSet.getDataSet());

		LabelTree col = new LabelTree(c, DataSet.getDataSet());

		String[][] test = TablePrinter.printToArray(tree, row, col, -1, 2);  //return a 2D array of table


		/*for(int i = 0; i < 5; i++){				//print array to console (test)

			for(int x = 0; x < 5; x++){
				System.out.print(test[i][x] + "\t");
			}

			System.out.println();

		}*/
		HTMLFileGenerator gen2 = new HTMLFileGenerator("test");
		gen2.generateHTML(test, "newTable",1,2);
		//for(String[] x : test){
			//for(String s : x){
				//System.out.print(s + " ");
			//}
			//System.out.println();
		//}

		
		
		DataSet.setData(RawDataReader.loadRawRecords("MOCK_TXT.csv"));

		DataSet.setFields(RawDataReader.readFields("MOCK_TXT.csv"));

		int[] f1 = {3,0,2}; //row + column selections

		PivotTree tree1 = new PivotTree(f1, DataSet.getDataSet());

		int[] r1 = {3,0};  //row selections

		int[] c1 = {2};   //column selections 
		
		

		LabelTree row1 = new LabelTree(r1, DataSet.getDataSet());

		LabelTree col1 = new LabelTree(c1, DataSet.getDataSet());

		String[][] test1 = TablePrinter.printToArray(tree1, row1, col1, -1, -1);  //return a 2D array of table

		HTMLFileGenerator gen3 = new HTMLFileGenerator("test1");
		gen3.generateHTML(test1, "newTable2",1,2);
		
	





	}
}