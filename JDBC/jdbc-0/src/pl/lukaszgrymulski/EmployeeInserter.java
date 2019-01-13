package pl.lukaszgrymulski;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeInserter {
	
	public static void updateEmployees(User user1) {	
		try {
			DBInfo db = new DBInfo();
			Connection connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());			
			Statement statement = connection.createStatement();
			String query = "INSERT INTO employees VALUES (NULL, '"
						+ user1.getFirstName()
						+ "', '"
						+ user1.getLastName()
						+ "', '"
						+ user1.getEmail()
						+ "')";
			statement.executeUpdate(query);
			System.out.println("INSERT COMPLETE");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
