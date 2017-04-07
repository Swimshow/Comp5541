import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLServerDatabase extends DatabaseHandler {

	/**
	 * Connects to a database using the provided parameters.
	 * 
	 * @param serverName - server address
	 * @param portNumber - port number
	 * @param dbName - database name
	 * @param userName 
	 * @param password 
	 * @return
	 * @throws SQLException
	 */
	
	public Connection connectToDatabase(String serverName, String portNumber, String dbName, String userName, String password) throws SQLException{
		{
			Connection conn = null;
			Properties connectionProps = new Properties();
			connectionProps.put("user", userName);
			connectionProps.put("password", password);

			// Using a driver manager:
				conn =
						DriverManager.getConnection("jdbc:sqlserver://" + serverName +
								":" + portNumber + ";" + "databasename=" + dbName + ";" + "user=" + userName + ";" +
								"password=" + password);
			
			System.out.println("Connected to database");
			return conn;
		}
	}
	
	// Shows all tables in the database by printing their details to the screen.
	public void showTables(Connection con, String dbName) throws SQLException{
		String query = ("SELECT * FROM " + dbName + ".INFORMATION_SCHEMA.TABLES");
		
		try{
			if(query!=null) executeQuery(con, query);
		}
		catch (SQLException e) {
			System.out.println(e);
		}
	}

	/**
	 * Shows the schema for the specified table. 
	 * @param con - connection
	 * @param tableName
	 * 
	 */
	public void showTableSchema(Connection con, String tableName){
		String query = ("sp_columns " + tableName);

		try{
			if(query!=null) executeQuery(con, query);
		}
		catch (SQLException e) {
			System.out.println(e);
		}
	}
}




