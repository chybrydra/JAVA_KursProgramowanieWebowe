package pl.lukaszgrymulski;

import java.io.*;
import java.sql.*;

public class WriteClob {

    private static Connection connection;
    private static PreparedStatement statement;
    private static Statement selectAllStatement;
    private static ResultSet resultSet;
    private static ResultSetMetaData rsmd;
    private static FileReader input;

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

            String sql = "UPDATE employees3 SET user_txtfile=? WHERE id=1";
            statement = connection.prepareStatement(sql);
            File fileToSend = new File("src/main/resources/sampleTextFile.txt");
            input = new FileReader(fileToSend);
            statement.setCharacterStream(1, input);
            System.out.println("Reading input file: " + fileToSend.getAbsolutePath());
            System.out.println("Storing file in DB: " + fileToSend);
            System.out.println("Query: " + sql);
            statement.executeUpdate();
            System.out.println("File send complete successfully!");
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection.close();

        } catch (SQLException e){
            System.out.println("Problem with MySQL!");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!!");
            e.printStackTrace();
        }
    }

}
