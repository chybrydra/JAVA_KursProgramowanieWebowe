package pl.lukaszgrymulski;

import java.io.*;
import java.sql.*;

public class DownloadClob {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    private static Reader input;
    private static FileWriter output;

    public static void main(String[] args) {
        try {
            DBInfo db = new DBInfo();
            connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
            statement = connection.createStatement();
            String sql = "SELECT user_txtfile FROM employees3 WHERE id=1";
            resultSet = statement.executeQuery(sql);

            File downloadedFile = new File("src/main/resources/user1txtfile.txt");
            output = new FileWriter(downloadedFile);

            if (resultSet.next()) {
                input = resultSet.getCharacterStream("user_txtfile");
                System.out.println("Reading file from DB");
                System.out.println(sql);
                char[] buffer = new char[1];
                while (input.read(buffer)>0){
                    output.write(buffer);
                }
                System.out.println("\nSaved to file: " + downloadedFile.getAbsolutePath());
                System.out.println("Download from DB complete!");

                input.close();
                output.close();
            }

            input.close();
            output.close();
            connection.close();
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
