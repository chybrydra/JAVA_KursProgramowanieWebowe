package pl.lukaszgrymulski;

import java.sql.*;

/** stored procedure IN - increase_salaries_for_department(?,?)*/

public class IncreaseSalariesForDepartment {
	
	private static PreparedStatement statement;
	private static CallableStatement callStatement;
	private static ResultSet resultSet;
	private static Connection connection;
	
	public static void main(String[] args) {
		try {
			DBInfo db = new DBInfo();
			connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());		
			//Show salaries before doing anything
			System.out.print("SALARIES BEFORE:\n\n");
			showSalaries();
			//prepare the stored procedure call
			callStatement = connection.prepareCall("{call increase_salaries_for_department(?,?)}");
			String theDepartment = "Engineering";
			Double theIncreaseAmount = 10000.0;
			callStatement.setString(1, theDepartment);
			callStatement.setDouble(2, theIncreaseAmount);
			//call stored procedure
			System.out.println("Calling stored procedure: {call increase_salaries_for_department("+theDepartment+","+theIncreaseAmount+")");
			callStatement.execute();
			System.out.println("Finished calling stored procedure");
			//Show salaries after calling
			System.out.print("SALARIES AFTER CALLING STORED PROCEDURE:\n\n");
			showSalaries();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static void showSalaries(){
		try {
			statement = connection.prepareStatement("SELECT * FROM employees2");
			resultSet = statement.executeQuery();
			printResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static void printResultSet() {
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
