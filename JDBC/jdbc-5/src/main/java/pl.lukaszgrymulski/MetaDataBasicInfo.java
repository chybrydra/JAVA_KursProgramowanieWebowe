package pl.lukaszgrymulski;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MetaDataBasicInfo {

    private static Connection connection;

    public static void main(String[] args) {
        try {
            DBInfo db = new DBInfo();
            connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());

            DatabaseMetaData dbmd = connection.getMetaData();

            System.out.println("DB Product Name: " + dbmd.getDatabaseProductName());
            System.out.println("DB Product Version: " + dbmd.getDatabaseProductVersion());
            System.out.println("Driver Name: " + dbmd.getDriverName());
            System.out.println("Driver Version: " + dbmd.getDriverVersion());
            System.out.println("UserName: " + dbmd.getUserName());
            System.out.println();
            System.out.println("URL: " + dbmd.getURL());
            System.out.println("SQL Keywords: " + dbmd.getSQLKeywords());
            System.out.println("Numeric Functions: " + dbmd.getNumericFunctions());
            System.out.println("Catalog Separator: " + dbmd.getCatalogSeparator());
            System.out.println("Schema Term:" + dbmd.getSchemaTerm());

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
