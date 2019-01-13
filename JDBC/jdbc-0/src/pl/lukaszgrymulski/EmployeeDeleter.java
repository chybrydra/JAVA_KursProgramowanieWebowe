package pl.lukaszgrymulski;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeDeleter{
	
	
	public static void deleteEmployee(String whereValue) {	
		
		try {
			DBInfo db = new DBInfo();
			Connection connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());			
			Statement statement = connection.createStatement();
			String query = "DELETE FROM employees WHERE " + whereValue;
			int rowsAffected = statement.executeUpdate(query);		
			System.out.println("rows affected: " + rowsAffected);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
}
