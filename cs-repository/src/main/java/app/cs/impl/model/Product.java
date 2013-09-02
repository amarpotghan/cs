package app.cs.impl.model;


public class Product {


	private String productId;
	private String productName;
	private String productType;

	public Product(String productId, String productName, String productType) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productType = productType;
	}
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}
}
