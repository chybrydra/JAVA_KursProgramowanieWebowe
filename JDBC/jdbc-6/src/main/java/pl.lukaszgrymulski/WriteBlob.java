package pl.lukaszgrymulski;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;

public class WriteBlob {

    private static Connection connection;
    private static PreparedStatement statement;
    private static Statement selectAllStatement;
    private static FileInputStream input;
    private static ResultSet resultSet;
    private static ResultSetMetaData rsmd;

    public static void main(String[] args) {
        try {
            DBInfo db = new DBInfo();
            connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());

            selectAllStatement = connection.createStatement();
            selectAllStatement.execute("SELECT * FROM employees3");

            resultSet = selectAllStatement.getResultSet();
            rsmd = resultSet.getMetaData();

            System.out.println("TABLE COLUMNS:");
            for (int i=1; i<=rsmd.getColumnCount(); i++) {
                System.out.println(i + ". " + rsmd.getColumnName(i) + " " + rsmd.getColumnTypeName(i));
            }
            System.out.println("======================\n");

            String sql = "UPDATE employees3 SET user_file=? WHERE id=1";
            statement = connection.prepareStatement(sql);
            File fileToSend = new File("src/main/resources/sampleFile.pdf");
            input = new FileInputStream(fileToSend);
            statement.setBinaryStream(1, input);
            System.out.println("Reading input file: " + fileToSend.getAbsolutePath());

            System.out.println("Storing file in DB: " + fileToSend);
            System.out.println("Query: " + sql);
            statement.executeUpdate();
            System.out.println("File send complete successfully!");

        } catch (SQLException e){
            System.out.println("Problem with MySQL!");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND!!");
            e.printStackTrace();
        }
    }

}
