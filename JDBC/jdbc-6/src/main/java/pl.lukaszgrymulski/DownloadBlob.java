package pl.lukaszgrymulski;

import java.io.*;
import java.sql.*;

public class DownloadBlob {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    private static InputStream input;
    private static FileOutputStream output;

    public static void main(String[] args) {
        try {
            DBInfo db = new DBInfo();
            connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            statement = connection.createStatement();
            String sql = "SELECT user_file FROM employees3 WHERE id=1";
            statement.executeQuery(sql);
            resultSet = statement.getResultSet();

            File downloadedFile = new File("src/main/resources/user1file.pdf");
            output = new FileOutputStream(downloadedFile);

            if (resultSet.next()) {
                input = resultSet.getBinaryStream("user_file");
                System.out.println("Reading file from DB");
                System.out.println(sql);
                byte[] buffer = new byte[1024];
                while (input.read(buffer) > 0) {
                    output.write(buffer);
                }
                System.out.println("\nSaved to file: " + downloadedFile.getAbsolutePath());
                System.out.println("Download from DB complete!");
            }

        } catch (SQLException e) {
            System.out.println("MySQL problem!");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Input/Output problem!");
            e.printStackTrace();
        }
    }

}
