package pl.lukaszgrymulski;

import java.sql.*;
import java.util.Scanner;

public class TransactionDemo {

    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement prepStatement;
    private static ResultSet resultSet;

    public static void main(String[] args) {
        try{
            DBInfo db = new DBInfo();
            connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            connection.setAutoCommit(false);

            System.out.println("SALARIES BEFORE:");
            showSalaries("HR");
            showSalaries("Engineering");

            //here I'm gonna start transaction example
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE employees2 SET salary = salary + 10000 WHERE department='Engineering'");
            statement.executeUpdate("UPDATE employees2 SET salary = salary + 5000 WHERE department='HR'");

            System.out.println("Transaction steps are ready \n");

            //ask user if he wants to commit
            boolean ok = askUserIfOkToSave();

            if (ok) {
                connection.commit(); //store in database
                System.out.println("Transaction commited");
            } else {
                connection.rollback(); //discard
                System.out.println("Transaction rolled back");
            }

            //show salaries to see if changes were commited
            showSalaries("HR");
            showSalaries("Engineering");

            connection.close();

        } catch (SQLException e) {
            System.out.println("Connection problemm, SQLState: " + e.getSQLState());
            e.printStackTrace();
        }
    }

    private static boolean askUserIfOkToSave() {
        String result = "";
        Scanner userInput = new Scanner(System.in);
        while (!"n".equals(result) && !"y".equals(result)) {
            System.out.print("You are about to increase some salaries. Do you want to commit [y/n]:");
            result = userInput.nextLine();
        }
        System.out.print("\n");
        return result.equals("y") ? true : false;
    }

    private static void showSalaries(String department) {
        try {
            prepStatement = connection.prepareStatement("SELECT last_name, first_name, salary FROM employees2 WHERE department = ?");
            prepStatement.setString(1, department);
            prepStatement.execute();
            resultSet = prepStatement.getResultSet();

            System.out.printf("%-12s %-12s %-12s\n", "LAST NAME", "1ST NAME", "SALARY");
            while(resultSet.next()){
                System.out.printf("%-12s %-12s %-12.2f\n",
                        resultSet.getString("last_name"),
                        resultSet.getString("first_name"),
                        resultSet.getDouble("salary"));
            }
            System.out.print("\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
