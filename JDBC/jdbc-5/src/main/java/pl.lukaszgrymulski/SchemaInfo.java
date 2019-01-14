package pl.lukaszgrymulski;

import java.sql.*;

public class SchemaInfo {

    private static DatabaseMetaData dbmd;
    private static String catalog = "jdbc_demo";
    private static String schemaPattern;
    private static String tableNamePattern = "employees";
    private static String columnNamePattern;
    private static String[] types;
    private static Connection connection;
    private static ResultSet resultSet;

    public static void main(String[] args) {
        try {
            DBInfo db = new DBInfo();
            Connection connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            dbmd = connection.getMetaData();

            System.out.println("LIST OF TABLES: ");
            System.out.println("----------------");
            resultSet = dbmd.getTables("jdbc_demo", null, null, null);
            while (resultSet.next()){
                System.out.println(resultSet.getString("TABLE_NAME"));
            }

            System.out.println("\nLIST OF COLUMNS: ");
            System.out.println("----------------");
            resultSet = dbmd.getColumns("jdbc_demo", null, "employees2", null);
            while (resultSet.next()){
                System.out.println(resultSet.getString("COLUMN_NAME"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
