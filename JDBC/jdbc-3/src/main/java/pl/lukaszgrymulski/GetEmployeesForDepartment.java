package pl.lukaszgrymulski;

import java.sql.*;

/** call stored procedure which returns ResultSet */

public class GetEmployeesForDepartment {

    private static PreparedStatement statement;
    private static CallableStatement callStatement;
    private static ResultSet resultSet;
    private static Connection connection;

    public static void main(String[] args) {
        try {
            DBInfo db = new DBInfo();
            connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());

            callStatement = connection.prepareCall("{call get_employees_for_department(?)}");
            String theDepartment = "Engineering";
            callStatement.setString(1, theDepartment);

            System.out.println("Calling stored procedure: {call get_employees_for_department("+theDepartment+")");
            callStatement.execute();
            System.out.println("Finished calling stored procedure");

            resultSet = callStatement.getResultSet();
            printResultSet();

            connection.close();
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
