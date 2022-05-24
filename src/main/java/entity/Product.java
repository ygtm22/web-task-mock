package entity;

public class Product{
	private Integer productId;
	private String productName;
	private Integer price;
	private String categoryName;
	
	public Product() {
		
	}
	
	public Product(Integer productId, String productName, Integer price, String categoryName) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.categoryName = categoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}