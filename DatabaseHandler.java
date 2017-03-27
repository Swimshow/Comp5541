import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class DatabaseHandler {

	public DatabaseHandler(){

	}

	public static Connection connectToDatabase(String dbms, String serverName, String portNumber, String dbName, String userName, String password) throws SQLException {
		{
			Connection conn = null;
			Properties connectionProps = new Properties();
			connectionProps.put("user", userName);
			connectionProps.put("password", password);

			// Using a driver manager:

			if (dbms.equals("mysql")) {
				//	        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				conn =
						DriverManager.getConnection("jdbc:" + dbms + "://" + serverName +
								":" + portNumber + "/" + dbName,
								connectionProps);
				conn.setCatalog(dbName);
			} else if (dbms.equals("sqlserver")) {
				//	        DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
				conn =
						DriverManager.getConnection("jdbc:" + dbms + "://" + serverName +
								":" + portNumber + ";" + "databasename=" + dbName + ";" + "user=" + userName + ";" +
								"password=" + password);
			}
			System.out.println("Connected to database");
			return conn;
		}
	}
	
	  public static void closeConnection(Connection conn) {
		    System.out.println("Releasing all open resources ...");
		    try {
		      if (conn != null) {
		        conn.close();
		        conn = null;
		    	System.out.println("Connection closed.");

		      }
		    } catch (SQLException sqle) {
		      System.out.println("SQLException" + sqle);
		    }
		    
		  }

		
	/**
	 * Takes in the names of all tables to be joined as a string, wherein 
	 * the tables are separated from each other by a space, a join type (either "join" or "crossjoin"),  
	 * and the name of a shared column to join the tables on (null in case of a crossjoin operation).
	 * Outputs a CSV file, which is saved to the default location and can be then fetched to and parsed in the RawDataReader class. 
	 *   
	 * @param con
	 * @param table1
	 * @param table2
	 * @param joinType
	 * @throws SQLException
	 */

	public static String joinTables(String tables, String joinType, String joinOn) throws SQLException {
		String query = "";
		String[] allTables = tables.split(" ");
		// Joining two tables on a column: 
		if(joinType=="join"){
			if(joinOn == null){ // No column is selected to join on
				System.out.println("Error: no column to join the tables on is specified.");
			}
			else {
				String subquery1 = allTables[0]; // Subquery "TableA JOIN Table B on TableA.ID = TableB.ID"
				for (int i = 0; i< allTables.length; i++){
					if(i==allTables.length-1 && allTables.length>2){ // last element must be joined with the first element
						subquery1 += (" JOIN " + allTables[i] + " ON " + allTables[i] 
								+ "." + joinOn + " = " + allTables[0] + "." + joinOn);
					}
					else {
						if(allTables.length==2 && i==allTables.length-1) break;

						subquery1 += (" JOIN " + allTables[i+1] + " ON " + allTables[i+1] 
								+ "." + joinOn + " = " + allTables[i] + "." + joinOn + " ");
					}
				}
				
				query = "SELECT * FROM " + subquery1; 
			}
		}
		// Cross-joining two tables:
		else if(joinType=="crossjoin"){
			String subquery2 = allTables[0];
			for(String tableName: allTables){
				if(tableName==allTables[0]){ // first table name is already in subquery2; must be skipped
					continue;
				}
				else{
					subquery2 += (" CROSS JOIN " + tableName);
				}
			}
			query = "SELECT * FROM " + subquery2;
		}
		
		return query;

	}

	public static void printJoinedTable(Connection con, String query) throws SQLException{
		Statement stmt = null;

		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int colNum = rsmd.getColumnCount();
			

			for(int i = 1; i<=colNum; i++){ // Printing out the schema
					System.out.print(rsmd.getColumnLabel(i) + "\t");

				}
			System.out.println();
			while(rs.next()){ // Printing out the body of the joined table
				for(int i = 1; i<=colNum; i++){
						System.out.print(rs.getString(i) + "\t");
				}
				System.out.println(); // Empty line between rows
			}

		}
		
		catch (SQLException e) {
			System.out.print(e);
		} finally {
			if (stmt != null) { stmt.close(); }
		}
	}
	
	public static String saveJoinedTableAsCSV(Connection con, String query, String tableName) throws SQLException, FileNotFoundException{
		Statement stmt = null;
		String fullTableName = tableName + ".csv";
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int colNum = rsmd.getColumnCount();
			PrintWriter pw = new PrintWriter(new File(fullTableName));
	        StringBuilder sb = new StringBuilder();
	        String currString;

			for(int i = 1; i<=colNum; i++){ // Printing out the schema
				if(i==colNum){
					sb.append(rsmd.getColumnLabel(i));	
					}
				else{
					sb.append(rsmd.getColumnLabel(i) + ",");
				}	
			}
			
			sb.append("\n"); //Moving to the next line after the schema
				
			while(rs.next()){ // Printing out the body of the joined table
				for(int i = 1; i<=colNum; i++){
					currString = rs.getString(i);
					if(currString.contains("\"")) currString.replace("\"", "\\\""); //escape "
						
					if(currString.contains("\'")) currString.replace("\'", "\\\""); //escape '

					if(currString.contains(",")) currString = "\"" + currString + "\""; //escape ,
					
					if(i==colNum){ // last element
						
						sb.append(currString);
					}
					else{
						
						sb.append(currString + ",");
					}	
						
				}
				sb.append("\n"); // Empty line between rows
			}
			pw.write(sb.toString());
	        pw.close();
		}
		
		catch (SQLException e) {
			System.out.println(e);
		} 
		catch (FileNotFoundException g) {
			System.out.println("Cannot save or open the file.");
			fullTableName = tableName + "1";
			System.out.println("New file name: " + fullTableName);
			
			
		}
		finally {
			if (stmt != null) { stmt.close(); }
		}
		return fullTableName;
	}
}
	

