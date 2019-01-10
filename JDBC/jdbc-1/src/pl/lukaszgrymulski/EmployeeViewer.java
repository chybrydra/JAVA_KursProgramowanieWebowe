package pl.lukaszgrymulski;

import java.sql.*;

public class EmployeeViewer {

	public static void viewEmployees() {
		try {
			DBInfo db = new DBInfo();
			Connection connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
			Statement statement = connection.createStatement();
			ResultSet myResultSet = statement.executeQuery("SELECT * FROM employees");
			while(myResultSet.next()) {
				System.out.printf("%d %-8s %-8s %-16s",
								myResultSet.getInt("id"),
								myResultSet.getString("first_name"),
								myResultSet.getString("last_name"),
								myResultSet.getString("email")						
				);
				System.out.println();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
