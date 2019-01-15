package pl.lukaszgrymulski;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeUpdater {
	
	
	public static void updateEmployee(String update, String where) {
		try {
			DBInfo db = new DBInfo();
			Connection connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());			
			Statement statement = connection.createStatement();
			String query = "UPDATE employees SET "+update+" WHERE "+where;
			statement.executeUpdate(query);		
			System.out.println("UPDATE COMPLETE");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
}
