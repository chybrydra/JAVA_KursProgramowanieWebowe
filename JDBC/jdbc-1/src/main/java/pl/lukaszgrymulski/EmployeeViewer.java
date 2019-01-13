package pl.lukaszgrymulski;

import java.sql.*;

public class EmployeeViewer {

	public static void viewEmployees() {
		try {
			DBInfo db = new DBInfo();
			Connection connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
			Statement statement = connection.createStatement();
			ResultSet myResultSet = statement.executeQuery("SELECT * FROM employees");
			System.out.printf("%-4s %-12s %-12s %-20s\n", "ID", "1ST NAME", "2ND NAME", "EMAIL");
			while(myResultSet.next()) {
				System.out.printf("%-4d %-12s %-12s %-20s\n",
								myResultSet.getInt("id"),
								myResultSet.getString("first_name"),
								myResultSet.getString("last_name"),
								myResultSet.getString("email")						
				);
			}
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
