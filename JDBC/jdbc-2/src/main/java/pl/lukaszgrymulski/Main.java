package pl.lukaszgrymulski;

public class Main {
	
	public static void main(String[] args) {			
		//sample inserting products into DB
		//ProductInserter.insertProduct(new Product(null, "GTA 4", 121.99, "games"));
		
		//sample select with filters
		ProductViewer myAllViewer = new ProductViewer();
		myAllViewer.setOrder(SortOrder.ASC_PRODUCT_NAME);
		myAllViewer.viewProductsFiltered();
		
		ProductViewer myPriceViewer = new ProductViewer(20.0, 100.0);
		myPriceViewer.setOrder(SortOrder.ASC_PRICE);
		myPriceViewer.viewProductsFiltered();
		
		ProductViewer myFilteredViewer = new ProductViewer(20.0, 100.0, "games");
		myFilteredViewer.setOrder(SortOrder.DESC_PRICE);
		myFilteredViewer.viewProductsFiltered();
	}
	
}
