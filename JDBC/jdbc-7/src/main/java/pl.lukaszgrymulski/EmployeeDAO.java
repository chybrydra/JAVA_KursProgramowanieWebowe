package pl.lukaszgrymulski;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EmployeeDAO {

    private Connection connection;

    public EmployeeDAO() {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("db.properties"));
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            String dburl = props.getProperty("dburl");
            connection = DriverManager.getConnection(dburl, user, password);
            System.out.println("DBConnection successful to DataBase: " + connection.getCatalog());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<Employee>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM employees2");
            while (resultSet.next()){
                Employee tempEmployee = convertRowToEmployee(resultSet);
                list.add(tempEmployee);
            }
        } catch (SQLException e) {
            System.out.println("Problem with DataBase connection!");
            e.printStackTrace();
        } finally {
            close(statement, resultSet);
        }
        return list;
    }

    public List<Employee> searchEmployees(String lastName) {
        List<Employee> list =  new ArrayList<Employee>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try{
            lastName += "%";
            statement = connection.prepareStatement("SELECT * FROM employees2 WHERE last_name LIKE ?");
            statement.setString(1,lastName);
            rs = statement.executeQuery();
            while (rs.next()){
                Employee tempEmployee = convertRowToEmployee(rs);
                list.add(tempEmployee);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement, rs);
            return list;
        }
    }

    private Employee convertRowToEmployee(ResultSet rs) {
        Employee employee = null;
        try {
            int id = rs.getInt("id");
            String lastName = rs.getString("last_name");
            String firstName = rs.getString("first_name");
            String email = rs.getString("email");
            BigDecimal salary = rs.getBigDecimal("salary");
            employee = new Employee(id, lastName, firstName, email, salary);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    private void close(Statement statement, ResultSet resultSet) {
        try {
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error while trying to close result set.");
            e.printStackTrace();
        }
        try {
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error while trying to close statement.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EmployeeDAO dao = new EmployeeDAO();
        System.out.println("\nSEARCH THOM%");
        System.out.println(dao.searchEmployees("thom"));
        System.out.println("\nSEARCH ALL:");
        System.out.println(dao.getAllEmployees());
    }
}
