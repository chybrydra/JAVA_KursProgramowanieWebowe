package pl.lukaszgrymulski;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IncreaseSalariesForDepartment {
	
	public static void main(String[] args) {
		try {
			DBInfo db = new DBInfo();
			Connection connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
			
			String theDepartment = "Engineering";
			int theIncreaseAmount = 10000;
			
			//Show salaries before
			System.out.print("SALARIES BEFORE:\n\n");
			showSalaries(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	static void showSalaries(Connection connection){
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees2");
			ResultSet resultSet = statement.executeQuery();
			printResultSet(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static void printResultSet(ResultSet resultSet) {
		try {
			System.out.printf("%-4s %-12s %-12s %-24s %-12s %-6.2s\n", "ID", "LAST_NAME", "FIRST_NAME", "EMAIL", "DEPARTMENT", "SALARY");
			while (resultSet.next()) {
				System.out.printf("%-4d %-12s %-12s %-24s %-12s %-6.2f\n",
									resultSet.getInt("id"),
									resultSet.getString("last_name"),
									resultSet.getString("first_name"),
									resultSet.getString("email"),
									resultSet.getString("department"),
									resultSet.getDouble("salary"));
			} 
			System.out.println();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
