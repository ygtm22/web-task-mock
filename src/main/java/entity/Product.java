package entity;

public class Product{
	private Integer id;
	private Integer productId;
	private String productName;
	private Integer price;
	private String categoryName;
	private Integer categoryId;
	private String description;
	
	public Product() {
		
	}
	
	public Product(Integer productId, String productName, Integer price, String categoryName) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.categoryName = categoryName;
	}
	
	public Product(Integer productId, String productName, Integer price, Integer categoryId, String description) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.categoryId = categoryId;
		this.description = description;
	}
	
	public Product(Integer productId, String productName, Integer price, String categoryName, String description) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.categoryName = categoryName;
		this.description = description;
	}
	
	public Product(Integer id, Integer productId, String productName, Integer price, String categoryName, String description) {
		this.id = id;
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.categoryName = categoryName;
		this.description = description;
	}
	
	public Product(Integer id, Integer productId, String productName, Integer price, Integer categoryId, String description) {
		this.id = id;
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.categoryId = categoryId;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
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
	
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}