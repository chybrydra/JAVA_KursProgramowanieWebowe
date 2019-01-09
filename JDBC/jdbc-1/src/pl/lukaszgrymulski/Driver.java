package pl.lukaszgrymulski;

import java.sql.*;

public class Driver {

	public static void main(String[] args) {
		try {
			// 1. Get a connection to DB
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
			// 2. Create a statement
			Statement statement = connection.createStatement();
			// 3. Execute query
			ResultSet myResultSet = statement.executeQuery("SELECT * FROM employees");
			// 4. Process the result set
			while(myResultSet.next()) {
				System.out.printf("%d %-8s %-8s %-16s",
								myResultSet.getInt("id"),
								myResultSet.getString("first_name"),
								myResultSet.getString("last_name"),
								myResultSet.getString("email")						
				);
				System.out.println();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
