package pl.lukaszgrymulski;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/* firsty I have to add stored procedure to MySQL using this:
	DELIMITER //
	CREATE PROCEDURE increase_salaries_for_department(IN the_department VARCHAR(64), IN increase_amount DECIMAL(10,2))
	BEGIN
	 UPDATE employees2 
	 SET salary= salary + increase_amount 
	 WHERE department=the_department;
	END //
	DELIMITER ;
*/

//stored procedure is a ready SQL statement, it's body is inside MySQL so I need to call it

public class IncreaseSalariesForDepartment {
	
	private static PreparedStatement statement;
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
			statement = connection.prepareCall("{call increase_salaries_for_department(?,?)}");
			String theDepartment = "Engineering";
			Double theIncreaseAmount = 10000.0;
			statement.setString(1, theDepartment);
			statement.setDouble(2, theIncreaseAmount);
			//call stored procedure
			System.out.println("Calling stored procedure: {call increase_salaries_for_department("+theDepartment+","+theIncreaseAmount+")");
			statement.execute();
			System.out.println("Finished calling stored procedure");
			//Show salaries after calling
			System.out.print("SALARIES AFTER CALLING STORED PROCEDURE:\n\n");
			showSalaries();
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
