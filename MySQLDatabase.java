import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLDatabase extends DatabaseHandler {
	
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
	public Connection connectToDatabase(String serverName, String portNumber, String dbName, String userName, String password) throws SQLException {
		{
			Connection conn = null;
			Properties connectionProps = new Properties();
			connectionProps.put("user", userName);
			connectionProps.put("password", password);

			// Using a driver manager:

			
				//	        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				conn =
						DriverManager.getConnection("jdbc:mysql://" + serverName +
								":" + portNumber + "/" + dbName,
								connectionProps);
				conn.setCatalog(dbName);
			
			System.out.println("Connected to database");
			return conn;
		}
	}

	// Shows all tables in the database by printing their details to the screen.
	public void showTables(Connection con, String dbName) throws SQLException{
		String query = ("SHOW TABLES IN " + dbName);

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
	public void showTableSchema(Connection con, String tableName){
		String query = ("desc " + tableName);

		try{
			if(query!=null) executeQuery(con, query);
		}
		catch (SQLException e) {
			System.out.println(e);
		}
	}
}
