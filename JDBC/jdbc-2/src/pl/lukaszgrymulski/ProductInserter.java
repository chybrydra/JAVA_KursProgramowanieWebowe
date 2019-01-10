package pl.lukaszgrymulski;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductInserter {
	
	static void insertProduct(Product product) {			
		try {
			DBInfo db = new DBInfo();
			Connection connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());			
			PreparedStatement statement = connection.prepareStatement("INSERT INTO products VALUES (NULL, ?, ?, ?)");
			statement.setString(1, product.getName());
			statement.setDouble(2, product.getPrice());
			statement.setString(3, product.getCategory());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}						
	}
}
