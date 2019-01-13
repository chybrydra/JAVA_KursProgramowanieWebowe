package pl.lukaszgrymulski;

import java.sql.*;

public class GetCountForDepartment {

    private static CallableStatement statement;
    private static Connection connection;

    public static void main(String[] args) {
        try {
            DBInfo db = new DBInfo();
            connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());

            String theDepartment = "Engineering";
            statement = connection.prepareCall("{call get_count_for_department(?,?)}");
            statement.setString(1, theDepartment);
            statement.registerOutParameter(2, Types.INTEGER);

            System.out.println("Calling the stored procedure: get_count_for_department(" + theDepartment + ", ?)");
            statement.execute();
            System.out.println("Finished calling the stored procedure");

            int theCount = statement.getInt(2);
            System.out.println("\nThe count: "+ theCount + " people in the " + theDepartment + " department.");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
