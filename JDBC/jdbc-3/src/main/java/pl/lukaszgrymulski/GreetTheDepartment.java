package pl.lukaszgrymulski;

import java.sql.*;

public class GreetTheDepartment {

    private static CallableStatement statement;
    private static Connection connection;

    public static void main(String[] args) {
        try {
            DBInfo db = new DBInfo();
            connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            String theDepartment = "Engineering";
            statement = connection.prepareCall("{call greet_the_department(?)}");
            statement.registerOutParameter(1, Types.VARCHAR);
            statement.setString(1, theDepartment);
            System.out.println("Calling the stored procedure: greet_the_department(" + theDepartment + ")");
            statement.execute();
            System.out.println("Finished calling the stored procedure");
            String theResult = statement.getString(1);
            System.out.println("The result: " + theResult);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
