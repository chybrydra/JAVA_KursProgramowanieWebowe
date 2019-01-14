package pl.lukaszgrymulski;

import java.sql.*;

public class ResultSetInfo {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    private static ResultSetMetaData rsmd;

    public static void main(String[] args) {
        try {
            DBInfo db = new DBInfo();
            connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM employees2");
            rsmd = resultSet.getMetaData();

            int columnCount = rsmd.getColumnCount();
            System.out.println("Columns: " + columnCount);
            System.out.println();

            for (int i=1; i<=columnCount ; i++){
                System.out.println("Column name: " + rsmd.getColumnName(i));
                System.out.println("Column type name: " + rsmd.getColumnTypeName(i));
                System.out.println("Column size:" + rsmd.getColumnDisplaySize(i));
                System.out.println("Is Nullable: " + rsmd.isNullable(i));
                System.out.println("Is Auto Increment: " + rsmd.isAutoIncrement(i));
                System.out.println();
            }

            printResults(columnCount);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private static void printResults(int columnCount) throws SQLException {
        Integer[] colWidth = new Integer[columnCount];
        String[] colFormatter = new String[columnCount];

        for (int i=1; i<=columnCount; i++){
            String thisColClass = null;
            try {
                thisColClass = rsmd.getColumnClassName(i);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (rsmd.getColumnName(i).equalsIgnoreCase("email")) {colWidth[i-1]=30;colFormatter[i-1]="%-30s";}
            else if (thisColClass.endsWith("String")) {colWidth[i-1]=16; colFormatter[i-1]="%-16s";}
            else if (thisColClass.endsWith("Integer")) {colWidth[i-1]=4; colFormatter[i-1]="%-4s";}
            else if (thisColClass.endsWith("BigDecimal")) {colWidth[i-1]=6; colFormatter[i-1]="%-6.2f";}
            else if (thisColClass.endsWith("Double")) {colWidth[i-1]=6; colFormatter[i-1]="%-6.2f";}
            else if (thisColClass.endsWith("Float")) {colWidth[i-1]=6; colFormatter[i-1]="%-6.2f";}
            else {colWidth[i-1] = 10; colFormatter[i-1]="Other";}
        }

        for (int i=1; i<=columnCount; i++) {
            String f = "%-" + colWidth[i - 1] + "s";
            String form = String.format(f ,rsmd.getColumnName(i).toUpperCase());
            System.out.print(form);
        }

        System.out.println();

        while (resultSet.next()){

            for (int i=1; i<=columnCount; i++){
                System.out.printf(colFormatter[i-1], resultSet.getObject(i));
            }
            System.out.println();
        }
    }

}
