package pl.lukaszgrymulski;

public class Main {
	
	public static void main(String[] args) {		
		Product product1 = new Product(null, "Rayman 3", 19.99, "games");
		Product product2 = new Product(null, "Half Life 2", 39.99, "games");
		Product product3 = new Product(null, "Potato", 1.55, "cuisine");
		Product product4 = new Product(null, "GTA:SA", 119.46, "games");
		Product product5= new Product(null, "Tomato", 0.89, "cuisine");
		
		ProductInserter.insertProduct(product1);
		ProductInserter.insertProduct(product2);
		ProductInserter.insertProduct(product3);
		ProductInserter.insertProduct(product4);
		ProductInserter.insertProduct(product5);
	}
	
}
