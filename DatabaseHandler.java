import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import jdbcTools.JDBCTutorialUtilities;

public class DatabaseHandler {

	public DatabaseHandler(){

	}
	/**
	 * Accepts a connection, a database name and database management system name ("sqlserver" or "mysql") and generates 
	 * the query retrieving the list of all table in the database.
	 * @param dbName
	 * @param dbms - "sqlserver" or "mysql"
	 */
	public static void showTables(Connection con, String dbName, String dbms) throws SQLException{
		String query = null;
		if(dbms=="sqlserver") query = ("SELECT * FROM " + dbName + ".INFORMATION_SCHEMA.TABLES");
		else if(dbms=="mysql") query = ("SHOW TABLES IN " + dbName);
		else System.out.println("Unknown database type.");

		try{
			if(query!=null) executeQuery(con, query);
		}
		catch (SQLException e) {
			System.out.println(e);
		}
	}

	/**
	 * Shows the schema for a specified table. 
	 * @param con - connection
	 * @param tableName
	 * @param dbms - "sqlserver" or "mysql"
	 */
	public static void showTableSchema(Connection con, String tableName, String dbms){
		String query = null;
		if(dbms=="sqlserver") query = ("sp_columns " + tableName);
		else if(dbms=="mysql") query = ("desc " + tableName);
		else System.out.println("Unknown database type.");

		try{
			if(query!=null) executeQuery(con, query);
		}
		catch (SQLException e) {
			System.out.println(e);
		}
	}
	/**
	 * Takes in a query and a connection, executes it and prints out the result.
	 * @param query
	 */
	public static void executeQuery(Connection con, String query) throws SQLException{
		Statement stmt = null;

		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int colNum = rsmd.getColumnCount();

			while(rs.next()){ // Printing out the body of the joined table
				for(int i = 1; i<=colNum; i++){
					System.out.print(rs.getString(i) + "\t");
				}
				System.out.println(); // Empty line between rows
			}
		}
		catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) { stmt.close(); }
		}


	}

	/**
	 * Connects to a database using the provided parameters.
	 * @param dbms - database management system, either "sqlserver" or "mysql"
	 * @param serverName - server address
	 * @param portNumber - port number
	 * @param dbName - database name
	 * @param userName 
	 * @param password 
	 * @return
	 * @throws SQLException
	 */
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
		String[] allTables = tables.split(" "); // array of table names
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
						if(allTables.length==2 && i==allTables.length-1) break; // the case with two tables only 
						if(i==allTables.length-2) continue;

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

	public static void printJoinedTable(Connection con, String query, String joinOn) throws SQLException{
		Statement stmt = null;

		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int colNum = rsmd.getColumnCount();
			ArrayList duplicateColumns = new ArrayList(); // list of the duplicate columns resulting from a Join with SELECT * 

			for(int i = 1; i<=colNum; i++){ //Building the list of indices for the duplicate columns
				if(rsmd.getColumnLabel(i).equalsIgnoreCase(joinOn)){
					duplicateColumns.add(i);
				}
			}

			for(int i = 1; i<=colNum; i++){ // Printing out the schema
				// Skip all but the first occurrence of a duplicate column
				if(rsmd.getColumnLabel(i).equalsIgnoreCase(joinOn) && i!= (Integer)duplicateColumns.get(0)){ 
					continue;
				}
				System.out.print(rsmd.getColumnLabel(i) + "\t");

			}
			System.out.println();
			while(rs.next()){ // Printing out the body of the joined table
				for(int i = 1; i<=colNum; i++){
					if(rsmd.getColumnLabel(i).equalsIgnoreCase(joinOn) && i!= (Integer)duplicateColumns.get(0)){ 
						continue;
					}
					System.out.print(rs.getString(i) + "\t");
				}
				System.out.println(); // Empty line between rows
			}

		}

		catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) { stmt.close(); }
		}
	}

	public static String saveJoinedTableAsCSV(Connection con, String query, String tableName, String joinOn) throws SQLException, FileNotFoundException{
		Statement stmt = null;
		String fullTableName = tableName + ".csv";
		ArrayList duplicateColumns = new ArrayList(); // list of the duplicate columns resulting from a Join with SELECT * 


		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int colNum = rsmd.getColumnCount();
			PrintWriter pw = new PrintWriter(new File(fullTableName));
			StringBuilder sb = new StringBuilder();
			String currString;

			for(int i = 1; i<=colNum; i++){ //Building the list of indices for the duplicate columns
				if(rsmd.getColumnLabel(i).equalsIgnoreCase(joinOn)){
					duplicateColumns.add(i);
				}
			}
			for(int i = 1; i<=colNum; i++){ // Printing out the schema
				if(i==colNum && (rsmd.getColumnLabel(i).equalsIgnoreCase(joinOn)==false|i== (Integer)duplicateColumns.get(0))){
					sb.append(rsmd.getColumnLabel(i));	
				}
				else if(rsmd.getColumnLabel(i).equalsIgnoreCase(joinOn)==false | i== (Integer)duplicateColumns.get(0)){

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

					if(i==colNum && (rsmd.getColumnLabel(i).equalsIgnoreCase(joinOn)==false | i== (Integer)duplicateColumns.get(0))){

						sb.append(currString);
					}
					else if(rsmd.getColumnLabel(i).equalsIgnoreCase(joinOn)==false | i== (Integer)duplicateColumns.get(0)){

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


