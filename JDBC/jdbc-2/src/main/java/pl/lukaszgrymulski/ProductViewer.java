package pl.lukaszgrymulski;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductViewer {
	
	private Connection connection;
	private String statementString;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private Double lowerLimit;
	private Double upperLimit;
	private String productCategory;
	private String searchingMode;
	private SortOrder order = SortOrder.NONE;
	
	
	public ProductViewer() {
		this.searchingMode = "000"; //all filters
	}
	
	public ProductViewer(Double lowerLimit, Double upperLimit, String productCategory) {
		this.lowerLimit = lowerLimit;
		this.upperLimit = upperLimit;
		this.productCategory = productCategory;
		this.searchingMode = "111"; //all filters
	}
	
	public ProductViewer(Double lowerLimit, Double upperLimit) {
		this.lowerLimit = lowerLimit;
		this.upperLimit = upperLimit;
		this.searchingMode = "110"; //both price filters
	}	
	
	void setOrder(SortOrder order) {
		this.order = order;
	}
	
	//MAIN METHOD
	void viewProductsFiltered() {
		connectToDataBase();
		createStatement();
		try {
			resultSet = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		printResultSet(resultSet);
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void createStatement() {		
		try {
			if (searchingMode.equals("111")) {
				statementString = "SELECT * FROM products WHERE price > ? AND price < ? AND category = ?";
				addOrder();
				statement = connection.prepareStatement(statementString);
				statement.setDouble(1, lowerLimit);
				statement.setDouble(2, upperLimit);
				statement.setString(3, productCategory);
			} else if (searchingMode.equals("110")) {
				statementString = "SELECT * FROM products WHERE price > ? AND price < ?";
				addOrder();
				statement = connection.prepareStatement(statementString);
				statement.setDouble(1, lowerLimit);
				statement.setDouble(2, upperLimit);
			} else {
				statementString = "SELECT * FROM products";
				addOrder();
				if ("ASC".equals(order)) statementString += " ORDER BY price ASC";
				if ("DESC".equals(order)) statementString += " ORDER BY price DESC";
				statement = connection.prepareStatement(statementString);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void addOrder() {
		if (order.equals(SortOrder.ASC_PRICE)) statementString += " ORDER BY price ASC";
		if (order.equals(SortOrder.DESC_PRICE)) statementString += " ORDER BY price DESC";
		if (order.equals(SortOrder.ASC_PRODUCT_NAME)) statementString += " ORDER BY name ASC";
		if (order.equals(SortOrder.DESC_PRODUCT_NAME)) statementString += " ORDER BY name DESC";
	}
	
	
	private void connectToDataBase() {		
		try {
			DBInfo db = new DBInfo();
			connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void printResultSet(ResultSet resultSet) {
		try {
			System.out.printf("%-4s %-24s %-12s %-12s\n", "id", "name", "price", "category");
			while (resultSet.next()) {
				System.out.printf("%-4d %-24s %-12f %-12s\n",
									resultSet.getInt("id"),
									resultSet.getString("name"),
									resultSet.getDouble("price"),
									resultSet.getString("category"));
			} 
			System.out.println();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
